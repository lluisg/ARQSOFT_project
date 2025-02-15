/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server;

/**
 *
 * @author Lluis
 */
public interface UserSessController {
    public void createGame();
    public void setFactory(GameFactory factory);
    public void setController(GameController controller);
    public GameController getController();
}
