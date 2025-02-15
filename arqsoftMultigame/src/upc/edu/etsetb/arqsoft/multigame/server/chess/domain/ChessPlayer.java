/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.chess.domain;

import java.util.ArrayList;
import java.util.List;
import upc.edu.etsetb.arqsoft.multigame.server.GameFactory;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Board;
import upc.edu.etsetb.arqsoft.multigame.server.domain.BoardException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Coordinate;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceSpec;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Player;
import upc.edu.etsetb.arqsoft.multigame.server.domain.TokenSpec;
import upc.edu.etsetb.arqsoft.multigame.server.impl.AbstractPlayer;
import upc.edu.etsetb.arqsoft.utilities.TupleOf2;

/**
 *
 * @author betbp
 */
public class ChessPlayer extends AbstractPlayer{
    
    private ChessPieceColor color;
    
    public ChessPlayer(ChessPlayerSpec spec){
        super();
        this.color = spec.getColor();
    }

    @Override
    public void playTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createAndPutPiecesOnBoard(Board board, GameFactory factory) throws PieceException, BoardException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TupleOf2<PieceSpec, Coordinate>> getPiecesCoordinates() {
        List<TupleOf2<PieceSpec,Coordinate>> pieces = new ArrayList<TupleOf2<PieceSpec,Coordinate>>();
        int row = 0;
        if(color.equals(ChessPieceColor.BLACK)){
            row = 7;
        }
        
        PieceSpec rook = new ChessPieceSpec(this.color,ChessPiece.Type.ROOK);
        Coordinate rook1 = new ChessCoordinate(row,0);
        Coordinate rook2 = new ChessCoordinate(row,7);
        pieces.add(new TupleOf2<> (rook,rook1));
        boolean add = pieces.add(new TupleOf2<PieceSpec,Coordinate> (rook,rook2));
        
        PieceSpec knight = new ChessPieceSpec(this.color,ChessPiece.Type.KNIGHT);
        Coordinate knight1 = new ChessCoordinate(row,1);
        Coordinate knight2 = new ChessCoordinate(row,6);
        pieces.add(new TupleOf2<> (knight,knight1));
        pieces.add(new TupleOf2<> (knight,knight2));
        
        PieceSpec bishop = new ChessPieceSpec(this.color,ChessPiece.Type.BISHOP);
        Coordinate bishop1 = new ChessCoordinate(row,2);
        Coordinate bishop2 = new ChessCoordinate(row,5);
        pieces.add(new TupleOf2<> (bishop,bishop1));
        pieces.add(new TupleOf2<> (bishop,bishop2));
        
        PieceSpec queen = new ChessPieceSpec(this.color,ChessPiece.Type.QUEEN);
        Coordinate queen1 = new ChessCoordinate(row,3);
        pieces.add(new TupleOf2<> (queen,queen1));
        
        PieceSpec king = new ChessPieceSpec(this.color,ChessPiece.Type.KING);
        Coordinate king1 = new ChessCoordinate(row,4);
        pieces.add(new TupleOf2<> (king,king1));
        
        if (this.color == ChessPieceColor.WHITE){
            row++;
        } else {
            row--;
        }
        
        PieceSpec pawn = new ChessPieceSpec(this.color,ChessPiece.Type.PAWN);
        for (int i=0 ; i<8 ; i++){
            Coordinate pawn1 = new ChessCoordinate(row,i);
            pieces.add(new TupleOf2<> (pawn,pawn1));
        }
        
        return pieces;
    }
    
    public List<TupleOf2<TokenSpec, Coordinate>> getTokensCoordinates(){
        return new ArrayList<TupleOf2<TokenSpec,Coordinate>>();
    }

    
}
