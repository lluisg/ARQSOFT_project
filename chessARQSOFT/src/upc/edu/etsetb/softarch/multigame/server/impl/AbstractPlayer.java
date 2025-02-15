/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.impl;

import java.util.*;
import upc.edu.etsetb.softarch.multigame.server.GameFactory;
import upc.edu.etsetb.softarch.multigame.server.domain.*;
import upc.edu.etsetb.softarch.utilities.TupleOf2;

/**
 *
 * @author Lluis
 */
public abstract class AbstractPlayer implements Player {
    @Override
    public void createAndPutPiecesOnBoard(Board board, GameFactory factory) throws PieceException, BoardException{
        
        List<TupleOf2<PieceSpec, Coordinate>> list_tuples = getPiecesCoordinates(); //first is piecespec and second is coord
        
        for(TupleOf2<PieceSpec, Coordinate> t: list_tuples){
            Piece piece = factory.createPiece(t.getFirst());
            board.setPieceInCell(piece, t.getSecond());
        }
    }
}
