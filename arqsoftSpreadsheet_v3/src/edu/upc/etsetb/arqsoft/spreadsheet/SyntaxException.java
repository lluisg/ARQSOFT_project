/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

/**
 *
 * @author Lluis
 */
public class SyntaxException extends Exception {

    public SyntaxException() {
    }

    /**
     * Constructs an instance of <code>SpellException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */

    public SyntaxException(String msg) {
        super(msg);
    }
}