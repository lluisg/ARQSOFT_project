/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

import arqsoft.spreadsheet.NaNException;
import arqsoft.spreadsheet.TextValueException;
import arqsoft.spreadsheet.Visitor;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author betbp
 */
public abstract class Function extends Operand{
    private List<FormulaComponent> elements;
    
    public abstract float compute(List<Float> values);
    
    public void setElements (List<FormulaComponent> list){
        elements = list;
    }

    public List<FormulaComponent> getElements() {
        return elements;
    }
    
    public List<Coordinate> getUsedCoordinates(){
        List<Coordinate> coor = new LinkedList<>();
        for (FormulaComponent fc : elements){
            if(fc instanceof CellOperand){
                coor.add(((CellOperand) fc).getCoordinate());
            }else if(fc instanceof RangeOperand){
                coor.addAll(((RangeOperand)fc).getCellsInRange());
            }else if(fc instanceof Function){
                coor.addAll(((Function)fc).getUsedCoordinates());
            }
        }
        return coor;
    }
    
    @Override
    public void accept(Visitor v) throws NaNException, TextValueException{
        v.visit(this);
    }
}
