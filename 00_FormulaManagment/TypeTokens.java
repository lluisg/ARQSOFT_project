import java.util.*;

public class TypeTokens{
  ArrayList<String> functions;
  ArrayList<String> operators;
  
  public TypeTokens(){
    this.functions = new ArrayList<String>();
    this.operators = new ArrayList<String>();

    this.functions.add("sum");
    this.functions.add("max");
    this.functions.add("min");
    this.functions.add("mean");

    this.operators.add("+");
    this.operators.add("-");
    this.operators.add("*");
    this.operators.add("/");
  }

  public ArrayList<String> getOp() {
    return this.operators;
  }
  public ArrayList<String> getFunctions() {
    return this.functions;
  }

}