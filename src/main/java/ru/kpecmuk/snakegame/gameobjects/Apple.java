package ru.kpecmuk.snakegame.gameobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 28.10.2017
 */

public class Apple extends Coordinates implements Comparable<SnakeCell> {
    private static final Logger log = LoggerFactory.getLogger(Apple.class);

    public Apple(int coordX, int coordY) {
        super(coordX, coordY);
    }

    /**
     * Сравнение координат головы и координат яблока
     *
     * @param snakeCell отсюда берём только голову змейки
     * @return 0 если координаты двух объектов совпадут
     */
    @Override
    public int compareTo(SnakeCell snakeCell) {
        int resultX;
        int resultY;

        resultX = Integer.compare(this.getCoordX(), snakeCell.getCoordX());
        if (resultX != 0) return resultX;

        resultY = Integer.compare(this.getCoordY(), snakeCell.getCoordY());
        if (resultY != 0) return resultY;

        return 0;
    }
}
