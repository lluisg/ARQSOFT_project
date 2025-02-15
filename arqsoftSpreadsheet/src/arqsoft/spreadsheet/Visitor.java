/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import arqsoft.spreadsheet.domain.CellOperand;
import arqsoft.spreadsheet.domain.Function;
import arqsoft.spreadsheet.domain.NumberOperand;
import arqsoft.spreadsheet.domain.Operator;
import arqsoft.spreadsheet.domain.RangeOperand;

/**
 *
 * @author betbp
 */
public interface Visitor {
    
    public void visit(Operator operator); 
    public void visit(NumberOperand number);
    public void visit(CellOperand cell) throws NaNException, TextValueException;
    public void visit(Function function) throws NaNException, TextValueException;
    public void visit(RangeOperand range) throws NaNException, TextValueException;

}
