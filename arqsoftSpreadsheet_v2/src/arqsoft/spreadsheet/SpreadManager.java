/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqsoft.spreadsheet;

import arqsoft.spreadsheet.domain.*;
import java.util.*;

/**
 *
 * @author betbp
 */
public class SpreadManager {

    private Spreadsheet spreadsheet;
    private SpreadFabric spreadFabric;
    private Calculator calculator;

    public SpreadManager() {
        spreadFabric = new SpreadFabric(this);
        this.calculator = new Calculator(this);
    }

    public void createEmptySpreadsheet(int row, int col) {
        this.spreadsheet = spreadFabric.createEmptySpreadsheet(row, col);
    }

    public boolean spreadsheetFromFile(Map<Coordinate, String> map) {

        boolean error_cells = false;
        try {
            spreadsheet = spreadFabric.spreadsheetFromFile(map);
        } catch (CancelException | ParserException | SyntaxException ex) {
            error_cells = true;
        }

        for (Coordinate coor : map.keySet()) {
            this.updateWatching(spreadsheet.getCell(coor));
        }

        for (Coordinate coor : map.keySet()) {
            Cell cell = spreadsheet.getCell(coor);
            if (cell.getState() == Cell.CellState.FORMULA) {
                List<Coordinate> lCoor = cell.getListWatchedCoordinates();
                try {
                    this.checkLogicalLoop(cell, lCoor);
                } catch (LogicalLoopException ex) {
                    cell.setErrState();
                    error_cells = true;
                }
            }
        }

        for (Coordinate coor : map.keySet()) {
            Cell cell = spreadsheet.getCell(coor);
            cell.update();
        }
        return !error_cells;
    }

    public void changeSizeSpreadsheet(int row, int col) {
        int[] sizeSpread = spreadsheet.getMaxSize();
        int maxrow = sizeSpread[0];
        int maxcol = sizeSpread[1];
        if (row < maxrow || col < maxcol) {
            for (int r = row; r < maxrow; r++) {
                Coordinate coord = new Coordinate(r, col);
                Cell cell = spreadsheet.getCell(coord);

                if (cell != null) {
                    List<Observer> l_observer = cell.getListObservers();
                    if (!l_observer.isEmpty()) {
                        throw new UnsupportedOperationException("CHANGE SIZE: Delete a cell with dependencies.");

                    }
                    List<Subject> l_oserving = cell.getListObserving();

                    for (Subject c_observing : l_oserving) {
                        c_observing.removeObserver((Observer) cell);
                    }

                    spreadsheet.removeCell(coord);
                }
            }

            for (int c = col; c < maxcol; c++) {
                Coordinate coord = new Coordinate(row, c);
                Cell cell = spreadsheet.getCell(coord);

                if (cell != null) {
                    List<Observer> l_observer = cell.getListObservers();
                    if (!l_observer.isEmpty()) {
                        throw new UnsupportedOperationException("CHANGE SIZE: Delete a cell with dependencies.");

                    }
                    List<Subject> l_oserving = cell.getListObserving();

                    for (Subject c_observing : l_oserving) {
                        c_observing.removeObserver((Observer) cell);
                    }

                    spreadsheet.removeCell(coord);
                }
            }
        }
        spreadsheet.setSize(row, col);

    }

    public Map<Coordinate, String> getRepresentationSpreadsheet() {
        return spreadsheet.getRepresentationMap();
    }

    public Map<Coordinate, String> getContentSpreadsheet() {
        return spreadsheet.getContentStringMap();
    }

    public void editCell(Coordinate coor, String exp) throws CancelException, ParserException, SyntaxException, LogicalLoopException, NaNException, TextValueException {
        Cell cell = getCell(coor);
        editContent(cell, exp);
        updateCell(cell, coor);
        spreadsheet.addCell(coor, cell);
    }

    public void editContent(Cell cell, String exp) throws CancelException, ParserException, SyntaxException {
        List<Token> tokens = StringManager.parseString(exp);

        Token.TokenType tt;
        if (tokens.isEmpty()) {
            tt = Token.TokenType.UNK;
        } else if (tokens.get(0).getType() == Token.TokenType.EQUAL) {//formula
            tt = Token.TokenType.EQUAL;
        } else if (tokens.size() == 1 && tokens.get(0).getType() == Token.TokenType.FLOAT) {
            tt = Token.TokenType.FLOAT;
        } else {
            tt = Token.TokenType.TEXT;
        }
        Content content = spreadFabric.createContent(tt, exp);

        if (tt == Token.TokenType.EQUAL) {
            StringManager.checkSyntax(tokens);
            List<FormulaComponent> fComp = StringManager.infix2postfix(tokens);
            ((Formula) content).setFormulaComponents(fComp);
        }
        cell.setContent(content);
    }

    private void updateCell(Cell cell, Coordinate coor_current) throws LogicalLoopException, NaNException, TextValueException {
        Content cont = cell.getContent();
        cell.unsubscriveFromAllObserving();

        if (cont instanceof Formula) {
            List<Coordinate> lCoor = ((Formula) cont).getUsedCoordinates();
            if(lCoor.contains(coor_current)){
                throw new LogicalLoopException();
            }
            checkLogicalLoop(cell, lCoor);
            for (Coordinate coor : lCoor) {
                Cell c = this.getCell(coor);
                spreadsheet.addCell(coor, c);
                c.registerObserver(cell);
                cell.registerObserved(c);
            }
            Float res = Calculator.computeFormula((Formula) cont);
            ((Formula) cont).setResult(res);
        }
        cell.updateState();
        cell.notifyObservers();
        //cell.setCellUnchanged();
    }

    public Cell getCell(Coordinate coor) {
        Cell cell = spreadsheet.getCell(coor);
        if (cell == null) {
            cell = spreadFabric.createEmptyCell();
        }
        return cell;
    }

    public Cell addOrGetCell(Coordinate coor) {
        Cell cell = spreadsheet.getCell(coor);
        if (cell == null) {
            cell = spreadFabric.createEmptyCell();
            spreadsheet.addCell(coor, cell);
        }
        return cell;
    }

    public List<Cell> getCells(List<Coordinate> lCoor) {
        return spreadsheet.getCells(lCoor);
    }

    public int[] getSizeSpread() {
        return spreadsheet.getMaxSize();
    }

    public boolean coordinateInside(Coordinate coor) {
        return spreadsheet.isCoordinateInside(coor);
    }

    private void checkLogicalLoop(Cell cell, List<Coordinate> lCoor) throws LogicalLoopException {
        List<Cell> lCells = spreadsheet.getCells(lCoor);
        for (Cell c : lCells) {
            if (c.isWatching(cell)) {
                throw new LogicalLoopException();
            }
        }
    }

    private void updateWatching(Cell cell) {
        List<Coordinate> watching = cell.getListWatchedCoordinates();
        if (watching != null) {
            for (Coordinate coor : watching) {
                Cell c = this.addOrGetCell(coor);
                cell.registerObserved(c);
                c.registerObserver(cell);
            }
        }
    }

}
