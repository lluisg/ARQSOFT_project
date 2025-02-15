/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.chess.domain;

import upc.edu.etsetb.arqsoft.multigame.server.domain.Board;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Cell;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Coordinate;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Piece;

/**
 *
 * @author betbp
 */
public class ChessBoard implements Board{
    private ChessCell[][] cells;
    
    public ChessBoard(){
        cells = new ChessCell[8][8];
        for (int i=0 ; i<8 ; i++){
            for (int j=0 ; j<8 ; j++){
                cells[i][j] = new ChessCell();
            }
            
        }
    }

    @Override
    public Cell getCell(Coordinate coordinate) {
        return cells[((ChessCoordinate)coordinate).getRow()][((ChessCoordinate)coordinate).getCol()];
    }

    @Override
    public void setPieceInCell(Piece piece, Coordinate coordinate) {
        //ChessCoordinate cc = (ChessCoordinate)coordinate;
        cells[((ChessCoordinate)coordinate).getCol()][((ChessCoordinate)coordinate).getRow()].setPiece((ChessPiece)piece);
    }

}
