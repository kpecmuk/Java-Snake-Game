package ru.kpecmuk.apple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.game.Game;
import ru.kpecmuk.gameobjects.Apple;
import ru.kpecmuk.gameobjects.SnakeCell;
import ru.kpecmuk.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static ru.kpecmuk.game.Setup.*;

/**
 * @author kpecmuk
 * @since 25.10.2017
 */
public class Apples {
    private static final Logger log = LoggerFactory.getLogger(Apples.class);

    /**
     * Список яблок
     */
    private final ArrayList<Apple> apples;
    private final Graphics2D graphics;
    private final Utils utils;

    public Apples(Game game, int coordX, int coordY) {
        this.utils = game.getUtils();
        this.graphics = game.getDisplay().getGraphics();
        this.apples = new ArrayList<>();
        this.apples.add(new Apple(coordX, coordY));
    }

    /**
     * Для доступа к объекту из вне
     *
     * @return список с яблоками
     */
    public final ArrayList<Apple> getApples() {
        return this.apples;
    }

    /**
     * Генерация нового яблока
     *
     * @param snakeCells список с ячейками змейки, который будем перебирать
     *                   чтобы новое яблоко не оказалось внутри змейки
     */
    public final void addNewApple(final ArrayList<SnakeCell> snakeCells) {
        Random rand = new Random();
        int randomX, randomY;
        boolean result;

        do {
            result = true;
            randomX = rand.nextInt((FIELD_X_SIZE - 1) + 1);
            randomY = rand.nextInt((FIELD_Y_SIZE - 1) + 1);

            for (SnakeCell snakeCell : snakeCells) {
                if (randomX == snakeCell.getCoordX() && randomY == snakeCell.getCoordY()) {
                    log.error("Apple inside snake body, generating new coord");
                    result = false;
                    break;
                }
            }
        }
        while (!result);

        apples.add(new Apple(randomX, randomY));
        log.info("New apple generated");
    }

    /**
     * Отрисовка яблок согласно списку
     * Координаты получаем из списка
     */
    public final void drawApples() {
        for (final Apple apple : apples) {
            drawApple(apple.getCoordX(), apple.getCoordY());
        }
    }

    /**
     * Отрисовка яблока по координатам
     *
     * @param x координата для перевода её в пиксели на экране
     * @param y координата для перевода её в пиксели на экране
     */
    private void drawApple(int x, int y) {
        graphics.setColor(Color.RED);
        graphics.fill3DRect(utils.getConvert().toPixel(x), utils.getConvert().toPixel(y),
                CELL_SIZE - 5, CELL_SIZE - 5, true);
    }
}
