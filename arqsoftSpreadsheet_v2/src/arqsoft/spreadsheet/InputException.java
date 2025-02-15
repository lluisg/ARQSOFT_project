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
public class InputException extends Exception {
    String msg;
    
    public InputException(String msg) {
        this.msg = msg;
    }
    
    public String getMsg(){
        return this.msg;
    }
    
}
