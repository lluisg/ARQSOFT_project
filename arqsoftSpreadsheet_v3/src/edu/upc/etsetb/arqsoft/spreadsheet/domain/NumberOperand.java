/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domain;

import edu.upc.etsetb.arqsoft.spreadsheet.NaNException;
import edu.upc.etsetb.arqsoft.spreadsheet.Visitor;

/**
 *
 * @author betbp
 */
public class NumberOperand extends Operand{
    private float value;

    public NumberOperand(String s) {
        value = Float.parseFloat(s);
    }
    
    public float getValue(){
        return value;
    }
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    
}
