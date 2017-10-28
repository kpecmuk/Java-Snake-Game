package ru.kpecmuk.snakegame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.graphics.Display;

/**
 * @author kpecmuk
 * @since 28.10.2017
 */

public class Utils {
    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    private Convert convert;
    private Time time = new Time();
    private UserInput userInput;

    public Utils(Display display) {
        this.convert = new Convert();
        this.time = new Time();
        this.userInput = new UserInput(display);
    }

    public Convert getConvert() {
        return this.convert;
    }

    public Time getTime() {
        return this.time;
    }

    public UserInput getUserInput() {
        return this.userInput;
    }
}
