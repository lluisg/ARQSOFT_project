/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author betbp
 */
public class FileSaver {
    
    public void saveS2VFile(String path, List<String> spreadsheet) throws FileNotFoundException, IOException{ 
        PrintWriter out = new PrintWriter(new File(path));
        for (String line : spreadsheet){
            out.println(line);
        }
        out.flush();
        out.close();
    }
}
