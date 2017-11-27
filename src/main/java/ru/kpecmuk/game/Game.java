package ru.kpecmuk.snakegame.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.snakegame.apple.Apples;
import ru.kpecmuk.snakegame.gameobjects.SnakeCell;
import ru.kpecmuk.snakegame.graphics.GameField;
import ru.kpecmuk.snakegame.snake.Direction;
import ru.kpecmuk.snakegame.snake.Snake;
import ru.kpecmuk.snakegame.utils.Utils;

import java.awt.*;

/**
 * @author kpecmuk
 * @since 24.10.2017
 */
public class Game extends Setup implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Game.class);

    public static long GAME_SPEED = 250_000_000L;
    private final Graphics2D graphics;
    private final Utils utils;
    private final GameField gameField;
    private final Snake snake;
    private final Apples applesObj;
    private boolean isRunning;
    private Thread gameThread;
    private boolean newUserAction = false;
    private boolean needToMove = false;
    private int userActionKey = 0;
    private boolean demoMode;

    public Game() {
        this.isRunning = false;
        this.demoMode = true;
        this.utils = new Utils(getDisplay());
        this.graphics = getDisplay().getGraphics();
        this.gameField = new GameField();
        this.applesObj = new Apples(this, FIELD_X_SIZE / 4, FIELD_Y_SIZE / 4);
        this.snake = new Snake(this, FIELD_X_SIZE / 2, FIELD_Y_SIZE / 2, applesObj);
        this.snake.directionObj().setDirect(Direction.directions.LEFT);
    }

    /**
     * С каждым скушанным яблоком увеличивается скорость змейки
     */
    public static void increaseGameSpeed() {
        log.info("Game speed increased");
        GAME_SPEED -= 3_500_000;
    }

    public final Utils getUtils() {
        return this.utils;
    }

    public final synchronized void startGame() {
        if (isRunning) return;

        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    final synchronized int stopGame() {
        if (!isRunning) return 1;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            log.error(String.valueOf(e));
        } finally {
            this.isRunning = false;
            cleanUp();
            return 0;
        }
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
        getDisplay().clear();

        gameField.drawField(graphics);
        applesObj.drawApples();
        snake.drawSnake();

        getDisplay().swapBuffers();
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
            snake.directionObj().changeDirection(userActionKey);
            log.info(String.valueOf(snake.directionObj().getDirection()));
        }

        if (needToMove) {
            needToMove = false;
            newUserAction = false;
            userActionKey = 0;

            if ((!snake.getMovement().moveSnake() && !demoMode) || (snake.getMovement().isSnakeCellFound())) {
                log.info("!!! GAME OVER !!!");
                doGameOver();
            }
        }
    }

    /**
     * Сюда лучше не смотреть. Тут такая херня творится что ппц.
     */
    @Override
    public final void run() {
        float delta = 0;
        int fps = 0, upd = 0, updateLoops = 0;
        utils.getTime().clearUpdateTitleTime();     // сброс счётчика обновления заголовка окна
        utils.getTime().saveLastTime(utils.getTime().getSystemTime()); // устанавливаем текущее время

        while (isRunning) {
            utils.getTime().saveCurrentTime();      // сохраняем текущее время
            utils.getTime().saveElapsedTime();      // вычистяем сколько времени прошло с последнего захода сюда
            utils.getTime().saveLastTime(utils.getTime().loadCurrentTime());// обновляем время последнего захода
            utils.getTime().increaseUpdateTitleTime(); // накручиваем счётчик времени обновления заголовка

            boolean needRender = false;
            float UPDATE_INTERVAL = utils.getTime().getSecond() / UPDATE_RATE;
            delta += (utils.getTime().loadElapsedTime() / UPDATE_INTERVAL);
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

            /*
                Двигаем змейку если надо и сохраняем время
             */
            if (utils.getTime().isTimeToMoveSnake()) {
                needToMove = true;
                utils.getTime().saveLastMovedTime();
            }

            /*
                Каждую секунду обновляем заголовок окна
             */
            if (utils.getTime().isTimeToUpdateTitle()) {
                String title = WINDOW_TITLE + " || fps:" + fps + "  | Upd: " + upd + "  | Loops: " + updateLoops +
                        " | " + snake.directionObj().getDirection();
                getDisplay().setWindowTitle(title);
                utils.getTime().clearUpdateTitleTime();
                upd = 0;
                updateLoops = 0;
                fps = 0;
            }
        }
    }
}
