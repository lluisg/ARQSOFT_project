/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

/**
 *
 * @author betbp
 */
public class Coordinate {
    private int row;
    private int col;
    
    public Coordinate(int r, int c){
        row=r;
        col=c;
    }

    public Coordinate(String s) {
        s = s.toLowerCase();
        String r="";
        String c="";
        for (int i=0 ; i<s.length() ; i++){
            char ascii = (s.charAt(i));
            if (ascii >= '0' && ascii <= '9'){
                c = s.substring(0,i);
                r = s.substring(i);
                break;
            } 
        }
        col = 0;
        for (int i=0 ; i<c.length() ; i++){
            col += (c.charAt(c.length()-1-i)-96) * ((int)Math.pow(25, i));
        }
        col -= 1;
        row = Integer.parseInt(r)-1;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public Coordinate getMinSize(Coordinate c){
        //compare two coordinates and get a coordinate with the smallest bottom right edge
        int row_max = this.row;
        if(c.row > row_max){
            row_max = c.row;
        }
        if(c.col >= this.col){
            return new Coordinate(row_max,c.col);
        }else{
            return new Coordinate(row_max,this.col);
        }
    }
    
    public boolean isBefore(Coordinate c){
        if(this.row <= c.row && this.col <= c.col){            
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.row;
        hash = 17 * hash + this.col;
        return hash;
    }
}
