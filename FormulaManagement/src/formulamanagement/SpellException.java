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
public class SpellException extends Exception {

    /**
     * Creates a new instance of <code>SpellException</code> without detail
     * message.
     */
    public SpellException() {
    }

    /**
     * Constructs an instance of <code>SpellException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SpellException(String msg) {
        super(msg);
    }
}