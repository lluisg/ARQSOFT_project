/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import edu.upc.etsetb.arqsoft.spreadsheet.domain.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.CellOperand;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.Function;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.NumberOperand;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.domain.RangeOperand;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author betbp
 */
public class FormulaVisitor implements Visitor{
    
    protected Stack<Float> operands;
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
    public void visit(Operator operator) throws TextValueException{
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
    
    protected Float getValueFromCell(Cell c) throws NaNException, TextValueException{
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
            //super.operands = new Stack<>();
        }
        
        @Override
        public float getResult(){
            List<Float> ff = new LinkedList<>();
            while (this.operands.size() > 0){
                Float f = operands.pop();
                if (f != null){
                    ff.add(f);
                }
            }
            return function.compute(ff);
        }
        
        @Override
        public void visit(Operator operator) throws TextValueException{
            Float f2 = this.getValueForOperator();
            Float f1 = this.getValueForOperator();
            float f = operator.operate(f1, f2);
            operands.push(f);
        }
        
        private Float getValueForOperator() throws TextValueException{
            Float f = operands.pop();
            if (f == null){
               return new Float(0);
            }
            return f;
        }
        
        @Override
        protected Float getValueFromCell(Cell c) throws NaNException, TextValueException{
            if(null == c.getState()){
                throw new NaNException();
            }else switch (c.getState()) {
                case EMPTY:
                    return null;
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
    }
    
    

}
