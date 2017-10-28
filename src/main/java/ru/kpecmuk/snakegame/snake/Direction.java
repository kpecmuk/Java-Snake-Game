package ru.kpecmuk.snakegame.snake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 26.10.2017
 */

public class Direction {
    private static final Logger log = LoggerFactory.getLogger(Direction.class);
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

    public void changeDirection(int key) {
        if (key == 37 && direct != directions.RIGHT) {  // LEFT
            this.direct = directions.LEFT;
        } else if (key == 38 && direct != directions.DOWN) {    // UP
            this.direct = directions.UP;
        } else if (key == 39 && direct != directions.LEFT) {    // RIGHT
            this.direct = directions.RIGHT;
        } else if (key == 40 && direct != directions.UP) {      // DOWN
            this.direct = directions.DOWN;
        }
    }

    public enum directions {UP, DOWN, LEFT, RIGHT}
}
