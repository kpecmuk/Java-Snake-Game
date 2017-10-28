package ru.kpecmuk.snakegame.apple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс со свойствами одного яблока.
 * Координаты яблока
 *
 * @author kpecmuk
 * @since 25.10.2017
 */

public class Apple {
    private static final Logger log = LoggerFactory.getLogger(Apple.class);

    private int appleCoordX;
    private int appleCoordY;

    Apple(int appleCoordX, int appleCoordY, boolean b) {
        this.appleCoordX = appleCoordX;
        this.appleCoordY = appleCoordY;
    }

    public int getAppleCoordX() {
        return this.appleCoordX;
    }

    public int getAppleCoordY() {
        return this.appleCoordY;
    }
}
