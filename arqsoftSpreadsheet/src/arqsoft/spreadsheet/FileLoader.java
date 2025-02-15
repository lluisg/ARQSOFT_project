/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author betbp
 */
public class FileLoader {
    
    public List<String> readLocalFile(String path) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        List<String> lineList = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lineList.add(line);
        }
        br.close();
        return lineList;
    }
    
    public List<String> readInternetFile(String path) throws MalformedURLException, IOException{
        URL url = new URL(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        List<String> lineList = new LinkedList<>();
        String line;
        while((line = br.readLine()) != null){
            lineList.add(line);
        }
        br.close();
        return lineList;
    }
}
