/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.client;

import upc.edu.etsetb.softarch.multigame.client.ClientProtocolMngr;
import upc.edu.etsetb.softarch.multigame.server.chess.network.ChessServerNetworkAdapter;


/**
 *
 * @author JuanCarlos
 */
public class ClientNetworkAdapter {
    
    private ChessServerNetworkAdapter servNetworkAd ;
    
    private ClientProtocolMngr clProtocolMngr ;
    
    public ClientNetworkAdapter(){
        
    }

    public void setClientProtocolMngr(ClientProtocolMngr clProtocolMngr){
        this.clProtocolMngr = clProtocolMngr ;
    }    
    
    public void setServerNetworkAd(ChessServerNetworkAdapter servNetworkAd){
        this.servNetworkAd = servNetworkAd ;
    }
    
    public void sendFromClientToServer(String fromClientToServer) {
        this.servNetworkAd.receiveFromClient(fromClientToServer);
    }
    
    public void receiveFromServer(String fromServerToClient){
        this.clProtocolMngr.receiveFromServer(fromServerToClient) ;
    }

}
