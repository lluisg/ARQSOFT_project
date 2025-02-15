/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulamanagement;

/**
 *
 * @author Lluis
 */
public class FormulaParseException extends Exception {

    /**
     * Creates a new instance of <code>FormulaParseException</code> without
     * detail message.
     */
    public FormulaParseException() {
    }

    /**
     * Constructs an instance of <code>FormulaParseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FormulaParseException(String msg) {
        super(msg);
    }
}
