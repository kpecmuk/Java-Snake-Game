package ru.kpecmuk.snakegame.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.apple.Apples;
import ru.kpecmuk.snakegame.graphics.Display;
import ru.kpecmuk.snakegame.graphics.GameField;
import ru.kpecmuk.snakegame.snake.Snake;
import ru.kpecmuk.snakegame.snake.SnakeHeading;
import ru.kpecmuk.snakegame.utils.Time;

import java.awt.*;

/**
 * @author kpecmuk
 * @since 24.10.2017
 */

public class Game implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Game.class);

    public static final int FIELD_X_SIZE = 30;
    public static final int FIELD_Y_SIZE = 25;
    public static final int CELL_SIZE = 32;
    public static final int CLEAR_COLOR = 0xff_40_40_40;
    public static final int WINDOW_WIDTH = CELL_SIZE * FIELD_X_SIZE + 5;
    public static final int WINDOW_HEIGHT = CELL_SIZE * FIELD_Y_SIZE + 5;
    public static final String WINDOW_TITLE = "Snake";
    public static final int NUMBER_OF_BUFFERS = 3;
    private boolean isRunning;
    private Display display;
    private Graphics2D graphics;
    private Thread gameThread;
    private Time time;
    private static final long IDLE_TIME = 1;
    private static final float UPDATE_RATE = 60.0f;
    private static long GAME_SPEED = 200_000_000L;

    private GameField gameField;
    private Snake snake;
    private Apples apples;
    private boolean needToMove = false;
    private long moveLastTime = 0;

    public Game(Display display) {
        this.isRunning = false;
        this.display = display;
        this.display.createWindow();
        this.graphics = display.getGraphics();
        this.time = new Time();
        this.gameField = new GameField();
        this.snake = new Snake(this.graphics, FIELD_X_SIZE / 2, FIELD_Y_SIZE / 2);
        this.apples = new Apples(this.graphics, FIELD_X_SIZE / 4, FIELD_Y_SIZE / 4);
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

    private void doRender() {
        display.clear();

        gameField.drawField(graphics);
        apples.drawApples();
        snake.drawSnake();

        display.swapBuffers();
    }

    private void update() {
        apples.check();

        if (needToMove) {
            snake.getMovement().moveSnake();
            needToMove = false;
        }
    }

    @Override
    public void run() {
        float delta = 0;
        int fps = 0, upd = 0, updateLoops = 0;
        long count = 0;

        long lastTime = time.getTime();

        while (isRunning) {
            long currentTime = time.getTime();

            long elapsedTime = currentTime - lastTime;
            lastTime = currentTime;

            count += elapsedTime;
            boolean needRender = false;
            float UPDATE_INTERVAL = time.getSecond() / UPDATE_RATE;
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

                if (snake.getHeadingObj().heading().equals(SnakeHeading.moving.UP) && !snake.getMovement().canIGoUp()) {

                    log.info("UP -> RIGHT");
                    snake.getHeadingObj().setHeading(SnakeHeading.moving.RIGHT);
                }
                if (snake.getHeadingObj().heading().equals(SnakeHeading.moving.RIGHT) && !snake.getMovement().canIGoRight()) {
                    log.info("RIGHT -> DOWN");
                    snake.getHeadingObj().setHeading(SnakeHeading.moving.DOWN);
                }
                if (snake.getHeadingObj().heading().equals(SnakeHeading.moving.DOWN) && !snake.getMovement().canIGoDown()) {
                    log.info("DOWN -> LEFT");
                    snake.getHeadingObj().setHeading(SnakeHeading.moving.LEFT);
                }
                if (snake.getHeadingObj().heading().equals(SnakeHeading.moving.LEFT) && !snake.getMovement().canIGoLeft()) {
                    log.info("LEFT -> UP");
                    snake.getHeadingObj().setHeading(SnakeHeading.moving.UP);
                }
            }

            if (count >= time.getSecond()) {
                String title = WINDOW_TITLE + " || fps:" + fps + "  | Upd: " + upd + "  | Loops: " + updateLoops +
                        " | " + snake.getHeadingObj().heading() + " | " + snake.getSnakeCells().get(0).getCellCoordX() + "  |  " +
                        snake.getSnakeCells().get(0).getCellCoordY();
                display.setWindowTitle(title);
                count = upd = updateLoops = fps = 0;
            }
        }
    }
}
