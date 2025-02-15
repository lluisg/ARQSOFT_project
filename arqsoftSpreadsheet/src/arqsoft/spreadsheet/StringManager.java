/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

/**
 *
 * @author Lluis
 */

import arqsoft.spreadsheet.domain.FormulaComponentFabric;
import arqsoft.spreadsheet.domain.FormulaComponent;
import arqsoft.spreadsheet.domain.Function;
import arqsoft.spreadsheet.domain.OperatorType;
import java.util.*;

public class StringManager {
    private static StringParser parser;
    private static FormulaComponentFabric fcFabric;
    
    public StringManager() {
        this.parser = new StringParser();
        this.fcFabric = new FormulaComponentFabric();
    }
    
    public static LinkedList<Token> parseString(String string) throws CancelException, ParserException{
        LinkedList<Token> tokens = parser.parse(string);
        
        if(tokens.size() == 1 && "CANCEL".equalsIgnoreCase(tokens.get(0).getContent())){
            throw new CancelException();
        }
        return tokens;
    }
    
    public static void checkSyntax(List<Token> tokens) throws SyntaxException{    
        boolean function = false;
        Stack<Token.TokenType> open_par = new Stack<>(); 
        if(tokens.get(0).getType() == Token.TokenType.EQUAL){
            for(int i=1 ; i<tokens.size() ; i++){
                Token tok = tokens.get(i);

                switch (tok.getType()) {
                    case EQUAL:
                        throw new SyntaxException("EXTRA_EQUALS_INSIDE_FORMULA");
                        
                    case TEXT:
                        throw new SyntaxException("TEXT_INSIDE_FORMULA: " + tok.getContent() + " is not a formula element");
                        
                    case FUNCTION:
                        if (i+1 == tokens.size() || tokens.get(i+1).getType() != Token.TokenType.OP_PARENTHESIS){
                            throw new SyntaxException("MISSING_OPEN_PARENTHESIS: Missign parenthesis after function " + tok.getContent());
                        }
                        function = true;
                        break;
                      
                    case OP_PARENTHESIS:
                        if (i+1 != tokens.size() && tokens.get(i+1).getType() == Token.TokenType.CL_PARENTHESIS){
                            throw new SyntaxException("USEFUL PARENTHESIS: Parenthesis without any data");
                        }
                        if (function){
                            open_par.push(Token.TokenType.FUNCTION);
                            function = false;
                        }else{
                            open_par.push(Token.TokenType.OP_PARENTHESIS);
                        }
                        break;
                        
                    case CL_PARENTHESIS:
                        if (open_par.empty()){
                            throw new SyntaxException("EXTRA_CLOSE_PARENTHESIS: Too many close parenthesis");
                        }
                        open_par.pop();
                        if(i+1 != tokens.size()){
                            Token.TokenType tt = tokens.get(i+1).getType();
                            if (tt != Token.TokenType.OPERATOR && tt != Token.TokenType.CL_PARENTHESIS && tt != Token.TokenType.SEPARATOR){
                                throw new SyntaxException("MISSING_OPERATOR: "+ tokens.get(i+1).getContent()+ " not allowed after ')'");
                            }
                        }
                        break;
                        
                    case OPERATOR:
                        Token.TokenType prev = tokens.get(i-1).getType();
                        if((prev == Token.TokenType.EQUAL || prev == Token.TokenType.SEPARATOR || prev == Token.TokenType.OP_PARENTHESIS)
                                && (tok.getContent().equals("*") || tok.getContent().equals("/"))){
                            throw new SyntaxException("MISSING_OPERAND: Not * or / after " + tokens.get(i-1).getContent());
                        }
                        if(i+1 == tokens.size()){
                            throw new SyntaxException("MISSING_OPERAND: Formula cannot end with an operator");
                        }else{
                            Token.TokenType next = tokens.get(i+1).getType();
                            if(next == Token.TokenType.CL_PARENTHESIS || next == Token.TokenType.SEPARATOR){
                                throw new SyntaxException("MISSING_OPERAND: Element after "+ tok.getContent() +" cannot be " + tokens.get(i+1).getContent()); 
                            }else if (next == Token.TokenType.OPERATOR){ // could be MISSING_OPERAND
                                throw new SyntaxException("NO_CONSEQUTIVE_OPERATORS: Not operator after operator:" + tok.getContent() + tokens.get(i+1).getContent());
                            }
                        }
                        break;
                        
                    case CELL_RANGE:     
                        if (open_par.empty() || open_par.peek() != Token.TokenType.FUNCTION){
                            throw new SyntaxException("CELL_RANGE_OUTSIDE_FUNCTION: Range of cells only inside functions");
                        }
                        if(tokens.get(i-1).getType() != Token.TokenType.SEPARATOR && tokens.get(i-2).getType() != Token.TokenType.FUNCTION &&
                                (i+1 == tokens.size() || tokens.get(i+1).getType() != Token.TokenType.SEPARATOR)){
                            throw new SyntaxException("MISSING_SEPARATORS: Range of cells cannot be combined with other elements");
                        }
                        break;
                        
                    case SEPARATOR:  
                        if(open_par.empty() || open_par.peek() != Token.TokenType.FUNCTION){
                            throw new SyntaxException("SEPARATOR_OUTSIDE_FUNCTION: Separator ';' only inside functions");
                        }
                        if(i+1 == tokens.size() || tokens.get(i+1).getType() == Token.TokenType.CL_PARENTHESIS){
                            throw new SyntaxException("MISSING_ELEMENT_AFTER_SEPARATOR: Missing element after separator ;");
                        }
                        break;
                        
                    case UNK:
                        throw new SyntaxException("UNKNOWN_COMPONENT: Unidentified element "+tok.getContent());
                }
            }
            
            if (!open_par.empty()){
                throw new SyntaxException("MISSING_CLOSE_PARENTHESIS: check correct parenthesis");
            }
                
        }else{
            throw new SyntaxException("MISSING_EQUAL_SIGN: No equal sign at the beginig of the formula");
        }        
    }

