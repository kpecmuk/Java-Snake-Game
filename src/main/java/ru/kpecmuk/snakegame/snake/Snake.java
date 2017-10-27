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

    private ArrayList<SnakeCell> snakeCells = new ArrayList<>();
    private SnakeHeading heading = new SnakeHeading();
    private SnakeMovement movement = new SnakeMovement(this);
    private Graphics2D graphics;


    public SnakeMovement getMovement() {
        return this.movement;
    }

    public SnakeHeading getHeadingObj() {
        return heading;
    }

    public ArrayList<SnakeCell> getSnakeCells() {
        return snakeCells;
    }

    @Override
    public String toString() {
        return snakeCells.get(0).getCellCoordX() + "  |  " + snakeCells.get(0).getCellCoordY();
    }

    public Snake(Graphics2D graphics, int x, int y) {
        this.graphics = graphics;
        this.snakeCells.add(new SnakeCell(x, y));
        this.snakeCells.add(new SnakeCell(x - 1, y));
        this.snakeCells.add(new SnakeCell(x - 2, y));
    }

    public void drawSnake() {
        for (SnakeCell snakeCell : snakeCells) {
            drawCell(snakeCell.getCellCoordX(), snakeCell.getCellCoordY());
        }
    }

    private void drawCell(int x, int y) {
        graphics.setColor(Color.green);
        graphics.fill3DRect(toPixel(x), toPixel(y), CELL_SIZE - 5, CELL_SIZE - 5, true);
    }
}
