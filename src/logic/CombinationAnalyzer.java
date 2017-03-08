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
    private HashMap<String, Cell> emptyCells;

    private HashMap<String, Cell> lastCells;

    public CombinationAnalyzer() {

        rand = new Random();
    }

    public Boolean hasWinningCombination( HashMap<String, Cell> map) {

        boolean result = false;

        playersCells = new HashMap<>();
        computerCells = new HashMap<>();
        emptyCells = new HashMap<>();

        lastCells = new HashMap<>();

        for (Cell cell : map.values()) {

            switch (cell.text){
                case PlaceHolders.COMPUTER:
                    computerCells.put(cell.name, cell);
                    break;
                case PlaceHolders.PLAYER:
                    playersCells.put(cell.name, cell);
                    break;
                default:
                    emptyCells.put(cell.name, cell);
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
            lastCells.clear();
            lastCells.put(cell.name, cell);
            if (((iconInRow + counterRowInDirection(UP,NONE, cell, cells) + counterRowInDirection(DOWN,NONE, cell, cells)) >= GameConfig.MIN_IN_ROW_FOR_WIN)){
                highlightLastCells();
                return true;
            }
            // left + none / right + none
            lastCells.clear();
            lastCells.put(cell.name, cell);
            if (((iconInRow + counterRowInDirection(NONE, LEFT, cell, cells) + counterRowInDirection(NONE, RIGHT, cell, cells)) >= GameConfig.MIN_IN_ROW_FOR_WIN)) {
                highlightLastCells();
                return true;
            }
            // right + down / left + up
            lastCells.clear();
            lastCells.put(cell.name, cell);
            if (((iconInRow + counterRowInDirection(DOWN, RIGHT, cell, cells) + counterRowInDirection(UP, LEFT, cell, cells)) >= GameConfig.MIN_IN_ROW_FOR_WIN)) {
                highlightLastCells();
                return true;
            }
            // right + up / left + down
            lastCells.clear();
            lastCells.put(cell.name, cell);
            if (((iconInRow + counterRowInDirection(UP, RIGHT, cell, cells) + counterRowInDirection(DOWN, LEFT, cell, cells)) >= GameConfig.MIN_IN_ROW_FOR_WIN)) {
                highlightLastCells();
                return true;
            }
        }
        return false;
    }

    private void highlightLastCells() {

        for (Cell cell : lastCells.values()) {
            cell.color = Color.RED;
        }
    }

    private int counterRowInDirection(String dir1, String dir2, Cell cell, HashMap<String, Cell> cells){

        int counter = 0;
        Point cellPoint = getCellPoint(cell.name);

        Point nextPoint = getNextInDirection(cellPoint,dir1, dir2);

        while (cells.containsKey(nextPoint.x + "_" + nextPoint.y)){
            lastCells.put(nextPoint.x + "_" + nextPoint.y, cells.get(nextPoint.x + "_" + nextPoint.y));
            nextPoint = getNextInDirection(nextPoint,dir1, dir2);
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

        int row = Character.getNumericValue(coord.charAt(0));
        int column = Character.getNumericValue(coord.charAt(2));

        return new Point(row, column);
    }

    public void makeComputerMove(HashMap<String, Cell> fieldMap) {
        Object[] values = emptyCells.values().toArray();
        Cell randomValue = (Cell)values[rand.nextInt(values.length)];
        fieldMap.get(randomValue.name).text = PlaceHolders.COMPUTER;
    }

    public boolean hasEmptyCells() {

        return !emptyCells.isEmpty();
    }
}
