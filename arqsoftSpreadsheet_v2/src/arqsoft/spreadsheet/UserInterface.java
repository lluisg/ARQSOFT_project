/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import arqsoft.spreadsheet.domain.Coordinate;
import java.util.*;
import java.lang.*;
/**
 *
 * @author betbp
 */
public class UserInterface {
    Scanner console;
    public UserInterface(){
        console = new Scanner(System.in);        
    }
     
    public int DisplayMenu(boolean spreadLoaded){
        System.out.println("Enter which option would you like from the next ones:");
        System.out.println("1- Create Empty Spreadsheet");
        System.out.println("2- Load a Spreadsheet");
        if(spreadLoaded){
            System.out.println("3- Save Spreadsheet");
            System.out.println("4- Display Spreadsheet");
            System.out.println("5- Display Formulas in the Spreadsheet");
            System.out.println("6- Edit Spreadsheet");
            System.out.println("7- Change size Spreadsheet");            
        }
        System.out.println("8- Close the program\n");

        boolean op = true;
        while(op){
            System.out.println("Option number:");
            String optionS = console.nextLine();
            try{
                return Integer.parseInt(optionS);
            }catch (NumberFormatException ex){
                System.out.println("The option has to be a number");
            }
        }
        return 0;
    }
        
    public String askCoord (){
        System.out.println("Write the cell coordinate you want to change in the form 'A1'(row col):");
        return console.nextLine();
    }
    
    public String askContent(){
        System.out.println("Write the content inside the cell:");
        return console.nextLine();
    }

    public String askRow(){
        System.out.println("Write how many rows do you want?");
        return console.nextLine();
    }

    public String askCol(){
        System.out.println("Write how many columns do you want?");
        return console.nextLine();
    }

    public String askOpenPath(){
        System.out.println("Introduce the path and name to the file you want to open:");
        return console.nextLine();
    }

    public String askSavePath(){
        System.out.println("Introduce the path to the directory you want to save the file");
        return console.nextLine();
    }
    
    public String askSaveName(){
        System.out.println("Introduce the name of the file");
        return console.nextLine();
    }
    
    public String askOriginFile(){
        System.out.println("Where are you loading the file from? [l]ocal or [i]nternet?");
        return console.nextLine();
    }
    
    public void displaySpreadsheet(Map<Coordinate,String> map, int[] sizeSpread){
        int maxrows = sizeSpread[0];
        int maxcols = sizeSpread[1];
        
        //TODO: columns after Z
        for(int c=0; c <= maxcols; c++){
            double nletters = Math.floor(c/26);
            double diff = c - nletters*26;
            //x-n*25=dif
            String colum = "";
            if(nletters>0){
                colum += (char)(nletters+64);

            }
            colum += (char)(diff+1+64);
            System.out.print("  "+colum+" ");
        }
        
        System.out.print("\n");
        for(int r=0; r<maxrows;r++){
            
            for(int c=0; c<maxcols; c++){
                System.out.print("  _ ");
            }
            
            System.out.print("\n"+ String.valueOf(r+1)+"|");
            
            for(int c=0; c<maxcols; c++){   
                Coordinate coord_elem = new Coordinate(r, c);
                
                String rep = map.get(coord_elem);
                if (rep == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(rep);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
        for(int c=0; c<maxcols; c++){
            System.out.print("  _ ");
        }
        System.out.print("\n");
    }

    public void sendMessage(String msg) {
        System.out.println(msg);
    }

    public boolean askReplaceFile() throws CancelException {
        boolean ask = true;
        while(ask){
            System.out.println("Do you want to replace the file? [Y]es [N]o or [cancel]");
            String s = console.nextLine();
            if (s.equalsIgnoreCase("Y")){
                return true;
            }else if (s.equalsIgnoreCase("N")){
                return false;
            }else if(s.equalsIgnoreCase("cancel")){
                throw new CancelException();
            }else{
                System.out.println("Unallowed answer");
            }
        }
        return true;
    }

    public boolean warnInfoLoss() {
        boolean validresponse = false;
        while(!validresponse){
            System.out.println("If you continue, all the changes on the spreadsheet will be lost. \n Are you sure to continue? [y/n]");
            String response = console.nextLine();
            if(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")){
                return true;
            }else if(response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")){
                return false;
            }
        }
        return true;
    }
}
