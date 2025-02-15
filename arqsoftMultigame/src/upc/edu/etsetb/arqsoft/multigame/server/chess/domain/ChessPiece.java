/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.chess.domain;

import upc.edu.etsetb.arqsoft.multigame.server.domain.Piece;

/**
 *
 * @author betbp
 */
public class ChessPiece implements Piece{
    
    public static enum Type{
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING;
    }
    
    private ChessCoordinate currentCoordinate;
    protected ChessPieceColor color;
    private Type type;
    
    public ChessPiece (ChessPieceSpec spec){
        this.color = spec.getColor();
    }
    
    public void setColor (ChessPieceColor color){
        this.color = color;
    }
    
    public ChessPieceColor getPieceColor(){
        return this.color;
    }
    
    public ChessCoordinate getCoordinate(){
        return currentCoordinate;
    }
    
    
}
