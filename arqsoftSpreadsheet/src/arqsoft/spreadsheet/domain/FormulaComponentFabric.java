/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

import arqsoft.spreadsheet.Token;





/**
 *
 * @author betbp
 */
public class FormulaComponentFabric {
    
    public FormulaComponent createFormulaComponent(Token t){

        switch(t.getType()){
            case FUNCTION:
                return newFunction(t.getContent());
                
            case OPERATOR:
                return newOperator(t.getContent());
                
            case CELL:
                return new CellOperand(t.getContent());
                
            case CELL_RANGE:
                String[] cells = t.getContent().split(":");
                Coordinate c1 = new Coordinate(cells[0]);
                Coordinate c2 = new Coordinate(cells[1]);
                if(c2.isBefore(c1)){
                    return new RangeOperand(c2,c1);
                }
                return new RangeOperand(c1,c2);    
                
            case FLOAT:
                return new NumberOperand(t.getContent());
                
            default:
                return null;
        }
        
    }
    
    private Function newFunction(String s){
        if(s.equalsIgnoreCase(FunctionType.MAX.getString())){
            return new MaxFunction();  
        }else if(s.equalsIgnoreCase(FunctionType.MIN.getString())){
            return new MinFunction();  
        }else if(s.equalsIgnoreCase(FunctionType.MEAN.getString())){
            return new MeanFunction();  
        }else if(s.equalsIgnoreCase(FunctionType.SUM.getString())){
            return new SumFunction();  
        }else{
            return null;
        } 
    }
    
    private Operator newOperator(String s){
        if(s.equalsIgnoreCase(OperatorType.ADDITION.getSymbol())){
            return new SumOperator();  
        }else if(s.equalsIgnoreCase(OperatorType.SUBSTRACTION.getSymbol())){
            return new SubstractionOperator();  
        }else if(s.equalsIgnoreCase(OperatorType.MULTIPLICATION.getSymbol())){
            return new MultiplicationOperator();  
        }else if(s.equalsIgnoreCase(OperatorType.DIVISION.getSymbol())){
            return new DivisionOperator();  
        }else{
            return null;
        }
    }
    
    public Operator createOperator(OperatorType ot){
        switch (ot){
            case ADDITION:
                return new SumOperator();
            case SUBSTRACTION:
                return new SubstractionOperator(); 
            case MULTIPLICATION:
                return new MultiplicationOperator();  
            case DIVISION:
                return new DivisionOperator();
            default:
                return null;
        }
    }
    
    public OperatorType string2operator(String s){
        if (s.equals(OperatorType.ADDITION.getSymbol())){
            return OperatorType.ADDITION;
        }else if (s.equals(OperatorType.SUBSTRACTION.getSymbol())){
            return OperatorType.SUBSTRACTION;
        }else if (s.equals(OperatorType.MULTIPLICATION.getSymbol())){
            return OperatorType.MULTIPLICATION;
        }else if (s.equals(OperatorType.DIVISION.getSymbol())){
            return OperatorType.DIVISION;
        }else if (s.equals(OperatorType.OP_PARENTHESIS.getSymbol())){
            return OperatorType.OP_PARENTHESIS;
        }else  if (s.equals(OperatorType.CL_PARENTHESIS.getSymbol())){
            return OperatorType.CL_PARENTHESIS;
        }else{
            return null;
        }
    }
}
