/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.network;

import upc.edu.etsetb.softarch.multigame.server.chess.ChessController;

/**
 *
 * @author JuanCarlos
 */
public class ChessServerProtocolMngr {

    private ChessServerNetworkAdapter servNetworkAdap;
    private ChessController controller; // Replace Game by the name of your Game class 

    public ChessServerProtocolMngr(){}

    public void setNetworkAdapter(ChessServerNetworkAdapter servNetworkAdap){
        this.servNetworkAdap = servNetworkAdap;
    }
    
    public void setGame(ChessController controller) {
        this.controller = controller;
    }

    public void receiveFromClient(String fromClientToServer) {
        this.processCommand(fromClientToServer);
    }
    
    public void sendFromServerToClient(String fromServerToClient) {
        this.servNetworkAdap.sendFromServerToClient(fromServerToClient);
    }

    public void processCommand(String fromClientToServer) {
        String[] parts = fromClientToServer.split(" ");
        if (parts.length != 2) {
            this.servNetworkAdap.sendFromServerToClient("ERROR: Non valid command arrived (there must be "
                    + "exactly two parts separated by whitespace): " + fromClientToServer);
            return;
        }
        switch (parts[0]) {
            case "M":
            case "m":
                String[] strCoords = parts[1].split(",");
                if (strCoords.length != 4) {
                    this.servNetworkAdap.sendFromServerToClient("ERROR: Non valid command arrived (there must "
                            + "be exactly four arguments separated by ',': " + fromClientToServer);
                    return;
                }
                int[] coords = new int[4];
                for (int i = 0; i < 4; i++) {
                    try {
                        coords[i] = Integer.parseInt(strCoords[i]);
                    } catch (NumberFormatException ex) {
                        this.servNetworkAdap.sendFromServerToClient("ERROR: Non valid command arrived "
                                + "(coordinate number " + (i + 1) + "is not a number:" + fromClientToServer);
                        return;
                    }
                }
                this.controller.move(coords[0],coords[1],coords[2],coords[3]);
                break;
        }
    }


}
