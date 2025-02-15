package edu.upc.etsetb.arqsoft.spreadsheet.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author betbp
 */
public enum OperatorType {
    OP_PARENTHESIS("(",1),
    CL_PARENTHESIS(")",1),
    ADDITION("+",4),
    SUBSTRACTION("-",4),
    MULTIPLICATION("*",3),
    DIVISION("/",3);

    private final String symbol;
    private final int precendence; //defined according to https://en.wikipedia.org/wiki/Order_of_operations#Programming_languages
    
    OperatorType(String s, int i){
        this.symbol = s;
        this.precendence = i;
    }
    
    public String getSymbol(){
        return symbol;
    }
    
    public int getPrecedence(){
        return precendence;
    }
}
