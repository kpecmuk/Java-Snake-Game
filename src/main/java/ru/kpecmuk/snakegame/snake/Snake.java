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

    private ArrayList<SnakeCell> snake = new ArrayList<>();
    private SnakeHeading heading = new SnakeHeading();
    private SnakeMovement move = new SnakeMovement(snake, heading);
    private Graphics2D graphics;


    public SnakeMovement getMovement() {
        return move;
    }

    public SnakeHeading getHeading() {
        return heading;
    }

    public ArrayList<SnakeCell> getSnake() {
        return snake;
    }

    @Override
    public String toString() {
        return snake.get(0).getCellCoordX() + "  |  " + snake.get(0).getCellCoordY();
    }

    public Snake(Graphics2D graphics, int x, int y) {
        this.graphics = graphics;
        this.heading.setHeading(SnakeHeading.moving.DOWN);
        this.snake.add(new SnakeCell(x, y));
        this.snake.add(new SnakeCell(x - 1, y));
        this.snake.add(new SnakeCell(x - 2, y));
    }

    public void drawSnake() {
        for (SnakeCell snakeCell : snake) {
            drawCell(snakeCell.getCellCoordX(), snakeCell.getCellCoordY());
        }
    }

    private void drawCell(int x, int y) {
        graphics.setColor(Color.green);
        graphics.fill3DRect(toPixel(x), toPixel(y), CELL_SIZE - 5, CELL_SIZE - 5, true);
    }
}
    