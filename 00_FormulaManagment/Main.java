import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);

    System.out.println("Enter content for new cell\n(Remember no spaces)");
    //String form = console.nextLine();
    String form;
    //form = "=SIN(x;A2:A3)*(1+var_12)*(AZ2+3.15)";
    //form = "=MAX(2)";
    //form = "32546";
    //form = "hello worlds";
    //form = "=g2a"; //detecta un CellOperand
    //form = "=a2:a3:a5";
    form = "=sum(a2:a3; 324; 324.36)*A2";

    System.out.println("Entered: "+form);
    
    SpreadManager sprMng = new SpreadManager();

    try {
      sprMng.evaluateContent(form);
    }catch (ParserException e) {
      System.out.println(e.getMessage());
    }
  }
}