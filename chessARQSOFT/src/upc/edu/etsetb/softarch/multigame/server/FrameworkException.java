/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server;

/**
 *
 * @author Lluis
 */
public class FrameworkException extends Exception {

    /**
     * Creates a new instance of <code>FrameworkException</code> without detail
     * message.
     */
    public FrameworkException() {
    }

    /**
     * Constructs an instance of <code>FrameworkException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FrameworkException(String msg) {
        super(msg);
    }
}
