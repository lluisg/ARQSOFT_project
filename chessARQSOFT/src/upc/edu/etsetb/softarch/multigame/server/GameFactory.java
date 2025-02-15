package upc.edu.etsetb.softarch.multigame.server;

import upc.edu.etsetb.softarch.multigame.server.chess.ChessFactory;
import upc.edu.etsetb.softarch.multigame.server.domain.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lluis
 */
public abstract class GameFactory {
    
    public static GameFactory getInstance(String game) throws UnknownFactoryException, FrameworkException {
        if(game.equalsIgnoreCase("chess")){
            return new ChessFactory();
        }else{
            throw new UnsupportedOperationException("GameFactory::createInstance(): factory not available for this game");
        }
    }
    public abstract GameController createController() throws FrameworkException ;
    public abstract Board createBoard() throws BoardException ;
    public abstract Piece createPiece(PieceSpec spec) throws PieceException ;
    public abstract Player createPlayer(PlayerSpec spec) throws PlayerException ;
    public abstract Token createToken(TokenSpec spec) throws TokenException ;
    public abstract ThirdParty createThirdParty() throws ThirdPartyException ;

}
