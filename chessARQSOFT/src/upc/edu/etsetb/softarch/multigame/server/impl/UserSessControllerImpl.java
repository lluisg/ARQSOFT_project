/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.impl;

import upc.edu.etsetb.softarch.multigame.server.GameController;
import upc.edu.etsetb.softarch.multigame.server.GameFactory;
import upc.edu.etsetb.softarch.multigame.server.UserSessController;

/**
 *
 * @author betbp
 */
public class UserSessControllerImpl implements UserSessController{
    private GameFactory gFactory;
    private GameController gController;
    
    @Override
    public void createGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFactory(GameFactory factory) {
        this.gFactory = factory;
    }

    @Override
    public void setController(GameController controller) {
        this.gController = controller;
    }

    @Override
    public GameController getController() {
        return gController;
    }
    
}
