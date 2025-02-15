/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;


import arqsoft.spreadsheet.Visitor;

/**
 *
 * @author betbp
 */
public abstract class Operator extends FormulaComponent{
    
    public abstract float operate(float f1, float f2);
    
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
}
