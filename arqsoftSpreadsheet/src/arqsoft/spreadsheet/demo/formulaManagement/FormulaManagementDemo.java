/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.demo.formulaManagement;

import arqsoft.spreadsheet.CancelException;
import arqsoft.spreadsheet.ParserException;
import arqsoft.spreadsheet.SyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author betbp
 */
public class FormulaManagementDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CancelException {
        Scanner console = new Scanner(System.in);

        System.out.println("Enter content for new cell");
        //String form = console.nextLine();
        String form;
        //form = "=SIN(x;A2:A3)*(1+var_12)*(AZ2+3.15)";
        //form = "=MAX(a2:b5)";
        //form = "325.46";
        //form = "hello worlds";
        //form = "=g2a"; //detecta un CellOperand
        //form = "=a2:a3:a5";
        //form = "=sum(a2:a3; 324; 324.36)*A2";
        //form = "=*2";
        //form = "=max1;*";
        //form = "=(max(2))";
        //form = "";
        //form = "=(5*4+3*2)-1";
        //form = "=sum(a2:b5;3*(4+5);2)+5/a3";
        //form = "=sum(a4;max(a5:b3))";
        //form = "=(a3)3";
        form = "=max(a3;";
        
        
        System.out.println("Entered: "+form);

        SpreadManagerDemo sprMng = new SpreadManagerDemo();

            try {
                sprMng.evaluateContent(form);
            } catch (ParserException ex) {
                Logger.getLogger(FormulaManagementDemo.class.getName()).log(Level.SEVERE, null, ex);
            }

    }  
}
