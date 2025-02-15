package upc.edu.etsetb.softarch.multigame.server.chess;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lluis
 */
public class NoPathFreeException extends Exception {

    /**
     * Creates a new instance of <code>NoPathFreeException</code> without detail
     * message.
     */
    public NoPathFreeException() {
    }

    /**
     * Constructs an instance of <code>NoPathFreeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoPathFreeException(String msg) {
        super(msg);
    }
}
