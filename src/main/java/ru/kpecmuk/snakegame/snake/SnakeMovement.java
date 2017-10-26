package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author kpecmuk
 * @since 26.10.2017
 */

public class SnakeMovement {
    private static final Logger log = LoggerFactory.getLogger(SnakeMovement.class);

    private ArrayList<SnakeCell> snake;

    SnakeMovement(ArrayList<SnakeCell> snake) {
        this.snake = snake;
    }

    private SnakeCell newSnakeCell(int x, int y) {
        SnakeCell cell = new SnakeCell(x, y);
        return cell;
    }

    public boolean moveUp() {
        boolean result = true;
        snake.add(0, new SnakeCell(snake.get(0).getCellCoordX(), snake.get(0).getCellCoordY() - 1));
        snake.remove(snake.size() - 1);
        return result;
    }

    public boolean moveDown() {
        boolean result = true;
        snake.add(0, new SnakeCell(snake.get(0).getCellCoordX(), snake.get(0).getCellCoordY() + 1));
        snake.remove(snake.size() - 1);
        return result;
    }

    public boolean moveLeft() {
        boolean result = true;
        snake.add(0, new SnakeCell(snake.get(0).getCellCoordX() - 1, snake.get(0).getCellCoordY()));
        snake.remove(snake.size() - 1);
        return result;
    }

    public boolean moveRight() {
        boolean result = true;
        snake.add(0, new SnakeCell(snake.get(0).getCellCoordX() + 1, snake.get(0).getCellCoordY()));
        snake.remove(snake.size() - 1);
        return result;
    }
}
