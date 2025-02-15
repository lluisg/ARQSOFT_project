/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess;

import upc.edu.etsetb.softarch.multigame.server.chess.network.ChessServerProtocolMngr;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import upc.edu.etsetb.softarch.multigame.server.*;
import upc.edu.etsetb.softarch.multigame.server.chess.domain.*;
import upc.edu.etsetb.softarch.multigame.server.domain.*;
import upc.edu.etsetb.softarch.multigame.server.impl.AbstractGameController;

/**
 *
 * @author Lluis
 */
public class ChessController extends AbstractGameController{
    private ChessServerProtocolMngr protMngr ;
    
    protected ChessController() throws FrameworkException{
        super();
        this.setInitialState();
        this.turn = 0;
    }
    
    @Override
    public void setFactory(GameFactory factory) {
        this.factory = factory;
    }

    @Override
    public void buildFramework() throws FrameworkException {
        super.buildFramework();
        players = new ArrayList<Player>();
    }
    
    @Override
    public void addPlayer(PlayerSpec spec) throws PlayerException, BoardException, PieceException{
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
                break;
            default:
                throw new PlayerException("There are already two players in the game");
        }
        super.addPlayer(spec);
    }

    @Override
    public void playGame() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void playTurn() {
        //throw new UnsupportedOperationException("ChessController::playTurn(): play not available for this game");
    }

    @Override
    protected void actionsAsPerPlayerTurn() {
        //throw new UnsupportedOperationException("ChessController::actionsAsPerPlayerTurn(): actions not available for this turn");
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
    protected void giveTokensToPlayer(Player player, ThirdParty thirdParty) {
        //This game doesn't use tokens
    }

    @Override
    protected void setInitialState() {
        this.gameState = ChessState.NOT_FINALIZED;
    }
    
    public void setServerProtMngr(ChessServerProtocolMngr serverProtMngr){
        this.protMngr = serverProtMngr;
    }
    
    public void move(int rO, int cO, int rD, int cD){
        //TODO: 3.3
        Player player = this.players.get(this.turn);
        ChessPieceColor playercolor = ((ChessPlayer)player).getColor();
        
        ChessPieceColor pieceOcolor = ((ChessBoard)board).getPieceColor(rO, cO);
        
        if(pieceOcolor == null){
            protMngr.sendFromServerToClient("E: Origin cell is empty");
            return;
        }else if(pieceOcolor != playercolor){
            protMngr.sendFromServerToClient("E: Piece of the other player at the origin cell.");
            return;
        }
        
        ChessPieceColor pieceDcolor = ((ChessBoard)board).getPieceColor(rD, cD);
        
        if(pieceDcolor != null && pieceDcolor == playercolor){
            protMngr.sendFromServerToClient("E: Piece of the player that requests the movement at the destination cell.");
            return;
        }
        
        try {
            ((ChessPlayer)player).checkIfCanMovePiece(rO, cO, rD, cD, board);
        } catch (NoPieceMovementException ex) {
            protMngr.sendFromServerToClient("E: Movement requested not corresponding to the type of piece found at the\n" +
"origin cell.");
            return;
        } catch (NoPathFreeException ex) {
            protMngr.sendFromServerToClient("E: No free path from origin cell to destination cell.");
            return;
        }
        
        //not necesary for now
        //((ChessPlayer)player).proceedToMove(rO, cO, rD, cD, board);
        protMngr.sendFromServerToClient("OK");        
        nextPlayer();
        
    }
}