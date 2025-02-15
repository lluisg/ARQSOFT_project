/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author betbp
 */
public class Formula extends Content{
    private float result;
    private List<FormulaComponent> components;
    
    public Formula(String content){
        super(content);
        this.result = 0;
    }

    @Override
    public String getRepresentation() {
        return String.valueOf(result);
    }
    
    public void setFormulaComponents(List<FormulaComponent> lc){
        this.components = lc;
    }
    
    public List<FormulaComponent> getFormulaComponents(){
        return this.components;
    }

    public void setResult(float res){
        this.result = res;
    }
    
    public float getResult(){
        return result;
    }
    
    public List<Coordinate> getUsedCoordinates(){
        List<Coordinate> coor = new LinkedList<>();
        for (FormulaComponent fc : components){
            if(fc instanceof CellOperand){
                coor.add(((CellOperand) fc).getCoordinate());
            }else if(fc instanceof RangeOperand){
                coor.addAll(((RangeOperand)fc).getCellsInRange());
            }else if(fc instanceof Function){
                coor.addAll(((Function)fc).getUsedCoordinates());
            }
        }
        List<Coordinate> coor_nodupl = removeDuplicates(coor);
        return coor_nodupl;
    }
    
    private List<Coordinate> removeDuplicates(List<Coordinate> list){
        List<Coordinate> list2 = new LinkedList<Coordinate>();
        boolean duplicate = false;
        
        for(Coordinate coord : list){
            duplicate = false;
            for(Coordinate coord2 : list2){
                if(coord.equals(coord2)){
                    duplicate = true;
                }
            }
            if(duplicate == false){
                list2.add(coord);                
            }
        }
        
        return list2;
    }

}
