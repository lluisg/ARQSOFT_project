/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import arqsoft.spreadsheet.domain.Formula;
import arqsoft.spreadsheet.domain.FormulaComponent;

/**
 *
 * @author betbp
 */
public class Calculator {
    private static SpreadManager spreadMng;
    
    public Calculator(SpreadManager sm){
        spreadMng = sm;
    }
    
    public static float computeFormula(Formula formula) throws NaNException, TextValueException{
        FormulaVisitor fv = new FormulaVisitor (spreadMng);
        for (FormulaComponent fc : formula.getFormulaComponents()){
            fc.accept(fv);
        }
        return fv.getResult();
    }
}
