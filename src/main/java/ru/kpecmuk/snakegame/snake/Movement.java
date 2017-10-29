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

    /**
     * Двигаем змейку. Берём направление и двигаем.
     *
     * @return false - если была достигнута граница поля
     */
    public boolean moveSnake() {
        boolean result = true;

        if (direction.getDirection().equals(Direction.directions.UP)) {
            if (canIGoUp()) {
                log.info("Moving UP");
                goUp();
            } else {
                log.info("ELSE moving RIGHT");
                direction.setDirect(Direction.directions.RIGHT);
                goRight();
                result = false;
            }
        }

        if (direction.getDirection().equals(Direction.directions.DOWN)) {
            if (canIGoDown()) {
                log.info("Moving DOWN");
                goDown();
            } else {
                log.info("ELSE moving LEFT");
                direction.setDirect(Direction.directions.LEFT);
                goLeft();
                result = false;
            }
        }

        if (direction.getDirection().equals(Direction.directions.RIGHT)) {
            if (canIGoRight()) {
                log.info("Moving RIGHT");
                goRight();
            } else {
                log.info("ELSE moving DOWN");
                direction.setDirect(Direction.directions.DOWN);
                goDown();
                result = false;
            }
        }

        if (direction.getDirection().equals(Direction.directions.LEFT)) {
            if (canIGoLeft()) {
                log.info("Moving LEFT");
                goLeft();
            } else {
                log.info("ELSE moving UP");
                direction.setDirect(Direction.directions.UP);
                goUp();
                result = false;
            }
        }
        return result;
    }

    /**
     * @return если можно двигаться вверх
     */
    private boolean canIGoUp() {
        return snakeCells.get(0).getCoordY() > 0;
    }

    /**
     * @return если можно двигаться влево
     */
    private boolean canIGoLeft() {
        return snakeCells.get(0).getCoordX() > 0;
    }

    /**
     * @return если можно двигаться вправо
     */
    private boolean canIGoRight() {
        return snakeCells.get(0).getCoordX() < FIELD_X_SIZE - 1;
    }

    /**
     * @return если можно двигаться вниз
     */
    private boolean canIGoDown() {
        return snakeCells.get(0).getCoordY() < FIELD_Y_SIZE - 1;
    }

    /**
     * Проверка не нашли ли яблоко.
     * Если нашли то удаляем и генерируем новое
     *
     * @return true если нашли
     */
    private boolean isAppleFound() {
        boolean result = false;

        for (Apple apple : applesObj.getApples()) {
            if (apple.compareTo(snakeCells.get(0)) == 0) {
                log.info("Apple found");
                applesObj.getApples().remove(apple);
                log.info("Apple removed");
                applesObj.addNewApple(snakeCells);
                log.info("New apple generated");
                reduceGameSpeed();
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Двигаемся вниз
     */
    private void goDown() {
        snakeCells.add(0, new SnakeCell(snakeCells.get(0).getCoordX(),
                snakeCells.get(0).getCoordY() + 1));
        if (!isAppleFound()) {
            removeTailCell();
        }
    }

    /**
     * Двигаемся вверх
     */
    private void goUp() {
        snakeCells.add(0, new SnakeCell(snakeCells.get(0).getCoordX(),
                snakeCells.get(0).getCoordY() - 1));
        if (!isAppleFound()) {
            removeTailCell();
        }
    }

    /**
     * Двигаемся влево
     */
    private void goLeft() {
        snakeCells.add(0, new SnakeCell(snakeCells.get(0).getCoordX() - 1,
                snakeCells.get(0).getCoordY()));
        if (!isAppleFound()) {
            removeTailCell();
        }
    }

    /**
     * Двигаемся вправо
     */
    private void goRight() {
        snakeCells.add(0, new SnakeCell(snakeCells.get(0).getCoordX() + 1,
                snakeCells.get(0).getCoordY()));
        if (!isAppleFound()) {
            removeTailCell();
        }
    }

    /**
     * Удаляем хвост
     */
    private void removeTailCell() {
        snakeCells.remove(snakeCells.size() - 1);
    }
}
