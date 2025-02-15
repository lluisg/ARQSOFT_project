/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.domain;

/**
 *
 * @author betbp
 */
public class CellException extends Exception {

    /**
     * Creates a new instance of <code>CellException</code> without detail
     * message.
     */
    public CellException() {
    }

    /**
     * Constructs an instance of <code>CellException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CellException(String msg) {
        super(msg);
    }
}
