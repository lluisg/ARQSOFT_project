/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

/**
 *
 * @author betbp
 */
public enum AcceptedFileType {
    CSV("csv"),
    C2V("c2v");
    
    private final String file_extension;
    
    AcceptedFileType(String s){
        file_extension = s;
    }
    
    public String getFileExtension(){
        return file_extension;
    }
}
