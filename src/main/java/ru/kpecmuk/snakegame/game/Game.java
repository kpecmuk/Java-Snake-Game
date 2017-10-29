package ru.kpecmuk.snakegame.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.apple.Apples;
import ru.kpecmuk.snakegame.gameobjects.SnakeCell;
import ru.kpecmuk.snakegame.graphics.Display;
import ru.kpecmuk.snakegame.graphics.GameField;
import ru.kpecmuk.snakegame.snake.Direction;
import ru.kpecmuk.snakegame.snake.Snake;
import ru.kpecmuk.snakegame.utils.Utils;

import java.awt.*;

/**
 * @author kpecmuk
 * @since 24.10.2017
 */
public class Game implements Runnable {
    public static final int FIELD_X_SIZE = 30;
    public static final int FIELD_Y_SIZE = 25;
    public static final int CELL_SIZE = 32;
    public static final int CLEAR_COLOR = 0xff_40_40_40;
    public static final int WINDOW_WIDTH = CELL_SIZE * FIELD_X_SIZE + 5;
    public static final int WINDOW_HEIGHT = CELL_SIZE * FIELD_Y_SIZE + 5;
    public static final String WINDOW_TITLE = "SnakeCell";
    public static final int NUMBER_OF_BUFFERS = 3;
    private static final Logger log = LoggerFactory.getLogger(Game.class);
    private static final long IDLE_TIME = 1;
    private static final float UPDATE_RATE = 60.0f;
    private static long GAME_SPEED = 250_000_000L;
    private boolean isRunning;
    private Display display;
    private Graphics2D graphics;
    private Thread gameThread;
    private Utils utils;
    private boolean newUserAction = false;

    private GameField gameField;
    private Snake snake;
    private Apples applesObj;
    private boolean needToMove = false;
    private long moveLastTime = 0;
    private int userActionKey = 0;
    private boolean demoMode;

    public Game(Display display) {
        this.isRunning = false;
        this.demoMode = true;
        this.display = display;
        this.display.createWindow();
        this.utils = new Utils(display);
        this.graphics = display.getGraphics();
        this.gameField = new GameField();
        this.applesObj = new Apples(this, FIELD_X_SIZE / 4, FIELD_Y_SIZE / 4);
        this.snake = new Snake(this, FIELD_X_SIZE / 2, FIELD_Y_SIZE / 2, applesObj);
        this.snake.directionObj().setDirect(Direction.directions.UP);
    }

    /**
     * С каждым скушанным яблоком увеличивается скорость змейки
     */
    public static void reduceGameSpeed() {
        GAME_SPEED -= 3_000_000;
    }

    public Utils getUtils() {
        return this.utils;
    }

    public Display getDisplay() {
        return this.display;
    }

    public synchronized void startGame() {
        if (isRunning) return;

        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    synchronized void stopGame() {
        if (!isRunning) return;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            log.error(String.valueOf(e));
        } finally {
            this.isRunning = false;
            cleanUp();
        }
    }

    private void cleanUp() {
        display.destroyWindow();
    }

    /**
     * Обнуляем списки  игровых объектов и начинаем игру заново
     */
    private void doGameOver() {
        demoMode = true;
        GAME_SPEED = 250_000_000L;
        snake.getSnakeCells().clear();
        snake.getSnakeCells().add(new SnakeCell(FIELD_X_SIZE / 2, FIELD_Y_SIZE / 2));
        snake.getSnakeCells().add(new SnakeCell(FIELD_X_SIZE / 2 + 1, FIELD_Y_SIZE / 2));
        snake.directionObj().setDirect(Direction.directions.LEFT);

        applesObj.getApples().clear();
        applesObj.addNewApple(snake.getSnakeCells());
    }

    /**
     * Отрисовываем игровые объекты в буфере экрана.
     * Очищаем, рисуем сетку, рисуем яблоки, рисуем змейку.
     * Затем переключаем буфер и его содержимое показывается на экране.
     */
    private void doRender() {
        display.clear();

        gameField.drawField(graphics);
        applesObj.drawApples();
        snake.drawSnake();

        display.swapBuffers();
    }

    /**
     * Когда нажата любая кнопка: отключается демо режим. В случае столкновения произойдет обнуление
     * игровых данных. В демо режиме происходит поворот. Ну тоесть поворот уже произошёл, но именно потому что
     * он произошёл - определяем что было столкновение с границей экрана.
     */
    private void update() {
        if (!newUserAction) userActionKey = utils.getUserInput().getKeyboardKey();

        if (userActionKey != 0) {
            demoMode = false;
            newUserAction = true;
            log.info(String.valueOf(userActionKey));
            snake.directionObj().changeDirection(userActionKey);
            log.info(String.valueOf(snake.directionObj().getDirection()));
        }

        if (needToMove) {
            needToMove = false;
            newUserAction = false;
            userActionKey = 0;

            if ((!snake.movementObj().moveSnake()) && (!demoMode)) {
                doGameOver();
            }
        }
    }

    @Override
    public void run() {
        float delta = 0;
        int fps = 0, upd = 0, updateLoops = 0;
        long count = 0;

        long lastTime = utils.getTime().getTime();

        while (isRunning) {
            long currentTime = utils.getTime().getTime();

            long elapsedTime = currentTime - lastTime;
            lastTime = currentTime;

            count += elapsedTime;
            boolean needRender = false;
            float UPDATE_INTERVAL = utils.getTime().getSecond() / UPDATE_RATE;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (needRender) {
                    updateLoops++;
                } else {
                    needRender = true;
                }
            }
            if (needRender) {
                doRender();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    log.error(String.valueOf(e));
                }
            }

            if (currentTime - moveLastTime > GAME_SPEED) {
                needToMove = true;
                moveLastTime = currentTime;
            }

            if (count >= utils.getTime().getSecond()) {
                String title = WINDOW_TITLE + " || fps:" + fps + "  | Upd: " + upd + "  | Loops: " + updateLoops +
                        " | " + snake.directionObj().getDirection() + " | " + snake.getSnakeCells().get(0).getCoordX() + "  |  " +
                        snake.getSnakeCells().get(0).getCoordY();
                display.setWindowTitle(title);
                count = upd = updateLoops = fps = 0;
            }
        }
    }
}
