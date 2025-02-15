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
public class ThirdPartyException extends Exception {

    /**
     * Creates a new instance of <code>ThirdPartyException</code> without detail
     * message.
     */
    public ThirdPartyException() {
    }

    /**
     * Constructs an instance of <code>ThirdPartyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ThirdPartyException(String msg) {
        super(msg);
    }
}
