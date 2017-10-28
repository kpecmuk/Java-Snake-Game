package ru.kpecmuk.snakegame.apple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.utils.Convert;

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

public class Apples extends Convert {
    private static final Logger log = LoggerFactory.getLogger(Apples.class);

    private ArrayList<Apple> apples;
    private Graphics2D graphics;

    public Apples(Graphics2D graphics, int coordX, int coordY) {
        this.graphics = graphics;
        this.apples = new ArrayList<>();
        this.apples.add(new Apple(coordX, coordY, false));
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

        apples.add(new Apple(randomX, randomY, false));
    }

    public void drawApples() {
        for (Apple apple : apples) {
            drawApple(apple.getAppleCoordX(), apple.getAppleCoordY());
        }
    }

    private void drawApple(int x, int y) {
        graphics.setColor(Color.RED);
        graphics.fill3DRect(toPixel(x), toPixel(y), CELL_SIZE - 5, CELL_SIZE - 5, true);
    }

    public int getCoordX(Apple apple) {
        return apple.getAppleCoordX();
    }

    public int getCoordY(Apple apple) {
        return apple.getAppleCoordY();
    }
}
