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
    private SnakeMovement move = new SnakeMovement(snake);
    private Graphics2D graphics;

    public ArrayList<SnakeCell> getSnakeList() {
        return this.snake;
    }

    public SnakeMovement getMovement() {
        return move;
    }

    public Snake(Graphics2D graphics, int x, int y) {
        this.graphics = graphics;


        this.snake.add(new SnakeCell(x, y));
        this.snake.add(new SnakeCell(x - 1, y));
        this.snake.add(new SnakeCell(x - 2, y));
    }

    public void drawSnake(Graphics2D g) {
        for (SnakeCell snakeCell : snake) {
            drawCell(snakeCell.getCellCoordX(), snakeCell.getCellCoordY());
        }
    }

    private void drawCell(int x, int y) {
        graphics.setColor(Color.green);
        graphics.fill3DRect(toPixel(x), toPixel(y), CELL_SIZE - 5, CELL_SIZE - 5, true);
    }
}
    