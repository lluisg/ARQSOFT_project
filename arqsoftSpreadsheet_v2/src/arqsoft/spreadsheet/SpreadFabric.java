/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import arqsoft.spreadsheet.domain.Cell;
import arqsoft.spreadsheet.domain.Content;
import arqsoft.spreadsheet.domain.Coordinate;
import arqsoft.spreadsheet.domain.Formula;
import arqsoft.spreadsheet.domain.Numerical;
import arqsoft.spreadsheet.domain.Spreadsheet;
import arqsoft.spreadsheet.domain.Text;
import java.util.*;

/**
 *
 * @author betbp
 */
public class SpreadFabric {
    private SpreadManager spreadMng;
    
    public SpreadFabric(SpreadManager sm){
        spreadMng = sm;
    }
    
    public Spreadsheet createEmptySpreadsheet(int row, int col){
        Spreadsheet spread = new Spreadsheet(row, col);
        return spread;
    }
    
    public Spreadsheet spreadsheetFromFile(Map<Coordinate,String> map) throws CancelException, ParserException, SyntaxException{
        Coordinate minSize = new Coordinate(0,0);
        for (Coordinate c : map.keySet()){
            minSize = minSize.getMinSize(c);
        }
        Spreadsheet spread = new Spreadsheet(minSize.getRow()+1,minSize.getCol()+1);
        for (Coordinate coor : map.keySet()){
            Cell cell = this.createEmptyCell();
            spreadMng.editContent(cell, map.get(coor));
            spread.addCell(coor, cell);
        }
        return spread;
    }
    
    public Cell createEmptyCell(){
        return new Cell();
    }
    
    public Content createContent(Token.TokenType t,String s){
        switch (t){
            case TEXT:
                return new Text(s);
            case FLOAT:
                return new Numerical(s);
            case EQUAL:
                return new Formula(s);
            default:
                return null;
        }        
    }   
}
