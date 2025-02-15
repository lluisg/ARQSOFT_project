/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domain;

import edu.upc.etsetb.arqsoft.spreadsheet.NaNException;
import edu.upc.etsetb.arqsoft.spreadsheet.TextValueException;
import edu.upc.etsetb.arqsoft.spreadsheet.Visitor;

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
