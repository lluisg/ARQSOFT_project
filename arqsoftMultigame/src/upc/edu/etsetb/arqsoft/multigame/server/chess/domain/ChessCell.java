/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.chess.domain;

import upc.edu.etsetb.arqsoft.multigame.server.domain.Cell;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Piece;

/**
 *
 * @author betbp
 */
public class ChessCell implements Cell{
    private ChessPiece piece;
    
    public ChessCell(){
        piece = null;
    }

    @Override
    public void setPiece(Piece piece) {
        this.piece = (ChessPiece)piece;
    }
    
    
}
