/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server.run;

import java.util.logging.Level;
import java.util.logging.Logger;
import upc.edu.etsetb.arqsoft.multigame.server.GameFactory;
import upc.edu.etsetb.arqsoft.multigame.server.UnknownFactoryException;
import upc.edu.etsetb.arqsoft.multigame.server.chess.ChessFactory;
import upc.edu.etsetb.arqsoft.multigame.server.domain.BoardException;

/**
 *
 * @author betbp
 */
public class ServerDemo {
    
    
    public static void main(String[] args){
        String gameName = "chess";
        try {
            ChessFactory chessFactory = (ChessFactory) GameFactory.getInstance(gameName);
            chessFactory.createBoard();
            
        } catch (UnknownFactoryException ex) {
            System.out.println("There's no game with name:" + gameName);
        } catch (BoardException ex) {
            System.out.println("Couldn't create board");
            
        }
    }
}
