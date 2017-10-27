package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.kpecmuk.snakegame.game.Game.FIELD_X_SIZE;
import static ru.kpecmuk.snakegame.game.Game.FIELD_Y_SIZE;

/**
 * @author kpecmuk
 * @since 26.10.2017
 */

public class SnakeMovement {
    private static final Logger log = LoggerFactory.getLogger(SnakeMovement.class);

    private Snake snake;

    SnakeMovement(Snake snake) {
        this.snake = snake;
    }

    public void moveSnake() {
        if (snake.getHeadingObj().heading().equals(SnakeHeading.moving.UP)) {
            goUp();
        }
        if (snake.getHeadingObj().heading().equals(SnakeHeading.moving.DOWN)) {
            goDown();
        }
        if (snake.getHeadingObj().heading().equals(SnakeHeading.moving.RIGHT)) {
            goRight();
        }
        if (snake.getHeadingObj().heading().equals(SnakeHeading.moving.LEFT)) {
            goLeft();
        }

        log.info("Moving to " + snake.getHeadingObj().heading());
    }

    public boolean canIGoUp() {
        return snake.getSnakeCells().get(0).getCellCoordY() > 0;
    }

    public boolean canIGoLeft() {
        return snake.getSnakeCells().get(0).getCellCoordX() > 0;
    }

    public boolean canIGoRight() {
        return snake.getSnakeCells().get(0).getCellCoordX() < FIELD_X_SIZE - 1;
    }

    public boolean canIGoDown() {
        return snake.getSnakeCells().get(0).getCellCoordY() < FIELD_Y_SIZE - 1;
    }

    private void goDown() {
        snake.getSnakeCells().add(0, new SnakeCell(snake.getSnakeCells().get(0).getCellCoordX(),
                snake.getSnakeCells().get(0).getCellCoordY() + 1));
        removeTailCell();
    }

    private void goUp() {
        snake.getSnakeCells().add(0, new SnakeCell(snake.getSnakeCells().get(0).getCellCoordX(),
                snake.getSnakeCells().get(0).getCellCoordY() - 1));
        removeTailCell();
    }

    private void goLeft() {
        snake.getSnakeCells().add(0, new SnakeCell(snake.getSnakeCells().get(0).getCellCoordX() - 1,
                snake.getSnakeCells().get(0).getCellCoordY()));
        removeTailCell();
    }

    private void goRight() {
        snake.getSnakeCells().add(0, new SnakeCell(snake.getSnakeCells().get(0).getCellCoordX() + 1,
                snake.getSnakeCells().get(0).getCellCoordY()));
        removeTailCell();
    }

    private void removeTailCell() {
        snake.getSnakeCells().remove(snake.getSnakeCells().size() - 1);
    }
}
