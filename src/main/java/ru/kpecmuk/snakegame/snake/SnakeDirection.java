package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 26.10.2017
 */

public class SnakeDirection {
    private static final Logger log = LoggerFactory.getLogger(SnakeDirection.class);
    private directions direct;

    public directions getDirection() {
        return this.direct;
    }

    public void setDirect(directions direct) {
        this.direct = direct;
    }

    @Override
    public String toString() {
        return "Heading: " + direct;
    }

    public enum directions {UP, DOWN, LEFT, RIGHT}
}
