/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.testJuanca;

import arqsoft.spreadsheet.ParserException;
import java.util.*;
import arqsoft.spreadsheet.StringParser;
import arqsoft.spreadsheet.Token;

/**
 *
 * @author JuanCarlos
 */
public class TokenizerIntTest {

    public static void main(String[] args) throws BadTokenException {
        String[] in = {
            "/A", "+A", "+12+13,2+SUMA(A27:A45", "+12+13,2+SUMA(B23:C47/",
            "+12+13,2+SUMA(/A1+B2*MEAN()B12+CA112",
            "+12 + 13,2+(SUMA(A1:B5;C7;SUMA(C8:C20))+B12)+CA112",
            "+12 + 13,2+(SUMA(A1:B5;C7;SUMA(C8:C20))  ?  +B12)+CA112",
            "(A1:A2)", "()",
            "(A1+A2)+", "(A1+A2)+B",
            "A1A2:A3", "A:A1", ":A1", "A1:A2A3", "A1:A", "A1:"};
        for (int i = 0; i < in.length; i++) {
            List<Token> result = new ArrayList<Token>();
            System.out.println("sentence: "+in[i]);
            StringParser tokenizer = null;
            try {
                //SpreadFabric factory = SpreadFabric.getInstance("DEFAULT");
                tokenizer = new StringParser();
                List<Token> lt = tokenizer.parse(in[i]);
                
                for(Token t : lt) {
                    System.out.printf("%s, %s\n",t.getContent(), t.getType());
                }
            /*} catch (UnkownFactoryException ex) {
                System.out.println("ERROR WHEN TRYING TO INSTANTIATE THE FACTORY. DETAILS: " + ex.getMessage());
                System.out.println("ENDING EXECUTION...");
                System.exit(-1);
            */

            } catch (ParserException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
        }
        
    }
    
    

}
