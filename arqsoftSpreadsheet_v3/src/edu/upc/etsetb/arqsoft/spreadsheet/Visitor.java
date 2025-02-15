/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import edu.upc.etsetb.arqsoft.spreadsheet.NaNException;
import edu.upc.etsetb.arqsoft.spreadsheet.TextValueException;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.CellOperand;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.Function;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.NumberOperand;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.RangeOperand;

/**
 *
 * @author betbp
 */
public interface Visitor {
    
    public void visit(Operator operator) throws TextValueException; 
    public void visit(NumberOperand number);
    public void visit(CellOperand cell) throws NaNException, TextValueException;
    public void visit(Function function) throws NaNException, TextValueException;
    public void visit(RangeOperand range) throws NaNException, TextValueException;

}
