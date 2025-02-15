/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.domain;

import upc.edu.etsetb.softarch.multigame.server.domain.*;
import upc.edu.etsetb.softarch.multigame.server.chess.*;
/**
 *
 * @author Lluis
 */
public abstract class ChessPiece implements Piece{
    
    public static enum Type {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING;
    }
    protected ChessPieceColor color;
    
    public ChessPiece(ChessPieceSpec spec){
        this.color = spec.getColor();
    }
            
    public void setColor(ChessPieceColor color){
        this.color = color;
    }
    public ChessPieceColor getColor() {
        return this.color;
    }
    
    public abstract void isPieceMovement(int rO, int cO, int rD, int cD) throws NoPieceMovementException ;
    public abstract void isPathFree(int rO, int cO, int rD, int cD, ChessBoard board) throws NoPathFreeException ;
    public abstract void movePiece(int rO, int cO, int rD, int cD, Board board) throws NoPieceMovementException, NoPathFreeException;
    public void canReachDestination(int rO, int cO, int rD, int cD, ChessBoard board) throws NoPieceMovementException, NoPathFreeException{
        isPieceMovement(rO, cO, rD, cD);
        isPathFree(rO, cO, rD, cD, board);
    }
}
