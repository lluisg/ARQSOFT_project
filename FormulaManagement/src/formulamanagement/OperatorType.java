package formulamanagement;

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
    ADDITION("+"),
    DIFFERENCE("-"),
    MULTIPLICATION("*"),
    DIVISION("/");
    
    private final String symbol;
    
    OperatorType(String s){
        this.symbol = s;
    }
    
    public String getSymbol(){
        return symbol;
    }
    
}
