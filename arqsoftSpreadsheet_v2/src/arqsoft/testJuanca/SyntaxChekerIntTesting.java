/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.testJuanca;

/**
 *
 * @author Lluis
 */
import arqsoft.spreadsheet.ParserException;
import arqsoft.spreadsheet.StringManager;
import arqsoft.spreadsheet.StringParser;
import arqsoft.spreadsheet.SyntaxException;
import arqsoft.spreadsheet.Token;
import java.util.*;
/**
 *
 * @author JuanCarlos
 */
public class SyntaxChekerIntTesting {

    public static void main(String[] args) {
        String[] in = {
            "=13.2",
            "=/A1",
            "=*A1",
            "=)A1",
            "=:A1",
            "=;A1",
            "=.A1",
            "=+SUM(MAX(MIN(A1;A2;A4:A6);B1:B7);C3)",
            "=+A",
            "=+12+13.2+SUM(A27:A45",
            "=+12+13.2+SUM(B23:C47/",
            "=+12+13.2+SUM(/A1+B2*MAX()B12+CA112",
            "=+12 + 13.2+(SUM(A1:B5;C7;SUM(C8:C20)) +B12)+CA112",
            "=+12 + 13.2+(SUM(A1:B5;C7;SUM(C8:C20))  ?  +B12)+CA112",
            "=(A1:A2)",
            "=()",
            "=(A1+A2)+",
            "=(A1+A2)+B",
            "=A1A2:A3",
            "=A:A1",
            "=:A1",
            "=A1:A2A3",
            "=A1:A",
            "=A1:"

        };
        for (String input : in) {
            try {
                StringManager strM = new StringManager();
                StringParser strP = new StringParser();
                System.out.println("\n\n" + input);
                List<Token> lt = new ArrayList<Token>();
                try {
                //SpreadFabric factory = SpreadFabric.getInstance("DEFAULT");
                     lt = strP.parse(input);

                } catch (ParserException ex) {
                    System.out.println(ex.getMessage());
                }
                
                for(Token t : lt) {
                    System.out.printf("%s, %s\n",t.getContent(), t.getType());
                }

                strM.checkSyntax(lt);
                               
                System.out.println("Checking OK.");
            } catch (SyntaxException ex) {
                System.out.println("An error has occurred while parsing. Details follow. " + ex.getMessage());
            }

        }
    }

}
