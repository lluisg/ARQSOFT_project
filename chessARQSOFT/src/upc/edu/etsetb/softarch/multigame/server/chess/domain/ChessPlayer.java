/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.domain;

import java.util.*;
import upc.edu.etsetb.softarch.multigame.server.GameFactory;
import upc.edu.etsetb.softarch.multigame.server.chess.ChessFactory;
import upc.edu.etsetb.softarch.multigame.server.chess.NoPathFreeException;
import upc.edu.etsetb.softarch.multigame.server.chess.NoPieceMovementException;
import upc.edu.etsetb.softarch.multigame.server.domain.*;
import upc.edu.etsetb.softarch.multigame.server.impl.AbstractPlayer;
import upc.edu.etsetb.softarch.utilities.TupleOf2;

/**
 *
 * @author Lluis
 */
public class ChessPlayer extends AbstractPlayer {
    
    private ChessPieceColor color;
   // List<TupleOf2<PieceSpec, Coordinate>> p_spec;
    
    public ChessPlayer(ChessPlayerSpec spec){
        super();
        this.color = spec.getColor();
    }
    
    @Override
    public void playTurn() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void createAndPutPiecesOnBoard(Board board, GameFactory factory) throws PieceException, BoardException {
        List<TupleOf2<PieceSpec, Coordinate>> p_spec = this.getPiecesCoordinates();
        for (TupleOf2<PieceSpec, Coordinate> tuple : p_spec){
            ChessPiece p = (ChessPiece) (factory.createPiece(tuple.getFirst()));
            board.setPieceInCell(p, tuple.getSecond());
        }      
    }

    @Override
    public List<TupleOf2<PieceSpec, Coordinate>> getPiecesCoordinates() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<TupleOf2<PieceSpec, Coordinate>> p_spec = new LinkedList<>();
        int pawnsRow, othersRow;
        if(this.color == ChessPieceColor.BLACK){
            pawnsRow = 6;
            othersRow = 7;
        }else{
            pawnsRow = 1;
            othersRow = 0;
        }
        
        ChessPieceSpec rookSpec = new ChessPieceSpec(this.color, ChessPiece.Type.ROOK);
        ChessCoordinate rookCoord1 = new ChessCoordinate(othersRow, 0);
        p_spec.add(new TupleOf2(rookSpec, rookCoord1));
        ChessCoordinate rookCoord2 = new ChessCoordinate(othersRow, 7);
        p_spec.add(new TupleOf2(rookSpec, rookCoord2));
        
        ChessPieceSpec knightSpec = new ChessPieceSpec(this.color, ChessPiece.Type.KNIGHT);
        ChessCoordinate knightCoord1 = new ChessCoordinate(othersRow, 1);
        p_spec.add(new TupleOf2(knightSpec, knightCoord1));
        ChessCoordinate knightCoord2 = new ChessCoordinate(othersRow, 6);
        p_spec.add(new TupleOf2(knightSpec, knightCoord2));
        
        ChessPieceSpec bishopSpec = new ChessPieceSpec(this.color, ChessPiece.Type.BISHOP);
        ChessCoordinate bishopCoord1 = new ChessCoordinate(othersRow, 2);
        p_spec.add(new TupleOf2(bishopSpec, bishopCoord1));
        ChessCoordinate bishopCoord2 = new ChessCoordinate(othersRow, 5);
        p_spec.add(new TupleOf2(bishopSpec, bishopCoord2));
        
        ChessPieceSpec kingSpec = new ChessPieceSpec(this.color, ChessPiece.Type.KING);
        ChessCoordinate kingCoord = new ChessCoordinate(othersRow, 4);
        p_spec.add(new TupleOf2(kingSpec, kingCoord));

        ChessPieceSpec queenSpec = new ChessPieceSpec(this.color, ChessPiece.Type.QUEEN);
        ChessCoordinate queenCoord = new ChessCoordinate(othersRow, 3);
        p_spec.add(new TupleOf2(queenSpec, queenCoord));
        
        ChessPieceSpec pawnSpec = new ChessPieceSpec(this.color, ChessPiece.Type.PAWN);
        for(int i=0; i<=7; i++){
            ChessCoordinate pawnCoord = new ChessCoordinate(pawnsRow, i);
            p_spec.add(new TupleOf2(pawnSpec, pawnCoord));
        }
        return p_spec;
    }
    
    public List<TupleOf2<TokenSpec, Coordinate>> getTokensCoordinates(){
        return new ArrayList<TupleOf2<TokenSpec,Coordinate>>();
    }
    
    public ChessPieceColor getColor(){
        return this.color;
    }
    public void setColor(ChessPieceColor c){
        this.color = c;
    }
    
    public ChessPieceColor getPiecesColor(){
        return this.color;
    }
    
    public void proceedToMove(int rO, int cO, int rD, int cD, Board board) throws BoardException {
        ChessCoordinate coorO = new ChessCoordinate(rO, cO);
        ChessCoordinate coorD = new ChessCoordinate(rD, cD);
        Cell cell = board.getCell(coorO);
        Piece piece = ((ChessCell)cell).getPiece();
        
        board.setPieceInCell(piece, coorD);
        
    }
    
    public void checkIfCanMovePiece(int rO, int cO, int rD, int cD, Board board) throws NoPieceMovementException, NoPathFreeException{
        ChessCoordinate coorO = new ChessCoordinate(rO, cO);
        Cell cell = board.getCell(coorO);
        Piece piece = ((ChessCell)cell).getPiece();
        ((ChessPiece)piece).canReachDestination(rO, cO, rD, cD, (ChessBoard)board);
    }
}