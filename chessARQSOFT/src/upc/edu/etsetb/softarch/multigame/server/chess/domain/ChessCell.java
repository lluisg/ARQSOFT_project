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
public class ChessCell implements Cell{
    private ChessPiece piece;
    private ChessCoordinate coordinate;
            
    public ChessCell(int r, int c){
        this.coordinate = new ChessCoordinate(r, c);
        this.piece = null;
    }

    @Override
    public void setPiece(Piece piece) {
        this.piece = ((ChessPiece)piece);
    }
    
    public void removePiece(){
        this.piece = null;
    }

    @Override
    public Piece getPiece() {
        return this.piece;
    }
    
}
