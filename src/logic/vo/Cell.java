package logic.vo;

import java.awt.*;

public class Cell {
    public String name;
    public String text;
    public Color color;

    public Cell copy() {
        Cell result = new Cell();
        result.name = this.name;
        result.text = this.text;
        result.color = this.color;
        return result;
    }
}
