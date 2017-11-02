package ru.kpecmuk.snakegame.gameobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 28.10.2017
 */
public class Coordinates implements GameObjects {
    private static final Logger log = LoggerFactory.getLogger(Coordinates.class);

    private final int coordX;
    private final int coordY;

    public final boolean equals(Coordinates o) {
        return (this == o) || (o != null) && (coordX == o.coordX) && (coordY == o.coordY);
    }

    Coordinates(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public final int getCoordX() {
        return this.coordX;
    }

    public final int getCoordY() {
        return this.coordY;
    }
}
