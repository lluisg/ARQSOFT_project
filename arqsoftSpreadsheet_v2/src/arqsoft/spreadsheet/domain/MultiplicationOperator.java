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
public class MultiplicationOperator extends Operator{

    @Override
    public float operate(float f1, float f2) {
        return f1*f2;
    }   
}
