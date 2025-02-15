/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import edu.upc.etsetb.arqsoft.spreadsheet.domain.Coordinate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author betbp
 */
public class FileParser {
    
    public Map<Coordinate,String> parseFile(List<String> file){
        Map<Coordinate,String> map = new HashMap<>();
        
        for (int i=0 ; i<file.size() ; i++){
            String[] cells = file.get(i).split(";");
            for (int j=0 ; j<cells.length ; j++){
                String c = cells[j];
                if(!c.isEmpty()){
                    if(c.startsWith("=")){
                        map.put(new Coordinate(i,j),c.replace(",", ";"));
                    }else{
                        map.put(new Coordinate(i,j), c);
                    }
                }
            }
        }
        return map;
    }

    public List<String> parseSpreadsheet(Map<Coordinate,String> map){
        List <String> file= new LinkedList<>();
        
        Coordinate minSize = new Coordinate(0,0);
        for (Coordinate c : map.keySet()){
            minSize = minSize.getMinSize(c);
        }
        
        for (int i=0 ;  i<minSize.getRow()+1 ; i++){
            String line = "";
            for (int j=0 ; j<minSize.getCol()+1 ; j++){
                String c = map.get(new Coordinate(i,j));
                if(c == null){
                    c = "";
                }else if(c.startsWith("=")){
                    c = c.replace(";", ",");
                }
                line += c + ";";
            }
            file.add(line.substring(0,line.length()-1));
        }
        return file;
    }

}
