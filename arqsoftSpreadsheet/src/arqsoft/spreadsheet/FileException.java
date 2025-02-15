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
public class FileException extends Exception {
    public enum FileError{
        FILE_NOT_FOUND,
        CANNOT_CREATE_NEW_FILE,
        CANNOT_READ,
        CANNOT_WRITE,
        FILE_TYPE_NOT_RECOGNISED,
        FILE_WITH_ERRORS,
        FILE_ALREADY_EXISTS, 
        DIRECTORY_NOT_FOUND, 
        CANNOT_CONNECT_URL,
        URL_NOT_VALID;
    }
    
    FileError fe;
    
    FileException(FileError fe) {
        this.fe = fe;
    }
    
    public FileError getError(){
        return fe;
    }
    
}
