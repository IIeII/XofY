package view;

import config.GameConfig;
import logic.vo.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashMap;

public class MainScreenViewLogic extends JFrame {

    public JButton restart;

    private int defaultCellSize = 80;
    private int distanceBetweenButtons = 2;

    public HashMap<String, JButton> fieldButtons = new HashMap<>();

    public MainScreenViewLogic() {
        setResizable(false);
        setVisible(true);
        init();
    }

    private void init() {

        setName("view");

        this.setBounds(100, 100, GameConfig.COLUMNS_COUNT*defaultCellSize + 200, GameConfig.ROWS_COUNT*defaultCellSize + 120);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initField();

        restart = new JButton("RESTART");
        restart.setVisible(true);
        restart.setFont(new Font("Tahoma", Font.BOLD, 10));
        restart.setBounds(50 + distanceBetweenButtons/2 + (distanceBetweenButtons + defaultCellSize)*GameConfig.COLUMNS_COUNT, 30, 100, 36);
        restart.setName("restartBtn");

        getContentPane().setLayout(null);
        getContentPane().add(restart);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        Line2D line;
        for (int row = 1; row < GameConfig.ROWS_COUNT; row++){
            line = new Line2D.Float(10, 32 + distanceBetweenButtons/2 + (distanceBetweenButtons + defaultCellSize)*row,  10 + distanceBetweenButtons/2 + (distanceBetweenButtons + defaultCellSize)*GameConfig.COLUMNS_COUNT,  32 + distanceBetweenButtons/2 + (distanceBetweenButtons + defaultCellSize)*row);
            g2.draw(line);
        }
        for (int column = 1; column < GameConfig.COLUMNS_COUNT; column++) {
            line = new Line2D.Float(10 + distanceBetweenButtons/2 + (distanceBetweenButtons + defaultCellSize)*column, 30,  10 + distanceBetweenButtons/2 + (distanceBetweenButtons + defaultCellSize)*column,  30 + distanceBetweenButtons/2 + (distanceBetweenButtons + defaultCellSize)*GameConfig.ROWS_COUNT);
            g2.draw(line);
        }

    }

    private void initField() {


        for (int row = 0; row < GameConfig.ROWS_COUNT; row++){
            for (int column = 0; column < GameConfig.COLUMNS_COUNT; column++) {

                fieldButtons.put(row + "_" + column, initButton(row, column));
            }
        }

    }

    private JButton initButton(int row, int column) {

        JButton button = new JButton("");
        button.setVisible(true);
        button.setFont(new Font("Tahoma", Font.PLAIN, defaultCellSize/2));
        button.setBounds(10 + (distanceBetweenButtons + defaultCellSize) * column, 10 + (distanceBetweenButtons + defaultCellSize) * row, defaultCellSize, defaultCellSize);
        button.setName(row + "_" + column);
        button.setFocusable(false);
        getContentPane().add(button);

        return button;
    }

    public void reset() {
        for (JButton button : fieldButtons.values()) {
            button.setText("");
        }
    }

    public void updateField(HashMap<String, Cell> map){

        for (Cell cell : map.values()) {
            JButton button = fieldButtons.get(cell.name);
            button.setText(cell.text);
            button.setForeground(cell.color);
        }
    }
}
