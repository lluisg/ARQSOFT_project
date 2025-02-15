/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.domain;

import upc.edu.etsetb.softarch.multigame.server.domain.GameState;

/**
 *
 * @author Lluis
 */
public enum ChessState implements GameState {
    //LOOK AT use cases PlayGame and PlayTurnChess IDENTIFY DIFERENT STATES
    NOT_FINALIZED, SUSPENSION, DRAW, STALEMATE, RESIGNATION, CHECKMATE;
    private ChessState state;
    
    public boolean isFinalization(){
        if(this.state.equals(NOT_FINALIZED)){
            return false;
        }else{
            return true;
        }
    }
    
    public void setState(ChessState state){
        this.state = state;
    }

}
