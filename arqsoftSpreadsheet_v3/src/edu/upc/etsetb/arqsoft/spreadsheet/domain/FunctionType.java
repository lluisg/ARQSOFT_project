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
public enum FunctionType {
    SUM("sum"),
    MAX("max"),
    MIN("min"),
    MEAN("mean");
    
    private final String s;
    
    FunctionType(String s){
        this.s = s;
    }
    
    public String getString(){
        return s;
    }
        
}
