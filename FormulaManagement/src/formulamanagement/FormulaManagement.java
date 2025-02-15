/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulamanagement;

import java.util.Scanner;

/**
 *
 * @author betbp
 */
public class FormulaManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

    System.out.println("Enter content for new cell");
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
    }catch(SpellException e){
      System.out.println(e.getMessage());
    }
  }  
}
