import java.util.*;
import java.util.regex.*;

public class StringParser{
  private LinkedList<TokenInfo> tokenInfos;

  public StringParser(){
    tokenInfos = new LinkedList<TokenInfo>();
    TypeTokens typesTokens = new TypeTokens();

    //this.add("sum|max|min|mean", 1); //funtions
    ArrayList<String> typesF = typesTokens.getFunctions();
    StringBuffer functions_aux = new StringBuffer("");
    for(String s : typesF){
      functions_aux.append(s);
      if(typesF.indexOf(s) != (typesF.size() -1)){
        functions_aux.append('|');
      }  
    }
    String functions = functions_aux.toString();
    this.add(functions, 1);
    
    this.add("[\\(|\\)]", 2); //parenthesis

    //this.add("[\\+ | \\- | \\* | \\/]", 3); //operators
    ArrayList<String> typesOp = typesTokens.getOp();
    StringBuffer operators_aux = new StringBuffer("");
    for(String s : typesOp){
      operators_aux.append('\\'+s);
      if(typesOp.indexOf(s) != (typesOp.size() -1)){
        operators_aux.append('|');
      }  
    }
    String operators = operators_aux.toString();
    this.add(operators, 3);
    
    this.add("[\\=]", 4);
    this.add("[0-9]*\\.*[0-9]+",5); //float
    this.add("[a-zA-Z]+[0-9]+\\:[a-zA-Z]+[0-9]+",6); //CellRange
    this.add("[a-zA-Z]+[0-9]+",7); //Cell
    this.add("\\;", 8); //separator of elements
    this.add("[a-zA-Z][a-zA-Z0-9\\s", 9); //text!!
    this.add(".",10);
  }

  public void add(String regex, int token) {
    tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
  }

  public LinkedList<Token> parse(String string){
    LinkedList<Token> tokens = new LinkedList<Token>();

    tokens.clear(); //clear the list of tokens to return    
    String s = string.trim();
    while (!s.equals("")) {
      boolean match = false;

      for (TokenInfo info : tokenInfos) {
        Matcher m = info.regex.matcher(s);
        if (m.find()) {
          match = true;

          String tok = m.group().trim();

          s = m.replaceFirst("");

          tokens.add(new Token(info.token, tok));
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
    public final int token;

    public TokenInfo(Pattern regex, int token) {
      super();
      this.regex = regex;
      this.token = token;
    }
  }
}