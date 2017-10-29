package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.apple.Apples;
import ru.kpecmuk.snakegame.game.Game;
import ru.kpecmuk.snakegame.gameobjects.SnakeCell;
import ru.kpecmuk.snakegame.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

import static ru.kpecmuk.snakegame.game.Game.CELL_SIZE;

/**
 * Основной класс змейки, отрисовка
 *
 * @author kpecmuk
 * @since 25.10.2017
 */
public class Snake {
    private static final Logger log = LoggerFactory.getLogger(Snake.class);

    private ArrayList<SnakeCell> snakeCells;
    private Direction direction;
    private Apples applesObj;
    private Movement movement; // кидаем ссылку на cells/direction/applesObj
    private Graphics2D graphics;
    private Utils utils;

    public Snake(Game game, int x, int y, Apples applesObj) {
        this.utils = game.getUtils();
        this.graphics = game.getDisplay().getGraphics();
        this.applesObj = applesObj;

        this.snakeCells = new ArrayList<>();
        this.snakeCells.add(new SnakeCell(x, y));
        this.snakeCells.add(new SnakeCell(x - 1, y));
        this.snakeCells.add(new SnakeCell(x - 2, y));

        this.direction = new Direction();
        this.movement = new Movement(snakeCells, direction, this.applesObj);
    }

    public Movement movementObj() {
        return this.movement;
    }

    public Direction directionObj() {
        return this.direction;
    }

    public ArrayList<SnakeCell> getSnakeCells() {
        return snakeCells;
    }

    @Override
    public String toString() {
        return snakeCells.get(0).getCoordX() + "  |  " + snakeCells.get(0).getCoordY();
    }

    public void drawSnake() {
        for (SnakeCell cell : snakeCells) {
            drawCell(cell.getCoordX(), cell.getCoordY());
        }
    }

    private void drawCell(int x, int y) {
        graphics.setColor(Color.green);
        graphics.fill3DRect(utils.getConvert().toPixel(x), utils.getConvert().toPixel(y),
                CELL_SIZE - 5, CELL_SIZE - 5, true);
    }
}
