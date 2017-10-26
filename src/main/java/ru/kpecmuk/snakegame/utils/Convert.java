package ru.kpecmuk.snakegame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.kpecmuk.snakegame.game.Game.CELL_SIZE;

/**
 * @author kpecmuk
 * @since 25.10.2017
 */

public class Convert {
    private static final Logger log = LoggerFactory.getLogger(Convert.class);

    protected int toPixel(int position) {
        return CELL_SIZE * position + 5;
    }
}
    