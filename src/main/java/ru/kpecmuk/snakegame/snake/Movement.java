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
     * Проверяем можно ли двигаться вверх
     *
     * @return true если можно
     */
    private boolean canIGoUp() {
        return snakeCells.get(0).getCoordY() > 0;
    }

    /**
     * Проверяем можно ли двигаться вверх
     *
     * @return true если можно
     */
    private boolean canIGoLeft() {
        return snakeCells.get(0).getCoordX() > 0;
    }

    /**
     * Проверяем можно ли двигаться вправо
     *
     * @return true если можно
     */
    private boolean canIGoRight() {
        return snakeCells.get(0).getCoordX() < FIELD_X_SIZE - 1;
    }

    /**
     * Проверяем можно ли двигаться вверх
     *
     * @return true если можно
     */
    private boolean canIGoDown() {
        return snakeCells.get(0).getCoordY() < FIELD_Y_SIZE - 1;
    }

    /**
     * Проверяем на столкновение со змейкой
     *
     * @return true - если голова змейки нашла своё тело
     */
    public boolean isSnakeCellFound() {
        boolean result = false;

        for (int snakeCell = 1; snakeCell < snakeCells.size() - 1; snakeCell++) {
            if (snakeCells.get(0).equals(snakeCells.get(snakeCell))) {
                log.info("Snake body found");
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Проверка не нашли ли яблоко.
     * Если нашли то удаляем, генерируем новое, ускоряемся
     *
     * @return true если нашли
     */
    private boolean isAppleFound() {
        boolean result = false;

        for (Apple apple : applesObj.getApples()) {
            if (snakeCells.get(0).equals(apple)) {
                log.info("Apple found");
                applesObj.getApples().remove(apple);
                log.info("Apple removed");
                applesObj.addNewApple(snakeCells);
                increaseGameSpeed();
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
