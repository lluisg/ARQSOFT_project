/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

/**
 *
 * @author betbp
 */
public interface FormulaElement {
    
    public void accept(Visitor v) throws NaNException, TextValueException;
    
}
