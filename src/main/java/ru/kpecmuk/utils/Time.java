package ru.kpecmuk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.kpecmuk.game.Game.GAME_SPEED;

/**
 * Системное время
 *
 * @author kpecmuk
 * @since 24.10.2017
 */
public class Time {
    private static final Logger log = LoggerFactory.getLogger(Time.class);

    private long lastTime;
    private long currentTime;
    private long elapsedTime;
    private long movedLastTime;
    private long updateTitleTime;

    public long getSecond() {
        return 1_000_000_000L;
    }

    /**
     * @return true если заголовок можно обновить
     */
    public boolean isTimeToUpdateTitle() {
        return this.updateTitleTime > getSecond();
    }

    /**
     * Увеличиваем счётчик времени до обновления заголовка окна
     */
    public void increaseUpdateTitleTime() {
        this.updateTitleTime += this.elapsedTime;
    }

    /**
     * Обнуляем счётчик обновления заголовка
     */
    public void clearUpdateTitleTime() {
        this.updateTitleTime = 0;
    }

    /**
     * @return сколько времени прошло между циклами
     */
    public long loadElapsedTime() {
        return this.elapsedTime;
    }

    /**
     * Вычисляем и сохраняем сколько прошло времени между циклами
     */
    public void saveElapsedTime() {
        this.elapsedTime = this.currentTime - this.lastTime;
    }

    /**
     * @return true если пора двигать змейку
     */
    public boolean isTimeToMoveSnake() {
        return ((currentTime - movedLastTime) > GAME_SPEED);
    }

    /**
     * Сохраняем время, когда последний раз двигали змейку
     */
    public void saveLastMovedTime() {
        this.movedLastTime = this.currentTime;
    }

    /**
     * @param time сохраняемое время
     */
    public void saveLastTime(long time) {
        this.lastTime = time;
    }

    /**
     * @return System.nanoTime()
     */
    public long getSystemTime() {
        return System.nanoTime();
    }

    /**
     * @return сохраненное текущее время
     */
    public long loadCurrentTime() {
        return this.currentTime;
    }

    /**
     * Сохраняем текущее время
     */
    public void saveCurrentTime() {
        this.currentTime = getSystemTime();
    }
}
