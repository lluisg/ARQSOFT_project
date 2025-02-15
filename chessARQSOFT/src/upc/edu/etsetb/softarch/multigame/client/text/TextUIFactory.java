/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc.edu.etsetb.softarch.multigame.client.text;

import upc.edu.etsetb.softarch.multigame.client.Client;
import upc.edu.etsetb.softarch.multigame.client.Color;
import upc.edu.etsetb.softarch.multigame.client.UIBoard;
import upc.edu.etsetb.softarch.multigame.client.UIFactory;
import upc.edu.etsetb.softarch.multigame.client.UIPiece;
import upc.edu.etsetb.softarch.multigame.client.UIRenderer;



/**
 *
 * @author JuanCarlos
 */
public class TextUIFactory extends UIFactory
{

    @Override
    public Client createUIClient() {
        return new TUIClient() ;
    }
    @Override
    public UIBoard createUIBoard() {
        return new TUIBoard() ;
    }

    @Override
    public UIPiece createUIPiece(String type, Color color) {
        return new TUIPiece(type,color) ;
    }

    @Override
    public UIRenderer createRenderer() {
        return new TUIRenderer() ;
    }

    
}
