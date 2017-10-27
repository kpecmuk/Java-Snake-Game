package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.kpecmuk.snakegame.game.Game.FIELD_X_SIZE;
import static ru.kpecmuk.snakegame.game.Game.FIELD_Y_SIZE;

/**
 * @author kpecmuk
 * @since 26.10.2017
 */

public class Movement {
    private static final Logger log = LoggerFactory.getLogger(Movement.class);

    private Snake snake;

    Movement(Snake snake) {
        this.snake = snake;
    }

    public void moveSnake() {
        if (snake.directionObj().getDirection().equals(Direction.directions.UP)) {
            goUp();
        }
        if (snake.directionObj().getDirection().equals(Direction.directions.DOWN)) {
            goDown();
        }
        if (snake.directionObj().getDirection().equals(Direction.directions.RIGHT)) {
            goRight();
        }
        if (snake.directionObj().getDirection().equals(Direction.directions.LEFT)) {
            goLeft();
        }

        log.info("Moving to " + snake.directionObj().getDirection());
    }

    public boolean canIGoUp() {
        return snake.getCells().get(0).getCellCoordY() > 0;
    }

    public boolean canIGoLeft() {
        return snake.getCells().get(0).getCellCoordX() > 0;
    }

    public boolean canIGoRight() {
        return snake.getCells().get(0).getCellCoordX() < FIELD_X_SIZE - 1;
    }

    public boolean canIGoDown() {
        return snake.getCells().get(0).getCellCoordY() < FIELD_Y_SIZE - 1;
    }

    private void goDown() {
        snake.getCells().add(0, new Cell(snake.getCells().get(0).getCellCoordX(),
                snake.getCells().get(0).getCellCoordY() + 1));
        removeTailCell();
    }

    private void goUp() {
        snake.getCells().add(0, new Cell(snake.getCells().get(0).getCellCoordX(),
                snake.getCells().get(0).getCellCoordY() - 1));
        removeTailCell();
    }

    private void goLeft() {
        snake.getCells().add(0, new Cell(snake.getCells().get(0).getCellCoordX() - 1,
                snake.getCells().get(0).getCellCoordY()));
        removeTailCell();
    }

    private void goRight() {
        snake.getCells().add(0, new Cell(snake.getCells().get(0).getCellCoordX() + 1,
                snake.getCells().get(0).getCellCoordY()));
        removeTailCell();
    }

    private void removeTailCell() {
        snake.getCells().remove(snake.getCells().size() - 1);
    }
}
