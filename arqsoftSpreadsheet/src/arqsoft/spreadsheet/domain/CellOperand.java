/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

import arqsoft.spreadsheet.NaNException;
import arqsoft.spreadsheet.TextValueException;
import arqsoft.spreadsheet.Visitor;

/**
 *
 * @author betbp
 */
public class CellOperand extends Operand{
    private Coordinate coordinate;

    public CellOperand(String s) {
        coordinate = new Coordinate(s);
    }
    
    public Coordinate getCoordinate(){
        return coordinate;
    }
    
    @Override
    public void accept(Visitor v) throws NaNException, TextValueException{
        v.visit(this);
    }
}
