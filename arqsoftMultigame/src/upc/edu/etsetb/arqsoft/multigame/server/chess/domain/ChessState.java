/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.chess.domain;

import upc.edu.etsetb.arqsoft.multigame.server.domain.GameState;

/**
 *
 * @author betbp
 */
public enum ChessState implements GameState{

    CONTINUE, SUSPENSION, DRAW, STALEMATE, RESIGNATION, CHECKMATE;
    
    private ChessState state;

    @Override
    public boolean isFinalization() {
        if (state.equals(CONTINUE)){
            return false;            
        }else{
            return true;
        }
    }
    
    public void setState(ChessState state){
        this.state = state;
    }
    
}
