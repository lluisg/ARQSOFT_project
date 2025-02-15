/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.domain;

import upc.edu.etsetb.softarch.multigame.server.chess.NoPathFreeException;
import upc.edu.etsetb.softarch.multigame.server.chess.NoPieceMovementException;
import upc.edu.etsetb.softarch.multigame.server.domain.Board;

/**
 *
 * @author Lluis
 */
public class Bishop extends ChessPiece{
    protected ChessPieceColor color;
    
    public Bishop(ChessPieceSpec spec) {
        super(spec);
    }

    @Override
    public void isPieceMovement(int rO, int cO, int rD, int cD) throws NoPieceMovementException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void isPathFree(int rO, int cO, int rD, int cD, ChessBoard board) throws NoPathFreeException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void movePiece(int rO, int cO, int rD, int cD, Board board) throws NoPieceMovementException, NoPathFreeException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
