package ru.kpecmuk.snakegame.graphics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

/**
 * Класс грифики
 *
 * @author kpecmuk
 * @since 24.10.2017
 */

public class Display {
    private static final Logger log = LoggerFactory.getLogger(Display.class);

    private int windowWidth, windowHeight, clearColor, numBuffers;
    private String windowTitle;
    private boolean isCreated;
    private BufferedImage buffer;
    private int[] bufferData;
    private Graphics graphicsBuffer;
    private BufferStrategy bufferStrategy;
    private JFrame window;
    private int key = 0;

    public Display(int windowWidth, int windowHeight, int clearColor, int numBuffers, String windowTitle) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.clearColor = clearColor;
        this.numBuffers = numBuffers;
        this.windowTitle = windowTitle;
        this.isCreated = false;
    }

    public int getUserKey() {
        return this.key;
    }

    public Graphics2D getGraphics() {
        return (Graphics2D) this.graphicsBuffer;
    }

    public void createWindow() {
        if (isCreated) return;

        window = new JFrame(this.windowTitle);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // чтобы останавливалась программа

        Dimension size = new Dimension(this.windowWidth, this.windowHeight);

        Canvas content = new Canvas();
        content.setPreferredSize(size);
        window.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                key = e.getKeyCode();
            }
        });

        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);     // меняет позицию окна на экране
        window.setVisible(true);

        buffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        graphicsBuffer = buffer.getGraphics();
        ((Graphics2D) graphicsBuffer).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        isCreated = true;
    }

    public void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }

    public void destroyWindow() {
        if (!isCreated) return;
        window.dispose();
    }

    public void setWindowTitle(String title) {
        this.window.setTitle(title);
    }

    public void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public void clearUserKey() {
        this.key = 0;
    }
}
