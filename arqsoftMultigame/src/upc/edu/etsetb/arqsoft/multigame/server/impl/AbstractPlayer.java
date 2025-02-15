/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.impl;

import java.util.List;
import upc.edu.etsetb.arqsoft.multigame.server.GameFactory;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Board;
import upc.edu.etsetb.arqsoft.multigame.server.domain.BoardException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Coordinate;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Piece;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceSpec;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Player;
import upc.edu.etsetb.arqsoft.utilities.TupleOf2;

/**
 *
 * @author betbp
 */
public abstract class AbstractPlayer implements Player{
    @Override
    public void createAndPutPiecesOnBoard(Board board, GameFactory factory) throws PieceException, BoardException{
        List<TupleOf2<PieceSpec, Coordinate>> list_pieces = getPiecesCoordinates();
        
        for (TupleOf2<PieceSpec,Coordinate> tuple : list_pieces ){
            Piece piece = factory.createPiece(tuple.getFirst());
            board.setPieceInCell(piece, tuple.getSecond());
        }
    } 
}
