/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.network;

import upc.edu.etsetb.softarch.multigame.client.ClientNetworkAdapter;


/**
 *
 * @author JuanCarlos
 */
public class ChessServerNetworkAdapter {
    
    private ClientNetworkAdapter clNetworkAdap ;
    
    private ChessServerProtocolMngr servProtocolMngr ;
    
    public ChessServerNetworkAdapter(){}

    public void setClNetworkAdap(ClientNetworkAdapter clNetworkAdap) {
        this.clNetworkAdap = clNetworkAdap;
    }

    public void setServProtocolMngr(ChessServerProtocolMngr servProtocolMngr) {
        this.servProtocolMngr = servProtocolMngr;
    }
    
    public void sendFromServerToClient(String fromClientToServer) {
        this.clNetworkAdap.receiveFromServer(fromClientToServer);
    }
    
    public void receiveFromClient(String fromClientToServer){
        this.servProtocolMngr.receiveFromClient(fromClientToServer) ;
    }
}
