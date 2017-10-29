package ru.kpecmuk.snakegame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.graphics.Display;

/**
 * Опрос действий игрока
 *
 * @author kpecmuk
 * @since 28.10.2017
 */

public class UserInput {
    private static final Logger log = LoggerFactory.getLogger(UserInput.class);

    private Display display;

    UserInput(Display display) {
        this.display = display;
    }

    public int getKeyboardKey() {
        int result = display.getUserKey();
        display.clearUserKey();
        return result;
    }
}
