/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulamanagement;

/**
 *
 * @author Lluis
 */

import java.util.*;

public class StringManager {
    private StringParser parser;
    
    public StringManager() {
        this.parser = new StringParser();
    }
    
    public LinkedList<Token> parse(String string) throws ParserException, SpellException{
        LinkedList<Token> tokens = parser.parse(string);
        this.checkSpell(tokens);
        
        return tokens;
    }
    
    public void checkSpell(LinkedList<Token> t) throws SpellException{
    int index = -1;
    boolean open_f = false; //there is an open parenthesis for a function
    boolean open_p = false; //there is an open parenthesis
    
    if(t.get(0).getType() == Token.TokenType.EQUAL){
      for(Token tok : t){
        index++;
        if(tok.getType() == Token.TokenType.TEXT){
          throw new SpellException("Spelling: No text inside formula");
        }

        if(tok.getType() == Token.TokenType.FUNCTION){
          if(open_p == true || open_f ==true){
            throw new SpellException("Spelling: more than one stacked parenthesis not allowed for now");
          }else if(t.get(index-1).getType() != Token.TokenType.CL_PARENTHESIS && t.get(index-1).getType() != Token.TokenType.EQUAL){
            throw new SpellException("Spelling: parenthesis after an operator, = or function");
          }else{
            open_f = true;
          }

        }else if(tok.getType() == Token.TokenType.OP_PARENTHESIS){
          if(open_p == true || open_f ==true){
            throw new SpellException("Spelling: more than one stacked parenthesis not allowed for now");
          }else if(t.get(index-1).getType() != Token.TokenType.OPERATOR && t.get(index-1).getType() != Token.TokenType.EQUAL){
            throw new SpellException("Spelling: parenthesis after an operator, = or function");
          }else{
            open_p = true;
          }

        }else if(tok.getType() == Token.TokenType.CL_PARENTHESIS){
          if(open_p == false && open_f == false){
            throw new SpellException("Spelling: check correct closing parenthesis");
          }else if(open_p == true){
            open_p = false;
          }else if(open_f == true){
            open_f = false;
          }

        }else if(tok.getType() == Token.TokenType.OPERATOR){
          if(t.get(index-1).getType() == Token.TokenType.EQUAL && (tok.getContent().equals("*") || tok.getContent().equals("/"))){
           throw new SpellException("Spelling: Not * or / after =");

          }else if(t.get(index-1).getType() == Token.TokenType.OPERATOR){
            throw new SpellException("Spelling: Not operator after operator");
          }

        }else if(tok.getType() == Token.TokenType.CELL_RANGE){
          if(open_f == false){
              throw new SpellException("Spelling: range of cells only inside functions");
          }

        }else if(tok.getType() == Token.TokenType.TEXT){
          if(open_f == false){
            throw new SpellException("Spelling: separators ; only inside functions");
          }else if(t.get(index+1).getType() == Token.TokenType.OP_PARENTHESIS){
            throw new SpellException("Spelling: after ; there should be an element");
          }

        }else if(tok.getType() == Token.TokenType.UNK){
          throw new SpellException("Spelling: unidentified element "+tok.getContent());
        }

        if(index == t.size()-1 && (open_p == true || open_f == true)){
          throw new SpellException("Spelling: check correct parenthesis");

        }
        
      }
    }
  }

  public LinkedList<Token> infix2postfix(LinkedList<Token> t){
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
