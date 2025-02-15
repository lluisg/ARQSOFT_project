/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import arqsoft.spreadsheet.FileManager.FileSource;
import arqsoft.spreadsheet.domain.Coordinate;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author betbp
 */
public class Manager {
    
    
    
    private FileManager fileMng; // create only when user needs it for the fisrt time
    private SpreadManager spreadMng;
    private UserInterface UI;
    private StringManager strMng;
    private boolean loadedSpread;
    private boolean closeProgram;
    public Integer MAX_SIZE_SPREAD;
    
    public Manager(){
        fileMng = null;
        UI = new UserInterface();
        strMng = new StringManager();
        spreadMng = new SpreadManager();
        loadedSpread = false;
        closeProgram = false;
        MAX_SIZE_SPREAD = 1000;
    }

    public void Init() throws ParserException, IOException{
        boolean carryon = true;
        while(!closeProgram){
            int instruction = UI.DisplayMenu(loadedSpread);
            UI.sendMessage("Instruction selected = "+instruction);

            switch(instruction){
                case 1: // "create"
                    carryon = true;
                    if(loadedSpread){
                        carryon = this.warnLoaded();
                    }
                    if(carryon){
                        this.createEmptySpreadsheet();
                    }
                    break;
                    
                case 2:// "load"
                    try {
                        carryon = true;
                        if(loadedSpread){
                            carryon = this.warnLoaded();
                        }
                        if(carryon){
                            this.loadS2VFile();
                            loadedSpread = true;                           
                        }
                    } catch (CancelException ex) {
                        UI.sendMessage("Action cancelled");
                        break;
                    }
                    break;
                    
                case 3: // "save"
                    try {
                        this.saveS2VFile();
                    } catch (CancelException ex) {
                        UI.sendMessage("Action cancelled");
                        break;
                    }
                    break;
                    
                case 4: // "display"
                    Map<Coordinate, String> spread = spreadMng.getRepresentationSpreadsheet();
                    int[] size = spreadMng.getSizeSpread();
                    UI.displaySpreadsheet(spread, size);
                    break;
                    
                case 5: // "edit"
                    this.editCell();
                    break;
                    
                case 6: // "size"
                    changeSizeSpreadsheet();
                    break;
                    
                case 7: // "close"
                    closeProgram = true;
                    break;
                default:
                    UI.sendMessage("Option not recognised");
            }          
        }
    }
    
    private void createEmptySpreadsheet(){
        boolean inputCoordOK = false;
        Integer[] inputCoord = {null, null};
        Integer col = null, row = null;
        
        while(inputCoordOK == false){
            try{
                inputCoord = askSize();
                inputCoordOK = true;
            }catch(InputException ie){
                UI.sendMessage(ie.getMsg());
                inputCoordOK = false;
            }
        }
        row = inputCoord[0];
        col = inputCoord[1];

        UI.sendMessage("size=: "+row+"x"+col);
        spreadMng.createEmptySpreadsheet(row, col);
        loadedSpread = true;
    }
    
    private void saveS2VFile() throws CancelException, IOException{
        if (fileMng == null){
            fileMng = new FileManager();
        }

        String path = this.getSaveFilePath();
        try {
            fileMng.saveFile(path, spreadMng.getContentSpreadsheet());
            UI.sendMessage("File saved correctly.");
        } catch (java.io.FileNotFoundException ex) {
            UI.sendMessage("Couldn't save the file " + path);
        } catch (FileException ex){
            UI.sendMessage(ex.getError().name());
        }
    }

    private void loadS2VFile() throws IOException, CancelException{
        FileSource origin = this.getOrigin();
        if (fileMng == null){
            fileMng = new FileManager();
        }
        String path = this.getOpenFilePath(origin);
        Map<Coordinate,String> spread_map = fileMng.readFile(path,origin);

        boolean correct = spreadMng.spreadsheetFromFile(spread_map);
        if(correct){
            UI.sendMessage("File loaded corretly.\n");            
        }else{
            UI.sendMessage("File loaded. Some cells have errors.\n");            
        } 
    }

    private void changeSizeSpreadsheet(){
        Integer[] inputCoord = {null, null};
        Integer col = null, row = null;
        boolean inputCoordOK = false;
        while(inputCoordOK == false){
            try{
                inputCoord = askSize();
                inputCoordOK = true;
            }catch(InputException ie){
                UI.sendMessage(ie.getMsg());
                inputCoordOK = false;
            }
        }
        row = inputCoord[0];
        col = inputCoord[1];
        
        spreadMng.changeSizeSpreadsheet(row, col);
        UI.sendMessage("New size=: "+row+"x"+col);
    }
    
