/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.demo.formulaManagement;

import arqsoft.spreadsheet.CancelException;
import arqsoft.spreadsheet.ParserException;
import arqsoft.spreadsheet.SyntaxException;
import arqsoft.spreadsheet.StringManager;
import arqsoft.spreadsheet.Token;
import arqsoft.spreadsheet.domain.FormulaComponent;
import java.util.List;

/**
 *
 * @author betbp
 */
public class SpreadManagerDemo {
  private StringManager strMng;
  private List<Token> tokens;

  public SpreadManagerDemo(){
    this.strMng = new StringManager();
  }
  
  public void evaluateContent(String content) throws CancelException,ParserException{
    this.tokens = strMng.parseString(content);
    if (tokens.isEmpty()){
        System.out.println("Empty string");
    }else if(tokens.get(0).getType() == Token.TokenType.EQUAL){ //first element is =
        System.out.println("Formula:");
        printList(tokens);
        try{
            strMng.checkSyntax(tokens);
            System.out.println("Syntax ok");
            List<FormulaComponent> fcList = strMng.infix2postfix(tokens);
            System.out.println("List in postfix notation");
        }catch(SyntaxException ex){
            System.out.println(ex.getMessage());
        }
        
    }else{ //is text or numerical
      boolean allInt = true;
      for(Token t : tokens){
        if(t.getType() == Token.TokenType.TEXT){
          allInt = false;
          System.out.println("text: "+content);
        }
      }
      if(allInt == true){
        float value = Float.parseFloat(content);
        System.out.println("number: "+content+" with value "+Float.toString(value));
      }

    }
  }

  public void printList(List<Token> l){    
    for(Token t : l) {
      System.out.printf("%s, %s\n",t.getContent(), t.getType());
    }
  }
}
