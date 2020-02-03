package pong;

import java.awt.*;

public class Paddle {

    private Color c = new Color(255, 255, 255);

    private int y = 0;
    private int x = 0;

    private int width = 0;
    private int height = 0;
    private int viewHeight = 0;

    public Paddle(int y, int x, int width, int height) {
        this.y = y / 2;
        this.viewHeight = y;
        this.x = x;
        this.width = width;
        this.height = height;
    }

    public Paddle(int y, int x, int width, int height, Color c) {
        this.y = y / 2;
        this.viewHeight = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.c = c;
    }

    public void draw(Graphics2D g) {
        g.setColor(c);
        g.fillRect(x - width / 2, y - height / 2, width, height);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void move(int i) {
        y -= i * 8.8;
        if (y < height / 2) {
            y = height / 2;
        }
        if (y > viewHeight - height / 2) {
            y = viewHeight - height / 2;
        }
    }

}
