/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.impl;

import java.util.*;
import upc.edu.etsetb.softarch.multigame.server.FrameworkException;
import upc.edu.etsetb.softarch.multigame.server.GameController;
import upc.edu.etsetb.softarch.multigame.server.GameFactory;
import upc.edu.etsetb.softarch.multigame.server.domain.*;

/**
 *
 * @author Lluis
 */
public abstract class AbstractGameController implements GameController {
    protected GameFactory factory;
    protected Board board;
    protected List<Player> players;
    protected ThirdParty thirdParty;
    protected GameState gameState;
    protected int turn;
    
    public AbstractGameController() throws FrameworkException{
        //this.buildFramework();
    }
    
    @Override
    public void setFactory(GameFactory factory) {
        this.factory = factory;
    }
    
    @Override
    public void buildFramework() throws FrameworkException {
        try {
            this.board = factory.createBoard();
        } catch (BoardException ex) {
            throw new FrameworkException("board couldn't be created");
        }
        try {
            this.thirdParty = factory.createThirdParty();
        } catch (ThirdPartyException ex) {
            //game doesn't use thirdParty
            //See the ThrirdParty exception --> doesn't need it or an error!!
            this.thirdParty = null;
        }
    }
    
    public void addPlayer(PlayerSpec spec) throws PlayerException, BoardException, PieceException {
        Player player = factory.createPlayer(spec);
        players.add(player);
        this.createAndGivePiecesToPlayer(player, board); 
        this.giveTokensToPlayer(player, thirdParty);
    }
    
    @Override
    public void playGame() {
        this.turn = 0;
        setInitialState(); //set gameState
        while(!gameState.isFinalization()){
            this.playTurn(); 
            this.actionsAsPerPlayerTurn();//Actualitza el gameState i quin jugador te el següent torn
        }
        //notify of game finalization
    }
    
    protected Player nextPlayer(){
        turn = (turn + 1) % players.size();
        Player p = players.get(turn);
        return p;
    }
            
            
    protected abstract void actionsAsPerPlayerTurn();
    protected abstract void createAndGivePiecesToPlayer(Player player, Board board) throws PlayerException, BoardException, PieceException;
    protected abstract void giveTokensToPlayer(Player player, ThirdParty thirdParty);
    protected abstract void setInitialState();
}
