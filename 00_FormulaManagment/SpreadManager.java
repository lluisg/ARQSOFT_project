import java.util.*;
import java.util.regex.*;
public class SpreadManager{
  StringParser parser;
  LinkedList<Token> tokens;

  public SpreadManager(){
    this.parser = new StringParser();
  }

  public void evaluateContent(String content) throws ParserException{
    this.tokens = parser.parse(content); //if not starts 

    if(tokens.get(0).getType() == 4){ //first element is =
      printList(tokens);

    }else{ //is text or numerical
      boolean allInt = true;
      for(Token t : tokens){
        if(t.getType() == 5){
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
      System.out.printf("%s, %s\n",t.getContent(), Integer.toString(t.getType()));
    }
  }
}