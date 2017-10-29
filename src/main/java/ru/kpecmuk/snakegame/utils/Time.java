package ru.kpecmuk.snakegame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Системное время
 *
 * @author kpecmuk
 * @since 24.10.2017
 */
public class Time {
    private static final Logger log = LoggerFactory.getLogger(Time.class);

    public long getTime() {
        return System.nanoTime();
    }

    public long getSecond() {
        return 1_000_000_000L;
    }
}
