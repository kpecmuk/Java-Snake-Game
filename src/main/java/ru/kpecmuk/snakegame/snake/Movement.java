package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.apple.Apples;
import ru.kpecmuk.snakegame.gameobjects.Apple;
import ru.kpecmuk.snakegame.gameobjects.SnakeCell;

import java.util.ArrayList;

import static ru.kpecmuk.snakegame.game.Game.*;

/**
 * Движение змейки, проверка на столкновение с границами.
 *
 * @author kpecmuk
 * @since 26.10.2017
 */

public class Movement {
    private static final Logger log = LoggerFactory.getLogger(Movement.class);

    private ArrayList<SnakeCell> snakeCells;
    private Direction direction;
    private Apples applesObj;

    Movement(ArrayList<SnakeCell> snakeCells, Direction direction, Apples applesObj) {
        this.snakeCells = snakeCells;
        this.direction = direction;
        this.applesObj = applesObj;
    }

    public void moveSnake() {
        if (direction.getDirection().equals(Direction.directions.UP)) {
            goUp();
        }
        if (direction.getDirection().equals(Direction.directions.DOWN)) {
            goDown();
        }
        if (direction.getDirection().equals(Direction.directions.RIGHT)) {
            goRight();
        }
        if (direction.getDirection().equals(Direction.directions.LEFT)) {
            goLeft();
        }

        log.info("Moving to " + direction.getDirection());
    }

    public boolean canIGoUp() {
        return snakeCells.get(0).getCoordY() > 0;
    }

    public boolean canIGoLeft() {
        return snakeCells.get(0).getCoordX() > 0;
    }

    public boolean canIGoRight() {
        return snakeCells.get(0).getCoordX() < FIELD_X_SIZE - 1;
    }

    public boolean canIGoDown() {
        return snakeCells.get(0).getCoordY() < FIELD_Y_SIZE - 1;
    }

    private boolean appleFoundCheck() {
        boolean result = false;

        for (Apple apple : applesObj.getApples()) {
            if (apple.getCoordX() == snakeCells.get(0).getCoordX()
                    && apple.getCoordY() == snakeCells.get(0).getCoordY()) {
                log.info("Apple found");
                applesObj.getApples().remove(apple);
                log.info("Apple removed");
                applesObj.addNewApple();
                log.info("New apple generated");
                reduceGameSpeed();
                result = true;
                break;
            }
        }
        return result;
    }

    private void goDown() {
        snakeCells.add(0, new SnakeCell(snakeCells.get(0).getCoordX(),
                snakeCells.get(0).getCoordY() + 1));
        if (!appleFoundCheck()) {
            removeTailCell();
        }
    }

    private void goUp() {
        snakeCells.add(0, new SnakeCell(snakeCells.get(0).getCoordX(),
                snakeCells.get(0).getCoordY() - 1));
        if (!appleFoundCheck()) {
            removeTailCell();
        }
    }

    private void goLeft() {
        snakeCells.add(0, new SnakeCell(snakeCells.get(0).getCoordX() - 1,
                snakeCells.get(0).getCoordY()));
        if (!appleFoundCheck()) {
            removeTailCell();
        }
    }

    private void goRight() {
        snakeCells.add(0, new SnakeCell(snakeCells.get(0).getCoordX() + 1,
                snakeCells.get(0).getCoordY()));
        if (!appleFoundCheck()) {
            removeTailCell();
        }
    }

    private void removeTailCell() {
        snakeCells.remove(snakeCells.size() - 1);
    }
}
