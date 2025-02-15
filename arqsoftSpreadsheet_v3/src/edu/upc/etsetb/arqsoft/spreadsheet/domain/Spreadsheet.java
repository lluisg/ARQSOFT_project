/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author betbp
 */
public class Spreadsheet {

    private Map<Coordinate, Cell> cells;
    private int maxCol;
    private int maxRow;

    public Spreadsheet(Integer row, Integer col) {
        this.maxCol = col;
        this.maxRow = row;
        cells = new HashMap<>();
    }

    public Cell getCell(Coordinate coor) {
        return cells.get(coor);
    }

    public List<Cell> getCells(List<Coordinate> lCoor) {
        List<Cell> list_cells = new LinkedList<>();
        for (Coordinate coor : lCoor) {
            list_cells.add(cells.get(coor));
        }
        list_cells.removeAll(Collections.singleton(null));
        return list_cells;
    }

    public int[] getMaxSize() {
        int[] maxsize = {this.maxRow, this.maxCol};
        return maxsize;
    }

    public void setSize(int row, int col) {
        this.maxRow = row;
        this.maxCol = col;
    }

    public void addCell(Coordinate coor, Cell cell) {
        cells.put(coor, cell);
        if (coor.getCol() + 1 > maxCol) {
            maxCol = coor.getCol() + 1;
        }
        if (coor.getRow() + 1 > maxRow) {
            maxRow = coor.getRow() + 1;
        }
    }

    public void removeCell(Coordinate coor) {
        cells.remove(coor);
    }

    public Map<Coordinate, String> getRepresentationMap() {
        Map<Coordinate, String> printMap = new HashMap();
        for (Map.Entry<Coordinate, Cell> entry : cells.entrySet()) {
            Coordinate coord = entry.getKey();
            Cell cell = entry.getValue();
            String representation = cell.getRepresentation();
            printMap.put(coord, representation);
        }
        return printMap;
    }

    public Map<Coordinate, String> getContentStringMap() {
        Map<Coordinate, String> saveMap = new HashMap<>();
        for (Map.Entry<Coordinate, Cell> entry : cells.entrySet()) {
            Coordinate coord = entry.getKey();
            String con = entry.getValue().getContentString();
            saveMap.put(coord, con);
        }
        return saveMap;
    }

    public boolean isCoordinateInside(Coordinate coor) {
        if (coor.getRow() < maxRow && coor.getCol() < maxCol) {
            return true;
        }
        return false;
    }
}
