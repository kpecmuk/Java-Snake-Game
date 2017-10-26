package ru.kpecmuk.snakegame.apple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс со свойствами одного яблока.
 * Координаты яблока и съеденно ли оно
 *
 * @author kpecmuk
 * @since 25.10.2017
 */

class Apple {
    private static final Logger log = LoggerFactory.getLogger(Apple.class);

    private int appleCoordX;
    private int appleCoordY;
    private boolean isEaten;

    boolean isEaten() {
        return this.isEaten;
    }

    void setEaten(boolean eaten) {
        this.isEaten = eaten;
    }

    Apple(int appleCoordX, int appleCoordY, boolean b) {
        this.appleCoordX = appleCoordX;
        this.appleCoordY = appleCoordY;
    }

    int getAppleCoordX() {
        return this.appleCoordX;
    }

    int getAppleCoordY() {
        return this.appleCoordY;
    }
}
