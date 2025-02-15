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
public class Text extends Content{
    
    public Text(String content){
        super();
        this.content = content;
    }

    @Override
    public String getRepresentation() {
        return content;
    }

}
