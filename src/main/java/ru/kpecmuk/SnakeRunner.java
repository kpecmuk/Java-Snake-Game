package ru.kpecmuk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.game.Game;

/**
 * Змейка
 *
 * @author kpecmuk
 * @since 24.10.2017
 */
public class SnakeRunner {
    private static final Logger log = LoggerFactory.getLogger(SnakeRunner.class);

    public static void main(String[] args) {

        Game game = new Game();
        game.startGame();
    }
}
