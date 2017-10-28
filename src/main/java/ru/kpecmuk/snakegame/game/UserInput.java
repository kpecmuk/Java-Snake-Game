package ru.kpecmuk.snakegame.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.graphics.Display;

/**
 * @author kpecmuk
 * @since 28.10.2017
 */

class UserInput {
    private static final Logger log = LoggerFactory.getLogger(UserInput.class);

    private Display display;

    UserInput(Display display) {
        this.display = display;
    }

    public int getUserKey() {
        int result = display.getUserKey();
        display.clearUserKey();
        return result;
    }
}
