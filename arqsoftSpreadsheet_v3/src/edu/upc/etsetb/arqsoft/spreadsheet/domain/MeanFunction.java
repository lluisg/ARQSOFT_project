/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domain;

import java.util.List;

/**
 *
 * @author betbp
 */
public class MeanFunction extends Function{

    @Override
    public float compute(List<Float> values) {
        float res = 0;
        for (Float f : values){
            res += f;
        }
        return res/values.size();
    }
}