    public static List<FormulaComponent> infix2postfix(List<Token> tokens){
        Stack<OperatorType> stack = new Stack<>();
        List<FormulaComponent> queue = new LinkedList<>();
        
        for (int i=0 ; i<tokens.size() ; i++){
            Token tok = tokens.get(i);
            String content = tok.getContent();
            
            switch (tok.getType()){
                case EQUAL:
                    break;
                    
                case OPERATOR:   
                    OperatorType ot = fcFabric.string2operator(content);

                    if (ot == OperatorType.ADDITION || ot == OperatorType.SUBSTRACTION){
                        Token.TokenType prev = tokens.get(i-1).getType();
                        if(prev == Token.TokenType.EQUAL || prev == Token.TokenType.OP_PARENTHESIS || prev == Token.TokenType.SEPARATOR){
                            Token t = new Token(Token.TokenType.FLOAT,"0");
                            queue.add(fcFabric.createFormulaComponent(t));
                        }
                    }
                    
                    boolean cont = true;
                    while(cont && !stack.empty()) {
                        OperatorType prev_ot = stack.peek();
                        if (prev_ot == OperatorType.OP_PARENTHESIS){
                            cont = false;
                        }else if(prev_ot.getPrecedence() <= ot.getPrecedence()){
                            //last operator has higer or equal precedence--> qe enqueu
                            queue.add(fcFabric.createOperator(stack.pop()));
                        }else{
                            cont = false;
                        }
                    }
                    stack.add(ot);
                    break;
                    
                case FLOAT:
                    queue.add(fcFabric.createFormulaComponent(tok));
                    break;
                    
                case FUNCTION:
                    int j = i+2;
                    int op_par = 1;
                    LinkedList<Token> fun_ele = new LinkedList<>();
                    fun_ele.add(tokens.get(i+1));
                    while(op_par > 0){
                        Token t = tokens.get(j);
                        if (t.getType() == Token.TokenType.OP_PARENTHESIS){
                            op_par ++;
                        }else if(t.getType() == Token.TokenType.CL_PARENTHESIS){
                            op_par--;
                        }
                        fun_ele.add(t);
                        j++;
                    }
                    Function f = (Function) fcFabric.createFormulaComponent(tok);
                    f.setElements(infix2postfix(fun_ele));
                    queue.add(f);
                    i = j-1;
                    break;
                    
                case CELL:
                    queue.add(fcFabric.createFormulaComponent(tok));
                    break;
                    
                case CELL_RANGE:
                    queue.add(fcFabric.createFormulaComponent(tok));
                    break;
                    
                case SEPARATOR:
                    boolean open = true;
                    while (open){
                        OperatorType ott = stack.pop();
                        if (ott == OperatorType.OP_PARENTHESIS){
                            open = false;
                        }else{
                            queue.add(fcFabric.createOperator(ott));
                        }
                    }
                    stack.add(OperatorType.OP_PARENTHESIS);
                    break;
                    
                case OP_PARENTHESIS:
                    stack.add(OperatorType.OP_PARENTHESIS);
                    break;
                    
                case CL_PARENTHESIS:
                    boolean isOpen = true;
                    while (isOpen){
                        OperatorType ott = stack.pop();
                        if (ott == OperatorType.OP_PARENTHESIS){
                            isOpen = false;
                        }else{
                            queue.add(fcFabric.createOperator(ott));
                        }
                    }
                    break;
            }        
        }
        
        while(!stack.empty()){
            queue.add(fcFabric.createOperator(stack.pop()));
        }
        
        return queue;
    }
    
    public static boolean isCellCoordinate(String s){
        try{    
            List<Token> l = parser.parse(s);
            if(l.isEmpty()){
                return false;
            } else if(l.size() == 1 && l.get(0).getType() == Token.TokenType.CELL){
                return true;            
            }
            return false;
            
        }catch(ParserException ex){
            return false;
        }
    }   
    
}

