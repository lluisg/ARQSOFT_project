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
public class ChessCoordinate implements Coordinate{
    private int col, row;

    public ChessCoordinate(int r, int c){
        this.row = r;
        this.col = c;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public void setCol(int col){
        this.col = col;
    }
}
