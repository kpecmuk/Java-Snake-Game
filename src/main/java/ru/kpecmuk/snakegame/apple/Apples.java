package ru.kpecmuk.snakegame.apple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.utils.Convert;

import java.awt.*;
import java.util.ArrayList;

import static ru.kpecmuk.snakegame.game.Game.CELL_SIZE;

/**
 * Тут будет список с яблоками
 *
 * @author kpecmuk
 * @since 25.10.2017
 */

public class Apples extends Convert {
    private static final Logger log = LoggerFactory.getLogger(Apples.class);

    private ArrayList<Apple> apples = new ArrayList<>();
    private Graphics2D graphics;

    public Apples(Graphics2D graphics, int coordX, int coordY) {
        this.graphics = graphics;
        apples.add(new Apple(coordX, coordY, false));
    }

    public void addNewApple() {


//        apples.add(new Apple(coordX, coordY, false));
    }

    public void drawApples() {
        for (Apple apple : apples) {
            if (!apple.isEaten()) {
                drawApple(apple.getAppleCoordX(), apple.getAppleCoordY());
            }
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

    public boolean isEaten(Apple apple) {
        return apple.isEaten();
    }

    public void eatApple(Apple apple) {
        apple.setEaten(true);
    }

    public void check() {
        for (Apple apple : apples) {
            if (apple.isEaten()) {
                apples.remove(apple);
            }
        }
    }
}
