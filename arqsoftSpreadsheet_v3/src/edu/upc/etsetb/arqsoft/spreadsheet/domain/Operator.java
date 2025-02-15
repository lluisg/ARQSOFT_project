/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domain;


import edu.upc.etsetb.arqsoft.spreadsheet.TextValueException;
import edu.upc.etsetb.arqsoft.spreadsheet.Visitor;

/**
 *
 * @author betbp
 */
public abstract class Operator extends FormulaComponent{
    
    public abstract float operate(float f1, float f2);
    
    @Override
    public void accept(Visitor v) throws TextValueException{
        v.visit(this);
    }
}
