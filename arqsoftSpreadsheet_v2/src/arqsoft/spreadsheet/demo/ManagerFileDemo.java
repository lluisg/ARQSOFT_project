/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.demo;

import arqsoft.spreadsheet.FileException;
import arqsoft.spreadsheet.FileManager;
import arqsoft.spreadsheet.NaNException;
import arqsoft.spreadsheet.SpreadManager;
import arqsoft.spreadsheet.StringParser;
import arqsoft.spreadsheet.domain.Coordinate;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author betbp
 */
public class ManagerFileDemo {

    
    
    
    private FileManager fileMng; // create only when user needs it for the fisrt time
    private SpreadManager spreadMng;
    private StringParser strParser;
    private boolean loadedSpread;
    private boolean closeProgram;
    public Integer MAX_SIZE_SPREAD;
    
    public ManagerFileDemo(){
        fileMng = null;
        spreadMng = new SpreadManager();
        loadedSpread = false;
        closeProgram = false;
        strParser = new StringParser();
        MAX_SIZE_SPREAD = 1000;
    }
    
    public void saveS2VFile(String path) throws FileNotFoundException, IOException{
        
        if (fileMng == null){
            fileMng = new FileManager();
        }
        try {
            fileMng.saveFile(path, spreadMng.getContentSpreadsheet());
        } catch (FileException ex) {
            Logger.getLogger(ManagerFileDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //private
    public void loadS2VFile(String path) throws IOException, FileException{
        FileManager.FileSource origin = FileManager.FileSource.LOCAL;
        if (fileMng == null){
            fileMng = new FileManager();
        }
        Map<Coordinate,String> spread_map = fileMng.readFile(path,origin);
        spreadMng.spreadsheetFromFile(spread_map);
    }
   
}

    