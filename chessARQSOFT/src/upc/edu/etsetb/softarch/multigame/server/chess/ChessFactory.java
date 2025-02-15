/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess;

import upc.edu.etsetb.softarch.multigame.server.*;
import upc.edu.etsetb.softarch.multigame.server.chess.domain.*;
import upc.edu.etsetb.softarch.multigame.server.domain.*;

/**
 *
 * @author Lluis
 */
public class ChessFactory extends GameFactory{
    private ChessController controller;
    private ChessBoard board;

    public ChessFactory() throws FrameworkException{
        this.controller = null;
        this.board = null;
    }
    
    @Override
    public GameController createController() throws FrameworkException{
        this.controller = new ChessController();
        controller.setFactory(this);
        return controller;
    }
    
    @Override
    public Board createBoard() throws BoardException{
        this.board = new ChessBoard();
        return board;
    }
    
    @Override
    public Piece createPiece(PieceSpec spec) throws PieceException{
        //throw new UnsupportedOperationException("Not implemented yet");
        ChessPieceSpec chessSpec = (ChessPieceSpec) spec;
        switch(chessSpec.getType()){
            case QUEEN:
                return new Queen(chessSpec);
            case KING:
                return new King(chessSpec);
            case BISHOP:
                return new Bishop(chessSpec);
            case KNIGHT:
                return new Knight(chessSpec);
            case ROOK:
                return new Rook(chessSpec);
            case PAWN:
                return new Pawn(chessSpec);
        }
        throw new PieceException("Unknown chess piece type: "+ chessSpec.getType());
    }
    
    @Override
    public Player createPlayer(PlayerSpec spec) throws PlayerException{
        return new ChessPlayer((ChessPlayerSpec)spec);
    }
    
    @Override
    public Token createToken(TokenSpec spec) throws TokenException{
        throw new TokenException("ChessFactory::createInstance(): Chess doesn't have Tokens");
    }
    
    @Override
    public ThirdParty createThirdParty() throws ThirdPartyException{
        throw new ThirdPartyException("ChessFactory::createInstance(): Chess doesn't have ThirdParty");
    }
}
