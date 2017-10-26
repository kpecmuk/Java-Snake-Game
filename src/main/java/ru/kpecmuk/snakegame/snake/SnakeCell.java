package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 25.10.2017
 */

class SnakeCell {
    private static final Logger log = LoggerFactory.getLogger(SnakeCell.class);

    private final int cellCoordX;
    private final int cellCoordY;

    SnakeCell(int cellCoordX, int cellCoordY) {
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
    