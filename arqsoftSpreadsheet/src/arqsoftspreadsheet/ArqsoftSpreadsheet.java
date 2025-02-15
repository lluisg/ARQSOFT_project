/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoftspreadsheet;

import arqsoft.spreadsheet.InputException;
import arqsoft.spreadsheet.Manager;
import arqsoft.spreadsheet.ParserException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author betbp
 */
public class ArqsoftSpreadsheet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Manager mng = new Manager();
            mng.Init();            
        } catch (ParserException ex) {
            Logger.getLogger(ArqsoftSpreadsheet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArqsoftSpreadsheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
