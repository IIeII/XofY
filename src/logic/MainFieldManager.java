package logic;

import config.GameConfig;
import logic.vo.Cell;
import logic.vo.PlaceHolders;
import util.EventDispatcherExt;
import util.EventTrans;

import java.awt.*;
import java.util.HashMap;

public class MainFieldManager extends EventDispatcherExt {

    private CombinationAnalyzer analyzer;
    private HashMap<String, Cell> fieldMap = new HashMap<>();


    public MainFieldManager() {

        analyzer = new CombinationAnalyzer();

        createField();
    }

    private void createField() {

        for (int row = 0; row < GameConfig.COLUMNS_COUNT; row++){
            for (int column = 0; column < GameConfig.ROWS_COUNT; column++) {
                fieldMap.put(row + "_" + column, createCell(row, column));
            }
        }
    }

    private Cell createCell(int row, int column) {

        Cell result = new Cell();
        result.text = PlaceHolders.EMPTY;
        result.name = row + "_" + column;
        result.color = Color.BLACK;

        return result;
    }

    //on user action performed
    public void actionPerformed(String userChoice){

        //check is action allowed
        Cell cell = fieldMap.get(userChoice);
        cell.text = PlaceHolders.PLAYER;
        cell.color = Color.BLACK;



        if (analyzer.hasWinningCombination(fieldMap)){
            //highlight winning
//            dispatchEvent(new EventTrans(Events.));
        } else {
            analyzer.makeComputerMove(fieldMap);
        }

        onFieldUpdated();
    }

    //notify
    private void onFieldUpdated() {
        try {
            dispatchEvent(new EventTrans(Events.FIELD_UPDATED, fieldMap));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    public void reset() {


    }
}
