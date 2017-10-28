package ru.kpecmuk.snakegame.gameobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 28.10.2017
 */

public abstract class Coordinates implements I_GameObjects {
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
}
