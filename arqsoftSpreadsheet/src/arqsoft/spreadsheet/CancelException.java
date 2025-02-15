/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

/**
 *
 * @author Lluis
 */
public class CancelException extends Exception {

    /**
     * Creates a new instance of <code>CancelException</code> without detail
     * message.
     */
    public CancelException() {
    }

    /**
     * Constructs an instance of <code>CancelException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CancelException(String msg) {
        super(msg);
    }
}
