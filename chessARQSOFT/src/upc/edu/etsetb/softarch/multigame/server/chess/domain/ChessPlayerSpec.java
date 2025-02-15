/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.domain;

import upc.edu.etsetb.softarch.multigame.server.domain.*;

/**
 *
 * @author Lluis
 */
public class ChessPlayerSpec implements PlayerSpec{

    public static ChessPlayerSpec getInstance(ChessPieceColor chessPieceColor) {
        return new ChessPlayerSpec(chessPieceColor);
    }
    ChessPieceColor color;
    public ChessPlayerSpec(ChessPieceColor piecesColor){
        this.color = piecesColor;
    }
    
    public ChessPieceColor getColor(){
        return this.color;
    }
}
