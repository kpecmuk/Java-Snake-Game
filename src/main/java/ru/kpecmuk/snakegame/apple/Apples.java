package ru.kpecmuk.snakegame.apple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.game.Game;
import ru.kpecmuk.snakegame.gameobjects.Apple;
import ru.kpecmuk.snakegame.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static ru.kpecmuk.snakegame.game.Game.*;

/**
 * Тут будет список с яблоками
 *
 * @author kpecmuk
 * @since 25.10.2017
 */

public class Apples {
    private static final Logger log = LoggerFactory.getLogger(Apples.class);

    private ArrayList<Apple> apples;
    private Graphics2D graphics;
    private Utils utils;

    public Apples(Game game, int coordX, int coordY) {
        this.utils = game.getUtils();
        this.graphics = game.getDisplay().getGraphics();
        this.apples = new ArrayList<>();
        this.apples.add(new Apple(coordX, coordY));
    }

    public ArrayList<Apple> getApples() {
        return this.apples;
    }

    public void addNewApple() {
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomX = rand.nextInt((FIELD_X_SIZE - 1) + 1);
        int randomY = rand.nextInt((FIELD_Y_SIZE - 1) + 1);

        apples.add(new Apple(randomX, randomY));
    }

    public void drawApples() {
        for (Apple apple : apples) {
            drawApple(apple.getCoordX(), apple.getCoordY());
        }
    }

    private void drawApple(int x, int y) {
        graphics.setColor(Color.RED);
        graphics.fill3DRect(utils.getConvert().toPixel(x), utils.getConvert().toPixel(y),
                CELL_SIZE - 5, CELL_SIZE - 5, true);
    }
}
