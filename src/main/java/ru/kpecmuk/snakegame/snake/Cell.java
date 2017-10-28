package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Кусок змейки
 *
 * @author kpecmuk
 * @since 25.10.2017
 */

public class Cell {
    private static final Logger log = LoggerFactory.getLogger(Cell.class);

    private final int cellCoordX;
    private final int cellCoordY;

    Cell(int cellCoordX, int cellCoordY) {
        this.cellCoordX = cellCoordX;
        this.cellCoordY = cellCoordY;
    }

    public int getCellCoordX() {
        return this.cellCoordX;
    }

    public int getCellCoordY() {
        return this.cellCoordY;
    }
}
