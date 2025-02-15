/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.chess;

import upc.edu.etsetb.arqsoft.multigame.server.FrameworkException;
import upc.edu.etsetb.arqsoft.multigame.server.GameController;
import upc.edu.etsetb.arqsoft.multigame.server.GameFactory;
import upc.edu.etsetb.arqsoft.multigame.server.chess.domain.ChessBoard;
import upc.edu.etsetb.arqsoft.multigame.server.chess.domain.ChessPieceColor;
import upc.edu.etsetb.arqsoft.multigame.server.chess.domain.ChessPlayer;
import upc.edu.etsetb.arqsoft.multigame.server.chess.domain.ChessPlayerSpec;
import upc.edu.etsetb.arqsoft.multigame.server.chess.domain.ChessState;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Board;
import upc.edu.etsetb.arqsoft.multigame.server.domain.BoardException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Player;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PlayerException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PlayerSpec;
import upc.edu.etsetb.arqsoft.multigame.server.domain.ThirdParty;
import upc.edu.etsetb.arqsoft.multigame.server.domain.ThirdPartyException;
import upc.edu.etsetb.arqsoft.multigame.server.impl.AbstractGameController;

/**
 *
 * @author betbp
 */
public class ChessController extends AbstractGameController{

    protected ChessController() throws BoardException, ThirdPartyException, FrameworkException {
        super();
        this.setInitialState();
    }

    @Override
    public void setFactory(GameFactory factory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buildFramework() throws FrameworkException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPlayer(PlayerSpec spec) throws PlayerException,BoardException,PieceException {
        if (!(spec instanceof ChessPlayerSpec)){
            throw new PlayerException("Not a chess player specification");
        }
        switch (players.size()){
            case 0:
                if (((ChessPlayerSpec)spec).getColor()!= ChessPieceColor.WHITE){
                    throw new PlayerException("Wrong asigned color, should be white");
                }
                break;
            case 1:
                if (((ChessPlayerSpec)spec).getColor()!= ChessPieceColor.BLACK){
                    throw new PlayerException("Wrong asigned color, should be black");
                }
            default:
                throw new PlayerException("There are already two players in the game");
        }
        super.addPlayer(spec);
    }

    @Override
    public void playGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionsAsPerPlayerTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void createAndGivePiecesToPlayer(Player player, Board board) throws PlayerException, BoardException, PieceException {
        if (!(player instanceof ChessPlayer)){
            throw new PlayerException("Player is not a player of Chess");
        }
        if (!(board instanceof ChessBoard)){
            throw new BoardException("Board is not a Chess Board");
        }
        ((ChessPlayer)player).createAndPutPiecesOnBoard(board, factory);
    }

    @Override
    protected void giveTokenToPlayer(Player player, ThirdParty thirdParty) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setInitialState() {
        this.gameState = ChessState.CONTINUE;
    }
    
}
