/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author betbp
 */
public class MinFunction extends Function{

    @Override
    public float compute(List<Float> values) {
        return Collections.min(values);
    }

}
