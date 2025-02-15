/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

/**
 *
 * @author betbp
 */
public class Numerical extends Content{
    private float value;

    public Numerical(String value){
        super(value);
        this.value = Float.parseFloat(value);
    }

    @Override
    public String getRepresentation() {
        return content;
    }
    
    public float getValue(){
        return value;
    }

    
}
