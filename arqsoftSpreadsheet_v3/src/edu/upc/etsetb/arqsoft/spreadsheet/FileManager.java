/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import edu.upc.etsetb.arqsoft.spreadsheet.domain.Coordinate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author betbp
 */
public class FileManager {

    public enum FileSource{INTERNET, LOCAL};
    
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
    
    private FileParser fParser;
    private FileSaver fSaver;
    private FileLoader fLoader;
    
    public FileManager(){
        fParser = new FileParser();
        fSaver = new FileSaver();
        fLoader = new FileLoader();
    }

    public void saveFile(String path, Map<Coordinate,String> map) throws FileException, FileNotFoundException, IOException{
        File file = new File(path);
        if(!file.isFile()){
            if(!file.createNewFile()){
                throw new FileException(FileException.FileError.CANNOT_CREATE_NEW_FILE);
            }
        }
        file.setWritable(true);
        List<String> stringList = fParser.parseSpreadsheet(map);
        fSaver.saveS2VFile(path, stringList);
    }

    public Map<Coordinate,String> readFile(String path, FileSource origin) throws FileNotFoundException, IOException {
        List<String> stringList = null;
        if (origin == FileSource.LOCAL){
            stringList = fLoader.readLocalFile(path);
        }else if(origin == FileSource.INTERNET){
            stringList = fLoader.readInternetFile(path);
        }
        return fParser.parseFile(stringList);
    }

    public String checkSaveDirectory(String path) throws FileException, IOException {
        File file = new File(path);
        if(!file.isDirectory()){
            throw new FileException(FileException.FileError.DIRECTORY_NOT_FOUND);
        }else if(!file.canWrite()){
            throw new FileException(FileException.FileError.CANNOT_WRITE);
        }
        if (!path.endsWith(File.separator)){
            path = path+File.separator;
        }
        return path;
    }
    
    public void checkSaveName(String path) throws FileException{
        File file = new File(path);
        if (file.isFile() && file.canWrite()){
            throw new FileException(FileException.FileError.FILE_ALREADY_EXISTS);
        }
        if (file.isFile() && !file.canWrite()){
            throw new FileException(FileException.FileError.CANNOT_WRITE);
        }
  
    }
    
    public void checkInternetFile(String url) throws FileException{
        try {
            (new java.net.URL(url)).openStream().close();
        } catch (MalformedURLException ex) {
            throw new FileException(FileException.FileError.URL_NOT_VALID);
        } catch (IOException ex) {
            throw new FileException(FileException.FileError.CANNOT_CONNECT_URL);
        }
        checkFileExtension(url);
        
    }
    
    public void checkOpenLocalFile(String path) throws FileException{
        File file =  new File(path);
        if (!file.isFile()){
            throw new FileException(FileException.FileError.FILE_NOT_FOUND); 
        }
        if (!file.canRead()){
            throw new FileException(FileException.FileError.CANNOT_READ);
        }
        this.checkFileExtension(path);

    }

    private void checkFileExtension(String path) throws FileException {
        boolean accepted = false;
        for (AcceptedFileType file_type : AcceptedFileType.values()){
            if(path.endsWith("."+file_type.getFileExtension())){
                accepted = true;
            }
        }
        if(!accepted){
            throw new FileException(FileException.FileError.FILE_TYPE_NOT_RECOGNISED);
        }
    }

}
