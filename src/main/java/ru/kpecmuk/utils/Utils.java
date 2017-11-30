package ru.kpecmuk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.graphics.Display;

/**
 * Сборник утилит: Time / Convert / UserInput доступно отсюда
 *
 * @author kpecmuk
 * @since 28.10.2017
 */
public class Utils {
    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    private Convert convert;
    private Time time;
    private UserInput userInput;

    /**
     * Создаем объекты утилит
     *
     * @param display ссылка на объект Display
     */
    public Utils(Display display) {
        this.convert = new Convert();
        this.time = new Time();
        this.userInput = new UserInput(display);
    }

    /**
     * Для доступа к конвертеру
     *
     * @return ссылка на объект Convert
     */
    public Convert getConvert() {
        return this.convert;
    }

    /**
     * Для получения времени
     *
     * @return ссылка на объект Time
     */
    public Time getTime() {
        return this.time;
    }

    /**
     * Для опроса клавиатуры
     *
     * @return ссылка на объект UserInput
     */
    public UserInput getUserInput() {
        return this.userInput;
    }
}
