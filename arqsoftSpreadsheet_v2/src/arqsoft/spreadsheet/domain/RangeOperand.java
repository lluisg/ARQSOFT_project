/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

import arqsoft.spreadsheet.NaNException;
import arqsoft.spreadsheet.TextValueException;
import arqsoft.spreadsheet.Visitor;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author betbp
 */
public class RangeOperand extends Operand{
    private Coordinate firstCell;
    private Coordinate lastCell;

    
    public RangeOperand(Coordinate first, Coordinate last){
        super();
        firstCell = first;
        lastCell = last;
    }

    @Override
    public void accept(Visitor v) throws NaNException, TextValueException{
        v.visit(this);
    }
    
    public List<Coordinate> getCellsInRange(){
        List<Coordinate> coor = new LinkedList<>();
        for(int i = firstCell.getRow() ; i<lastCell.getRow()+1 ;  i++){
            for (int j = firstCell.getCol() ; j<lastCell.getCol()+1 ; j++){
                coor.add(new Coordinate(i,j));
            }
        }
        return coor;
    }
}
