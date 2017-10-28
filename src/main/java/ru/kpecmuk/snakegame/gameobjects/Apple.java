package ru.kpecmuk.snakegame.gameobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 28.10.2017
 */

public class Apple extends Coordinates {
    private static final Logger log = LoggerFactory.getLogger(Apple.class);

    public Apple(int coordX, int coordY) {
        super(coordX, coordY);
    }
}
    