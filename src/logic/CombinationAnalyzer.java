package logic;

import config.GameConfig;
import logic.vo.Cell;
import logic.vo.PlaceHolders;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class CombinationAnalyzer {

    private final String NONE = "NONE";
    private final String UP = "UP";
    private final String DOWN = "DOWN";
    private final String LEFT = "LEFT";
    private final String RIGHT = "RIGHT";

    private Random rand;

    private HashMap<String, Cell> playersCells;
    private HashMap<String, Cell> computerCells;
    private HashMap<String, CellExt> emptyCells;


    public CombinationAnalyzer() {

        rand = new Random();
    }

    public Boolean hasWinningCombination( HashMap<String, Cell> map) {

        boolean result = false;

        playersCells = new HashMap<>();
        computerCells = new HashMap<>();
        emptyCells = new HashMap<>();

        for (Cell cell : map.values()) {

            switch (cell.text){
                case PlaceHolders.COMPUTER:
                    computerCells.put(cell.name, cell);
                    break;
                case PlaceHolders.PLAYER:
                    playersCells.put(cell.name, cell);
                    break;
                default:
                    emptyCells.put(cell.name, new CellExt(cell.copy()));
            }
        }

        if (isContainWiningLine(computerCells) || isContainWiningLine(playersCells)){
            result = true;
        }

        return result;
    }

    private boolean isContainWiningLine(HashMap<String, Cell> cells) {
        int iconInRow = 1;
        for (Cell cell : cells.values()) {
            // up + none / down + none
            if (((iconInRow + counterInDirection(UP,NONE, cell, cells) + counterInDirection(DOWN,NONE, cell, cells)) >= GameConfig.MIN_IN_ROW_FOR_WIN)){
                return true;
            }
            // left + none / right + none
            if (((iconInRow + counterInDirection(LEFT,NONE, cell, cells) + counterInDirection(RIGHT,NONE, cell, cells)) >= GameConfig.MIN_IN_ROW_FOR_WIN)) {
                return true;
            }
            // right + down / left + up
            if (((iconInRow + counterInDirection(RIGHT,DOWN, cell, cells) + counterInDirection(LEFT,UP, cell, cells)) >= GameConfig.MIN_IN_ROW_FOR_WIN)) {
                return true;
            }
            // right + up / left + down
            if (((iconInRow + counterInDirection(RIGHT,UP, cell, cells) + counterInDirection(LEFT,DOWN, cell, cells)) >= GameConfig.MIN_IN_ROW_FOR_WIN)) {
                return true;
            }
        }
        return false;
    }

    private int counterInDirection (String dir1, String dir2, Cell cell, HashMap<String, Cell> cells){

        int counter = 0;
        Point cellPoint = getCellPoint(cell.name);

        Point nextPoint = getNextInDirection(cellPoint,dir1, dir2);

        while (cells.containsKey(nextPoint.y + "_" + nextPoint.y)){
            nextPoint = getNextInDirection(cellPoint,dir1, dir2);
            counter += 1;
        }

        return counter;
    }

    private Point getNextInDirection(Point point, String dir1, String dir2){

        int row = point.x;
        int column = point.y;

        if (dir1.equals(UP)) {
            row--;
        }
        if (dir1.equals(DOWN)) {
            row++;
        }
        if(dir2.equals(LEFT)){
            column--;
        }
        if(dir2.equals(RIGHT)){
            column++;
        }

        return new Point(row, column);
    }

    private Point getCellPoint(String coord) {

        int row = coord.charAt(0);
        int column = coord.charAt(2);

        return new Point(row, column);
    }

    private void analyzePossibleLoseCombination(){

    }

    private void analyzePossibleWinCombination(){

    }

    public void makeComputerMove(HashMap<String, Cell> fieldMap) {
        //analyzePossibleLoseCombination();
        //analyzePossibleWinCombination();
        rand.nextInt(emptyCells.size());

        Object[] values = emptyCells.values().toArray();
        CellExt randomValue = (CellExt)values[rand.nextInt(values.length)];
        fieldMap.get(randomValue.name).text = PlaceHolders.COMPUTER;
    }

    private class CellExt extends Cell {
        public int cost = 1;
        public CellExt(Cell cell) {
            super();
            text = cell.text;
            name = cell.name;
            color = cell.color;
        }
    }
}
