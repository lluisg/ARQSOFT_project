/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import arqsoft.spreadsheet.domain.Cell;
import arqsoft.spreadsheet.domain.CellOperand;
import arqsoft.spreadsheet.domain.Coordinate;
import arqsoft.spreadsheet.domain.FormulaComponent;
import arqsoft.spreadsheet.domain.Function;
import arqsoft.spreadsheet.domain.NumberOperand;
import arqsoft.spreadsheet.domain.Operator;
import arqsoft.spreadsheet.domain.RangeOperand;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author betbp
 */
public class FormulaVisitor implements Visitor{
    
    private Stack<Float> operands;
    private SpreadManager spreadMng;
    
    public FormulaVisitor (SpreadManager sm){
        spreadMng = sm;
        operands = new Stack<>();
    }
    
    public float getResult(){
        if (operands.size() == 1){
            return operands.pop();
        }
        return Float.NaN;
    }

    @Override
    public void visit(Operator operator){
        float f2 = operands.pop();
        float f = operator.operate(operands.pop(), f2);
        operands.push(f);
    }
    
    @Override
    public void visit(NumberOperand number) {
        operands.push(number.getValue());
    }

    @Override
    public void visit(CellOperand cell) throws NaNException, TextValueException{
        Cell c = spreadMng.getCell(cell.getCoordinate());
        operands.push(this.getValueFromCell(c));
    }

    @Override
    public void visit(Function function) throws NaNException, TextValueException{
        FunctionVisitor vis = new FunctionVisitor(this.spreadMng,function);
        for (FormulaComponent fc :  function.getElements()){
            fc.accept(vis);
        }
        operands.push(vis.getResult());
    }

    @Override
    public void visit(RangeOperand range) throws NaNException,TextValueException {
        List<Coordinate> coor = range.getCellsInRange();
        for (Cell c: spreadMng.getCells(coor)){
            operands.push(this.getValueFromCell(c));
        }
    }
    
    public Float getValueFromCell(Cell c) throws NaNException, TextValueException{
        if(null == c.getState()){
            throw new NaNException();
        }else switch (c.getState()) {
            case EMPTY:
                return new Float(0);
            case NUMBER:
                return c.getValue();
            case TEXT:
                throw new TextValueException();
            case FORMULA:
                return c.getValue();
            default:
                throw new NaNException();
        }
    }
    
    private class FunctionVisitor extends FormulaVisitor{
        
        private Function function;
        
        public FunctionVisitor(SpreadManager sm, Function fun) {
            super(sm);
            function = fun;
        }
        
        @Override
        public float getResult(){
            return function.compute(super.operands);
        }
    }

}
