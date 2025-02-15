package upc.edu.etsetb.softarch.multigame.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lluis
 */
public class UnknownFactoryException extends Exception {

    /**
     * Creates a new instance of <code>UnknownFactoryException</code> without
     * detail message.
     */
    public UnknownFactoryException() {
    }

    /**
     * Constructs an instance of <code>UnknownFactoryException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnknownFactoryException(String msg) {
        super(msg);
    }
}
