/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.run;

import upc.edu.etsetb.softarch.multigame.server.FrameworkException;
import upc.edu.etsetb.softarch.multigame.server.GameController;
import upc.edu.etsetb.softarch.multigame.server.GameFactory;
import upc.edu.etsetb.softarch.multigame.server.UnknownFactoryException;
import upc.edu.etsetb.softarch.multigame.server.chess.ChessController;
import upc.edu.etsetb.softarch.multigame.server.chess.ChessFactory;
import upc.edu.etsetb.softarch.multigame.server.chess.domain.ChessPieceColor;
import upc.edu.etsetb.softarch.multigame.server.chess.domain.ChessPlayerSpec;
import upc.edu.etsetb.softarch.multigame.server.domain.*;

/**
 *
 * @author Lluis
 */
public class ServerDemo {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        int a = 3;
        GameFactory factory;
        GameController controller;
        
        
        try{
            factory = (ChessFactory)GameFactory.getInstance("chess");
            controller = (ChessController)factory.createController();
            controller.buildFramework();
            ChessPlayerSpec specWhite = new ChessPlayerSpec(ChessPieceColor.WHITE);
            controller.addPlayer(specWhite);
            ChessPlayerSpec specBlack = new ChessPlayerSpec(ChessPieceColor.BLACK);
            controller.addPlayer(specBlack);
//            controller.addPlayer(specWhite);
            
            
        }catch(UnknownFactoryException e){
            System.out.println("There's no any game with this name");
        //}
        }catch(BoardException e){
            System.out.println("Couldn't create a ChessBoard: " + e.getMessage());
        }catch(FrameworkException e){
            System.out.println("There's an error creating the framework: " + e.getMessage());
        } catch (PlayerException e) {
            System.out.println("There's an error adding player: " + e.getMessage());
        } catch (PieceException e) {
            System.out.println("There's an error adding pieces: " + e.getMessage());
        }
    }    
}
