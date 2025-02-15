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
public class PieceException extends Exception {

    /**
     * Creates a new instance of <code>PieceException</code> without detail
     * message.
     */
    public PieceException() {
    }

    /**
     * Constructs an instance of <code>PieceException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PieceException(String msg) {
        super(msg);
    }
}
