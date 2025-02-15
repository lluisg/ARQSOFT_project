/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server;

import upc.edu.etsetb.softarch.multigame.server.domain.BoardException;
import upc.edu.etsetb.softarch.multigame.server.domain.PieceException;
import upc.edu.etsetb.softarch.multigame.server.domain.PlayerException;
import upc.edu.etsetb.softarch.multigame.server.domain.PlayerSpec;

/**
 *
 * @author Lluis
 */
public interface GameController {
    public void setFactory(GameFactory factory) ;
    public void buildFramework() throws FrameworkException;
    public void addPlayer(PlayerSpec spec) throws PlayerException, BoardException, PieceException;
    public void playGame();
    public void playTurn();
}
