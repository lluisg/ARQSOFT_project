/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.domain;

import java.util.List;
import upc.edu.etsetb.arqsoft.multigame.server.GameFactory;
import upc.edu.etsetb.arqsoft.utilities.TupleOf2;

/**
 *
 * @author betbp
 */
public interface Player {
    public void playTurn();
    public void createAndPutPiecesOnBoard(Board board, GameFactory factory)throws PieceException, BoardException;
    public List<TupleOf2<PieceSpec, Coordinate>> getPiecesCoordinates();
    
}
