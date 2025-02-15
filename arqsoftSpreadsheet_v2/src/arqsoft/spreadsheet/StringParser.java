/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import arqsoft.spreadsheet.Token.TokenType;
import arqsoft.spreadsheet.domain.FunctionType;
import arqsoft.spreadsheet.domain.OperatorType;
import java.util.List;

import java.util.*;
import java.util.regex.*;

public final class StringParser {
    private static List<TokenInfo> tokenInfos;

    public StringParser(){
        this.tokenInfos = new LinkedList<>();
    
        //parenthesis
        this.add("[\\(]", TokenType.OP_PARENTHESIS); 
        this.add("[\\)]",TokenType.CL_PARENTHESIS);

        //functions
        String functions = "";
        for (FunctionType fun : FunctionType.values()){
           functions+= fun.getString() + "|";
           functions+= fun.getString().toUpperCase() + "|";
        }
        functions = functions.substring(0, functions.length()-1);
        this.add(functions, TokenType.FUNCTION);    

        //operators
        String operators = "";
        for (OperatorType op : OperatorType.values()){
            operators += "\\" + op.getSymbol() + "|";
        }
        operators = operators.substring(0,operators.length()-1);
        this.add(operators, TokenType.OPERATOR);

        this.add("=",TokenType.EQUAL); //equal
        this.add("[0-9]*\\.*[0-9]+",TokenType.FLOAT); //float
        this.add("[a-zA-Z]+[0-9]+\\:[a-zA-Z]+[0-9]+",TokenType.CELL_RANGE); //CellRange
        this.add("[a-zA-Z]+[0-9]+",TokenType.CELL); //Cell
        this.add("\\;", TokenType.SEPARATOR); //separator of elements
        this.add("[a-zA-Z][a-zA-Z0-9\\s]*", TokenType.TEXT); //text
        this.add("[ ]", TokenType.WHITESPACE);
        this.add(".",TokenType.UNK);//other symbols
    }

    private void add(String regex, TokenType token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }

    public static List<Token> parse(String string) throws ParserException{
        LinkedList<Token> tokens = new LinkedList<Token>();

        String s = string.trim();
        while (!s.equals("")) {
            boolean match = false;

            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("");
                    if(info.token != Token.TokenType.WHITESPACE){
                        tokens.add(new Token(info.token, tok));          
                    }

                    break;
                }
            }
            if (!match){
                throw new ParserException("Unexpected character in input: "+s);
            }
        }
        return tokens;
    }

    private class TokenInfo {
        public final Pattern regex;
        public final TokenType token;

        public TokenInfo(Pattern regex, TokenType token) {
            super();
            this.regex = regex;
            this.token = token;
    }
  }
 
}