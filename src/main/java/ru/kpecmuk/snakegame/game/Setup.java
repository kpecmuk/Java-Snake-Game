package ru.kpecmuk.snakegame.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.graphics.Display;

/**
 * @author kpecmuk
 * @since 01.11.2017
 */

public class Setup {
    public static final int FIELD_X_SIZE = 30;
    public static final int FIELD_Y_SIZE = 25;
    public static final int CELL_SIZE = 32;
    public static final int WINDOW_WIDTH = CELL_SIZE * FIELD_X_SIZE + 5;
    public static final int WINDOW_HEIGHT = CELL_SIZE * FIELD_Y_SIZE + 5;
    static final String WINDOW_TITLE = "Java-Snake-Game";
    static final long IDLE_TIME = 1;
    static final float UPDATE_RATE = 60.0f;
    private static final Logger log = LoggerFactory.getLogger(Setup.class);
    private static final int CLEAR_COLOR = 0xff_40_40_40;
    private static final int NUMBER_OF_BUFFERS = 3;
    private Display display;

    Setup() {
        this.display = new Display(WINDOW_WIDTH, WINDOW_HEIGHT, CLEAR_COLOR, NUMBER_OF_BUFFERS, WINDOW_TITLE);
        this.display.createWindow();
    }

    public Display getDisplay() {
        return this.display;
    }

    void cleanUp() {
        display.destroyWindow();
    }
}
