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
public class Token {
    public enum TokenType{
        FUNCTION,
        OPERATOR,
        OP_PARENTHESIS,
        CL_PARENTHESIS,
        EQUAL,
        FLOAT,
        CELL_RANGE,
        CELL,
        SEPARATOR,
        TEXT,
        WHITESPACE,
        UNK;
    }
  
    private final TokenType token;
    private final String sequence;

    public Token(TokenType token, String sequence) {
        super();
        this.token = token;
        this.sequence = sequence;
    }

    public String getContent(){
        return this.sequence;
    }
    public TokenType getType(){
        return this.token;
    } 
}
