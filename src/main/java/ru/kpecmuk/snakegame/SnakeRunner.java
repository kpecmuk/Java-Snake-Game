package ru.kpecmuk.snakegame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.game.Game;
import ru.kpecmuk.snakegame.graphics.Display;

import static ru.kpecmuk.snakegame.game.Game.*;

/**
 * Змейка
 *
 * @author kpecmuk
 * @since 24.10.2017
 */
public class SnakeRunner {
    private static final Logger log = LoggerFactory.getLogger(SnakeRunner.class);

    public static void main(String[] args) {

        Display display = new Display(WINDOW_WIDTH, WINDOW_HEIGHT, CLEAR_COLOR, NUMBER_OF_BUFFERS, WINDOW_TITLE);

        Game game = new Game(display);
        game.startGame();
    }
}