    private void editCell() {
        
        Coordinate coord = this.getCoord();
        boolean content_ok = false;
        
        while (!content_ok){
            
            String content = UI.askContent();
            try {
                spreadMng.editCell(coord, content);
                content_ok = true;
                UI.sendMessage("Cell edited correctly.");
            } catch (CancelException ex) {
                UI.sendMessage("You've enterered the cancel sequence.");
                content_ok = true;
            } catch (ParserException | SyntaxException ex) {
                UI.sendMessage(ex.getMessage());
            } catch (LogicalLoopException ex) {
                UI.sendMessage("The formula you are trying to enter creates a loop.");
            } catch (NaNException ex) {
                UI.sendMessage("The formula you are trying to enter uses cells with errors.");
            } catch (TextValueException ex) {
                UI.sendMessage("The formula you are trying to enter uses cells with text.");
            }
        } 
    }

    private FileSource getOrigin() {
        FileSource or = null;
        while(or == null){
            String origin = UI.askOriginFile();
            if (origin.equalsIgnoreCase("local")||origin.equalsIgnoreCase("l")){
                or = FileSource.LOCAL;
            }else if (origin.equalsIgnoreCase("internet")||origin.equalsIgnoreCase("i")){
                or = FileSource.INTERNET;
            }else{
                UI.sendMessage("origin not valid, please enter it again");
            }
        }
        return or;
    }

    private String getOpenFilePath(FileSource origin) throws CancelException {
        boolean not_valid = true;
        String path = null;
        while(not_valid){
            path = UI.askOpenPath();
            if(path.equalsIgnoreCase("CANCEL")){
                throw new CancelException();
            }else if (origin == FileSource.LOCAL){
                try {
                    fileMng.checkOpenLocalFile(path);
                    not_valid = false;
                } catch (FileException ex) {
                    if(null != ex.getError())switch (ex.getError()) {
                        case FILE_NOT_FOUND:
                            UI.sendMessage("File not found");
                            break;
                        case CANNOT_READ:
                            UI.sendMessage("You don't have permission to read the file");
                            break;
                        case FILE_TYPE_NOT_RECOGNISED:
                            UI.sendMessage("File extension not recognised");
                            break;
                        default:
                            break;
                    }
                }
            }else if(origin == FileSource.INTERNET){
                try {
                    fileMng.checkInternetFile(path);
                    not_valid = false;
                } catch (FileException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return path;
    }
    
    private String getSaveFilePath() throws CancelException, IOException{
        boolean directory = false;
        String path = "";
        while(!directory){
            path = UI.askSavePath();
            try {
                path = fileMng.checkSaveDirectory(path);
                if (path.equalsIgnoreCase("CANCEL")){
                    throw new CancelException();
                }
                directory=true;
            } catch (FileException ex) {
                switch (ex.getError()){
                    case CANNOT_WRITE:
                        UI.sendMessage("Cannot write in this directory");
                        break;
                    case DIRECTORY_NOT_FOUND:
                        UI.sendMessage("Cannot find the directory");
                }
            }
        }

        String file = "";
        boolean name = false;
        while(!name){
            file = UI.askSaveName();
            if(file.equalsIgnoreCase("CANCEL")){
                throw new CancelException();
            }
            if(!file.endsWith("."+AcceptedFileType.C2V.getFileExtension())){
                file = file + ".c2v";
            }
            try{
                fileMng.checkSaveName(path+file);
                path = path + file;
                name = true;
            }catch(FileException ex){
                switch (ex.getError()){
                    case CANNOT_WRITE:
                        UI.sendMessage("Cannot write in this directory");
                        break;
                    case FILE_ALREADY_EXISTS:
                        UI.sendMessage("File " + file + " already exists");
                        if(UI.askReplaceFile()){
                            path = path+file;
                            name = true;
                            break;
                        }
                        break;
                }
            }
        }
        return path;
    }

    private Integer[] askSize() throws InputException{
        Integer col = null, row = null;
        while(row == null){
            String rowS = UI.askRow();
            try{
                row = Integer.parseInt(rowS);
            }catch(NumberFormatException ex){
                UI.sendMessage("Enter a valid number. You entered: "+ rowS);
                row=null;
            }
        }            
        while(col == null || row*col>MAX_SIZE_SPREAD){
            String colS = UI.askCol();
            try{
                col = Integer.parseInt(colS);
                if(row*col>MAX_SIZE_SPREAD){
                    UI.sendMessage("Memory exceeded");
                }
            }catch(NumberFormatException ex){
                UI.sendMessage("Enter a valid number. You entered: "+ colS);
                col=null;  
            } 
        }
        Integer[] size = {row, col};
        return size;
    }

    private Coordinate getCoord() {
        boolean valid = false;
        Coordinate c = null;
        
        while(!valid){
            String s = UI.askCoord();
            if(strMng.isCellCoordinate(s)){
                c = new Coordinate(s);
                valid = spreadMng.coordinateInside(c);
                if (!valid){
                    UI.sendMessage("Coordinate " + s + " is not inside the spreadsheet." );
                }
            }else{
                UI.sendMessage("Coordinate not in the correct form");
            }   
        }
        return c;
    }

    private boolean warnLoaded() {
        boolean carry = UI.warnLoaded();
        return carry;
    }
    
}
