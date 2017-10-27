package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.utils.Convert;

import java.awt.*;
import java.util.ArrayList;

import static ru.kpecmuk.snakegame.game.Game.CELL_SIZE;

/**
 * @author kpecmuk
 * @since 25.10.2017
 */

public class Snake extends Convert {
    private static final Logger log = LoggerFactory.getLogger(Snake.class);

    private ArrayList<Cell> cells = new ArrayList<>();
    private Direction direction = new Direction();
    private Movement movement = new Movement(this);
    private Graphics2D graphics;


    public Snake(Graphics2D graphics, int x, int y) {
        this.graphics = graphics;
        this.cells.add(new Cell(x, y));
        this.cells.add(new Cell(x - 1, y));
        this.cells.add(new Cell(x - 2, y));
    }

    public Movement movementObj() {
        return this.movement;
    }

    public Direction directionObj() {
        return this.direction;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return cells.get(0).getCellCoordX() + "  |  " + cells.get(0).getCellCoordY();
    }

    public void drawSnake() {
        for (Cell cell : cells) {
            drawCell(cell.getCellCoordX(), cell.getCellCoordY());
        }
    }

    private void drawCell(int x, int y) {
        graphics.setColor(Color.green);
        graphics.fill3DRect(toPixel(x), toPixel(y), CELL_SIZE - 5, CELL_SIZE - 5, true);
    }
}
