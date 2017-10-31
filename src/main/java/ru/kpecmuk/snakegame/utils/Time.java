package ru.kpecmuk.snakegame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.kpecmuk.snakegame.game.Game.GAME_SPEED;

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

    /*
        Время обновления заголовка окна
     */
    public boolean isTimeToUpdateTitle() {
        return this.updateTitleTime > getSecond();
    }

    public void increaseUpdateTitleTime() {
        this.updateTitleTime += this.elapsedTime;
    }

    public void clearUpdateTitleTime() {
        this.updateTitleTime = 0;
    }

    public long loadElapsedTime() {
        return this.elapsedTime;
    }

    public void saveElapsedTime() {
        this.elapsedTime = this.currentTime - this.lastTime;
    }

    /*
        А не пора ли двигать змейку?
     */
    public boolean isTimeToMoveSnake() {
        return ((currentTime - movedLastTime) > GAME_SPEED);
    }

    public void saveLastMovedTime() {
        this.movedLastTime = this.currentTime;
    }

    public void saveLastTime(long currentTime) {
        this.lastTime = currentTime;
    }

    public long getSystemTime() {
        return System.nanoTime();
    }

    public long loadCurrentTime() {
        return this.currentTime;
    }

    public void saveCurrentTime() {
        this.currentTime = getSystemTime();
    }
}
