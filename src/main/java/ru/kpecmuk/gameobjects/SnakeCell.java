package ru.kpecmuk.gameobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Звено змейки, содержащее координаты объектов
 *
 * @author kpecmuk
 * @since 28.10.2017
 */
public class SnakeCell extends Coordinates {
    private static final Logger log = LoggerFactory.getLogger(SnakeCell.class);

    /**
     * @param coordX координата расположения ячейки по X
     * @param coordY координата расположения ячейки по Y
     */
    public SnakeCell(int coordX, int coordY) {
        super(coordX, coordY);
    }
}
