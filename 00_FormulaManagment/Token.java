import java.util.*;
public class Token{

  public final int token;
  public final String sequence;

  public Token(int token, String sequence) {
    super();
    this.token = token;
    this.sequence = sequence;
  }

  public String getContent(){
    return this.sequence;
  }
  public int getType(){
    return this.token;
  }
}