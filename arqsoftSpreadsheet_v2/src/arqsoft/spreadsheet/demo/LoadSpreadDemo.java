/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.demo;

import arqsoft.spreadsheet.FileException;
import arqsoft.spreadsheet.Manager;
import arqsoft.spreadsheet.SpreadManager;
import java.io.IOException;

/**
 *
 * @author betbp
 */
public class LoadSpreadDemo {

    
    public static void main(String[] args) throws IOException, FileException {
        ManagerFileDemo mng = new ManagerFileDemo();
                
        String path = "/home/betbp/Documents/spread1.c2v";
        
        mng.loadS2VFile(path);
        int i = 0;
        
        //try edit cell
        
    }
    
}
