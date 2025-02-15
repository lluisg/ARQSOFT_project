/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.domain;

/**
 *
 * @author Lluis
 */
public class PlayerException extends Exception {

    /**
     * Creates a new instance of <code>PlayerException</code> without detail
     * message.
     */
    public PlayerException() {
    }

    /**
     * Constructs an instance of <code>PlayerException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PlayerException(String msg) {
        super(msg);
    }
}
