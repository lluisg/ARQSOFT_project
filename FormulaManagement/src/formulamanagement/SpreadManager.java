/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulamanagement;

import java.util.LinkedList;

/**
 *
 * @author betbp
 */
public class SpreadManager {
  private StringManager strMng;
  private LinkedList<Token> tokens;

  public SpreadManager(){
    this.strMng = new StringManager();
  }
  
  public void evaluateContent(String content) throws ParserException, SpellException{
    this.tokens = strMng.parse(content);

    if(tokens.get(0).getType() == Token.TokenType.EQUAL){ //first element is =
      printList(tokens);

    }else{ //is text or numerical
      boolean allInt = true;
      for(Token t : tokens){
        if(t.getType() == Token.TokenType.TEXT){
          allInt = false;
          System.out.println("text: "+content);
        }
      }
      if(allInt == true){
        int value = Integer.parseInt(content);
        System.out.println("number: "+content+" with value "+Integer.toString(value));
      }

    }
  }

  public void printList(LinkedList<Token> l){    
    for(Token t : l) {
      System.out.printf("%s, %s\n",t.getContent(), t.getType());
    }
  }
}
