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
public class TokenException extends Exception {

    /**
     * Creates a new instance of <code>TokenException</code> without detail
     * message.
     */
    public TokenException() {
    }

    /**
     * Constructs an instance of <code>TokenException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public TokenException(String msg) {
        super(msg);
    }
}
