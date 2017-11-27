package ru.kpecmuk.snakegame.graphics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

import static ru.kpecmuk.snakegame.game.Game.WINDOW_HEIGHT;
import static ru.kpecmuk.snakegame.game.Game.WINDOW_WIDTH;

/**
 * Рисуем игровую сетку
 *
 * @author kpecmuk
 * @since 25.10.2017
 */
public class GameField {
    private static final Logger log = LoggerFactory.getLogger(GameField.class);

    public void drawField(Graphics2D graphics) {

        graphics.setColor(Color.BLACK);
        for (int i = 2; i <= WINDOW_WIDTH; i += 32) {
            graphics.drawLine(i, 2, i, WINDOW_HEIGHT - 3);
        }

        for (int i = 2; i <= WINDOW_HEIGHT; i += 32) {
            graphics.drawLine(2, i, WINDOW_WIDTH - 3, i);
        }
    }
}
