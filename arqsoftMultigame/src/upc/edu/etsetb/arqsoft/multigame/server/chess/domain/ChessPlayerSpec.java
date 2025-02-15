/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.chess.domain;

import upc.edu.etsetb.arqsoft.multigame.server.domain.PlayerSpec;

/**
 *
 * @author betbp
 */
public class ChessPlayerSpec implements PlayerSpec{
    private ChessPieceColor color;
    
    public ChessPlayerSpec(ChessPieceColor color){
        this.color = color;
    }
    
    public ChessPieceColor getColor(){
        return this.color;
    }
    
}
