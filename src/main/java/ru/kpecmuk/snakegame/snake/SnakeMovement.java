package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static ru.kpecmuk.snakegame.game.Game.FIELD_X_SIZE;
import static ru.kpecmuk.snakegame.game.Game.FIELD_Y_SIZE;

/**
 * @author kpecmuk
 * @since 26.10.2017
 */

public class SnakeMovement {
    private static final Logger log = LoggerFactory.getLogger(SnakeMovement.class);

    private ArrayList<SnakeCell> snake;
    private SnakeHeading heading;

    SnakeMovement(ArrayList<SnakeCell> snake, SnakeHeading heading) {
        this.snake = snake;
        this.heading = heading;
    }

    public SnakeHeading getHeading() {
        return heading;
    }

    public void moveSnake(SnakeHeading snakeHeading) {

        if (this.heading.getHeading().equals(SnakeHeading.moving.UP)) {
            goUp();
        } else if (this.heading.getHeading().equals(SnakeHeading.moving.DOWN)) {
            goDown();
        } else if (this.heading.getHeading().equals(SnakeHeading.moving.LEFT)) {
            goLeft();
        } else if (this.heading.getHeading().equals(SnakeHeading.moving.RIGHT)) {
            goRight();
        } else {
            log.error("Moving to " + snakeHeading.getHeading());
        }
    }

    public boolean canIGoUp() {
        if (snake.get(0).getCellCoordY() >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canIGoLeft() {
        if (snake.get(0).getCellCoordX() >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canIGoRight() {
        if (snake.get(0).getCellCoordX() <= FIELD_X_SIZE - 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canIGoDown() {
        if (snake.get(0).getCellCoordY() <= FIELD_Y_SIZE - 2) {
            return true;
        } else {
            return false;
        }
    }

    private void goDown() {
        snake.add(0, new SnakeCell(snake.get(0).getCellCoordX(), snake.get(0).getCellCoordY() + 1));
        removeTailCell();
    }

    private void goUp() {
        snake.add(0, new SnakeCell(snake.get(0).getCellCoordX(), snake.get(0).getCellCoordY() - 1));
        removeTailCell();
    }

    private void goLeft() {
        snake.add(0, new SnakeCell(snake.get(0).getCellCoordX() - 1, snake.get(0).getCellCoordY()));
        removeTailCell();
    }

    private void goRight() {
        snake.add(0, new SnakeCell(snake.get(0).getCellCoordX() + 1, snake.get(0).getCellCoordY()));
        removeTailCell();
    }

    private void removeTailCell() {
        snake.remove(snake.size() - 1);
    }
}
