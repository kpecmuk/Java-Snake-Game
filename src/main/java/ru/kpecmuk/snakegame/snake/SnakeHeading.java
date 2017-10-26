package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.UnresolvedPermission;

/**
 * @author kpecmuk
 * @since 26.10.2017
 */

public class SnakeHeading {
    private static final Logger log = LoggerFactory.getLogger(SnakeHeading.class);

    public enum moving {UP, DOWN, LEFT, RIGHT}

    private moving heading = moving.DOWN;

    public moving getHeading() {
        return heading;
    }

    public void setHeading(moving heading) {
        this.heading = heading;
    }

    @Override
    public String toString() {
        return "Heading: " + heading;
    }
}
    