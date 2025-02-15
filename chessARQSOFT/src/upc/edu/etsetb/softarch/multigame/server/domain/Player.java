/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.domain;

import java.util.*;
import upc.edu.etsetb.softarch.multigame.server.GameFactory;
import upc.edu.etsetb.softarch.utilities.TupleOf2;

/**
 *
 * @author Lluis
 */
public interface Player {
    public void playTurn();
    public void createAndPutPiecesOnBoard(Board board, GameFactory factory) throws PieceException, BoardException;
    public List<TupleOf2<PieceSpec, Coordinate>> getPiecesCoordinates();
}
