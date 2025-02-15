/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.arqsoft.multigame.server;

import upc.edu.etsetb.arqsoft.multigame.server.chess.ChessFactory;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Board;
import upc.edu.etsetb.arqsoft.multigame.server.domain.BoardException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Piece;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PieceSpec;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Player;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PlayerException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.PlayerSpec;
import upc.edu.etsetb.arqsoft.multigame.server.domain.ThirdParty;
import upc.edu.etsetb.arqsoft.multigame.server.domain.ThirdPartyException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.Token;
import upc.edu.etsetb.arqsoft.multigame.server.domain.TokenException;
import upc.edu.etsetb.arqsoft.multigame.server.domain.TokenSpec;

/**
 *
 * @author betbp
 */
public abstract class GameFactory {
    public static GameFactory getInstance(String game) throws UnknownFactoryException {
        if (game.equalsIgnoreCase("chess")){
            return new ChessFactory();
        }else{
            throw new UnknownFactoryException("unrecognised " + game);
        }
        
    }
    public abstract GameController createController() ;
    public abstract Board createBoard() throws BoardException ;
    public abstract Piece createPiece(PieceSpec spec) throws PieceException ;
    public abstract Player createPlayer(PlayerSpec spec) throws PlayerException ;
    public abstract Token createToken(TokenSpec spec) throws TokenException ;
    public abstract ThirdParty createThirdParty() throws ThirdPartyException ;
    
}
