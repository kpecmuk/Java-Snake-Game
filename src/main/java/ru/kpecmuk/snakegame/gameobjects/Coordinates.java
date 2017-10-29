package ru.kpecmuk.snakegame.gameobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 28.10.2017
 */
public class Coordinates implements GameObjects, Comparable<Coordinates> {
    private static final Logger log = LoggerFactory.getLogger(Coordinates.class);

    private final int coordX;
    private final int coordY;

    Coordinates(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    /**
     * Сравнение координат головы и координат яблока
     *
     * @param coordinates отсюда берём только голову змейки
     * @return 0 если координаты двух объектов совпадут
     */
    public int compareTo(Coordinates coordinates) {
        int resultX;
        int resultY;

        resultX = Integer.compare(this.getCoordX(), coordinates.getCoordX());
        if (resultX != 0) return resultX;

        resultY = Integer.compare(this.getCoordY(), coordinates.getCoordY());
        if (resultY != 0) return resultY;

        return 0;
    }
}
