/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet.domain;

import arqsoft.spreadsheet.Calculator;
import arqsoft.spreadsheet.NaNException;
import arqsoft.spreadsheet.Observer;
import arqsoft.spreadsheet.Subject;
import arqsoft.spreadsheet.TextValueException;
import java.util.*;

/**
 *
 * @author betbp
 */
public class Cell implements Subject, Observer{
    public enum CellState{
        EMPTY, NUMBER, FORMULA, TEXT, ERR;
    }
    
    private Content content;
    private List<Observer> observers;
    private List<Subject> observing;
    private CellState state;
   
    public Cell(){
        this.observers = new ArrayList<>();
        this.observing = new ArrayList<>();
        state = CellState.EMPTY;
    }

    @Override
    public void registerObserver(Observer observer) {
        if(!observers.contains(observer)){
            this.observers.add(observer);
        }
    }
    
    public void registerObserved(Subject observed) {
        if (!observing.contains(observed)){
            this.observing.add(observed);
        }
    }

    @Override
    public void removeObserver(Observer cell) {
        this.observers.remove(cell);
    }
    
    @Override
    public void notifyObservers() {
        for(Observer observer : this.observers){
            observer.update();
        }
    }

    @Override
    public void update() {
        if(content instanceof Formula){
            boolean dependError = false;
            for(Subject s : observing){
                CellState st = ((Cell)s).getState();
                if(st == Cell.CellState.ERR){
                    this.state = CellState.ERR;
                    dependError = true;
                }
            }
            if(dependError == false){
                try {
                    float result = Calculator.computeFormula((Formula)content);
                    ((Formula)this.content).setResult(result);
                    updateState();
                } catch (NaNException | TextValueException ex) {
                    ((Formula)this.content).setResult(Float.NaN);
                    this.state = Cell.CellState.ERR;
                }          
            }
            for(Observer obs : this.observers){
                obs.update();
            }
        }
    }    
    
    public void unsubscriveFromAllObserving(){
        for(Subject s : observing){
            s.removeObserver(this);
        }
        observing.clear();
    }
        
    public void updateState(){
        if (content instanceof Numerical){
            state = CellState.NUMBER;
        }else if(content instanceof Formula){
            state = CellState.FORMULA;
        }else if(content instanceof Text){
            state =  CellState.TEXT;
        }else{//content is empty
            state =  CellState.EMPTY;
        }
    }
    
    public CellState getState(){
        return state;
    }
    
    public void setErrState(){
        state = CellState.ERR;
    }
        
    public String getRepresentation(){
        if(this.state == Cell.CellState.ERR){
            return "ERROR";
        }else if(this.state == Cell.CellState.EMPTY){
            return "";
        }
        return this.content.getRepresentation();
    }
    
    public String getContentString(){
        if (content == null){
            return "";
        }
        return content.getContent();
    }
    
    public List<Observer> getListObservers(){
        return this.observers;
    }
    
    public List<Subject> getListObserving(){
        return this.observing;
    }
    
    public void setContent(Content c){
        this.content = c;
        this.updateState();
    }
    
    public Content getContent(){
        return this.content;
    }
    
    public void setFormulaComponents(List<FormulaComponent> lc){
        if (content instanceof Formula){
            ((Formula) content).setFormulaComponents(lc);            
        }
    }
    
    public void setResult(float i){
        ((Formula) content).setResult(i);
    }
    
    public boolean isWatching (Cell c){
        if(observing.contains(c)){
            return true;
        }
        for (Subject cc : observing){
            if(((Cell)cc).isWatching(c)){
                return true;
            }
        }
        return false;
    }
    
    public float getValue(){
        if (content instanceof Numerical){
            return ((Numerical)content).getValue();
        }else if(content instanceof Formula){
            return ((Formula)content).getResult();
        }else{
            return Float.NaN;
        }
    }
    
    public List<Coordinate> getListWatchedCoordinates(){
        if (content instanceof Formula){
            return ((Formula)content).getUsedCoordinates();
        }
        return null;
    }

}
