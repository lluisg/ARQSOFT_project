/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.chess;

import upc.edu.etsetb.arqsoft.multigame.server.GameController;
import upc.edu.etsetb.arqsoft.multigame.server.GameFactory;
import upc.edu.etsetb.arqsoft.multigame.server.chess.domain.ChessBoard;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Board;
import upc.edu.etsetb.arqsoft.multigame.server.domain.BoardException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Piece;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceSpec;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Player;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PlayerException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PlayerSpec;
import upc.edu.etsetb.arqsoft.multigame.server.domain.ThirdParty;
import upc.edu.etsetb.arqsoft.multigame.server.domain.ThirdPartyException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Token;
import upc.edu.etsetb.arqsoft.multigame.server.domain.TokenException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.TokenSpec;

/**
 *
 * @author betbp
 */
public class ChessFactory extends GameFactory{
    private ChessController controller;
    private ChessBoard board;

    @Override
    public GameController createController() {
        controller = new ChessController();
        return controller;
    }

    @Override
    public Board createBoard() throws BoardException {
        board = new ChessBoard();
        return board;
    }

    @Override
    public Piece createPiece(PieceSpec spec) throws PieceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player createPlayer(PlayerSpec spec) throws PlayerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Token createToken(TokenSpec spec) throws TokenException {
        throw new TokenException("Chess doesn't need Tokens"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ThirdParty createThirdParty() throws ThirdPartyException {
        throw new ThirdPartyException("Chess doesn't need Third Party"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
