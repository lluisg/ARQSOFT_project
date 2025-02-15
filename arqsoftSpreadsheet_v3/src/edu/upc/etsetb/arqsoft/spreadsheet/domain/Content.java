/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domain;

/**
 *
 * @author betbp
 */
public abstract class Content {
    String content;
    
    public Content(String content){
        this.content = content;
    }
    
    public abstract String getRepresentation();
    
    public String getContent(){
        return content;
    } 
}
