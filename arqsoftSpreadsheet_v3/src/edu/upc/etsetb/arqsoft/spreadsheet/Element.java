/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import edu.upc.etsetb.arqsoft.spreadsheet.NaNException;
import edu.upc.etsetb.arqsoft.spreadsheet.TextValueException;

/**
 *
 * @author betbp
 */
public interface Element {
    
    public void accept(Visitor v) throws NaNException, TextValueException;
    
}
