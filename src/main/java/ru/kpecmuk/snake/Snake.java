package ru.kpecmuk.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.apple.Apples;
import ru.kpecmuk.game.Game;
import ru.kpecmuk.gameobjects.SnakeCell;
import ru.kpecmuk.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

import static ru.kpecmuk.game.Game.CELL_SIZE;

/**
 * Основной класс змейки, отрисовка
 *
 * @author kpecmuk
 * @since 25.10.2017
 */
public class Snake {
    private static final Logger log = LoggerFactory.getLogger(Snake.class);

    private final ArrayList<SnakeCell> snakeCells;
    private final Direction direction;
    private final Apples applesObj;
    private final Movement movement; // кидаем ссылку на cells/direction/applesObj
    private final Graphics2D graphics;
    private final Utils utils;

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

    public final Movement getMovement() {
        return this.movement;
    }

    public final Direction directionObj() {
        return this.direction;
    }

    public final ArrayList<SnakeCell> getSnakeCells() {
        return this.snakeCells;
    }

    @Override
    public final String toString() {
        return snakeCells.get(0).getCoordX() + "  |  " + snakeCells.get(0).getCoordY();
    }

    public final void drawSnake() {
        for (final SnakeCell cell : snakeCells) {
            drawCell(cell.getCoordX(), cell.getCoordY());
        }
    }

    private void drawCell(int x, int y) {
        graphics.setColor(Color.green);
        graphics.fill3DRect(utils.getConvert().toPixel(x), utils.getConvert().toPixel(y),
                CELL_SIZE - 5, CELL_SIZE - 5, true);
    }
}
