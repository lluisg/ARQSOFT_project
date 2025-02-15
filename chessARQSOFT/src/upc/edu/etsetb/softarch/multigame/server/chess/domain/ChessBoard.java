/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.domain;

import upc.edu.etsetb.softarch.multigame.server.domain.*;


/**
 *
 * @author Lluis
 */
public class ChessBoard implements Board{
    private ChessCell[][] cells;
    
    public ChessBoard(){
        int rows = 8;
        int cols = 8;
        cells = new ChessCell[rows][cols];

        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                cells[i][j] = new ChessCell(i, j);
            }
        } 
    }
    
    @Override
    public Cell getCell(Coordinate coord){
        return cells[((ChessCoordinate)coord).getRow()][((ChessCoordinate)coord).getCol()];
    }
    
    @Override
    public void setPieceInCell(Piece piece, Coordinate coord) throws BoardException{
        cells[((ChessCoordinate)coord).getRow()][((ChessCoordinate)coord).getCol()].setPiece((ChessPiece)piece);
    }
    
    
    public ChessPiece getPiece(int r, int c){
        return (ChessPiece)cells[r][c].getPiece();
    }
    
    public ChessPieceColor getPieceColor(int r, int c){
        Piece piece = cells[r][c].getPiece();
        return ((ChessPiece)piece).getColor();
    }
    
    public void putPiece(Piece p, int r, int c){
        cells[r][c].setPiece(p);
    }

}
