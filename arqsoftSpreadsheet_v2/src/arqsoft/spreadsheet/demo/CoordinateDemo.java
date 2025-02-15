/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.demo;

import arqsoft.spreadsheet.domain.Coordinate;

/**
 *
 * @author betbp
 */
public class CoordinateDemo {
    public static void main(String[] args) {
        /*String s = "ac1215";
        Coordinate c = new Coordinate(s);
        
        System.out.println("row: " + c.getRow() + " col: " +c.getCol());*/
        //50 --> AZ
        //25 --> Z
        //0 --> A
        //26 --> AA
        int i = 50;
        String s = int2col(i);
        System.out.println(s);
        System.out.println(int2col2(i));
        
    }
    
    private static String int2col(int i){
        //pravar operacions sense chars
        
        int a = 'A'-1;
        //int a = 'A';
        int mod = 'Z'-'A';
        //i = i + 1;
        String s = "";
        //col += (r.charAt(r.length()-1-i)-96) * ((int)Math.pow(25, i));
        do{
            int r = i%mod;
            if(r==0){
                r = mod+1;
                i = (int)Math.floor(i/mod) - 1;
            }else{
                i = (int)Math.floor(i/mod);
            }
            s = s + " "+(char)(r+a);
        }while(i>0);
        StringBuilder sb = new StringBuilder(s);  
        return sb.reverse().toString();
 
    }
    
    private static String int2col2(int i){
        //pravar operacions sense chars
        
        int a = 'A'-1;
        //int a = 'A';
        int mod = 'Z'-'A';
        //i = i + 1;
        String s = "";
        do{
            int r = i%mod;
            /*if(r==0){
                r = mod+1;
                i = (int)Math.floor(i/mod) - 1;
            }else{
                i = i - (int)Math.floor(i/mod);
            }*/
            
            s = s +" "+ r;
            i = i - (int)Math.floor(i/mod)*mod;
        }while(i>=mod);
        StringBuilder sb = new StringBuilder(s);  
        return sb.reverse().toString();
 
    }
}
