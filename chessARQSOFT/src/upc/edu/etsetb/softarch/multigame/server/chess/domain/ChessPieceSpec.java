/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.server.chess.domain;

import upc.edu.etsetb.softarch.multigame.server.domain.*;

/**
 *
 * @author Lluis
 */
public class ChessPieceSpec implements PieceSpec{
    private ChessPieceColor color;
    private ChessPiece.Type type;
    
    public static ChessPieceSpec getInstance(ChessPieceColor color, ChessPiece.Type type){
        return new ChessPieceSpec(color, type);
    }
    protected ChessPieceSpec(ChessPieceColor color, ChessPiece.Type type){
        this.color = color;
        this.type = type;
    }
    public ChessPieceColor getColor(){
        return this.color;
    }
    public ChessPiece.Type getType(){
        return this.type;
    }
}
