package pong;

import java.awt.*;
import java.util.Random;

public class Ball {

    private int x = 80;
    private int y = 100;

    private Random random = new Random();
    private int dx = random.nextDouble() > 0.5 ? -(random.nextInt(2) + 9) : (random.nextInt(2) + 9);
    private int dy = random.nextInt(5) + 3;

    private int size = 15;

    private long time = System.currentTimeMillis();

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int update(Graphics2D g, Paddle[] paddles) {
        if (System.currentTimeMillis() - time < 3000) {
            return 0;
        }
        int prevx = x;
        int prevy = y;
        x += dx;
        y += dy;

        if (x < 0) {
            dx *= -1;
            x = prevx;
            return -1;
        }
        if (x > g.getClipBounds().width - size) {
            dx *= -1;
            x = prevx;
            return 1;
        }
        if (y < 0) {
            dy *= -1;
            y = prevy;
        }
        if (y > g.getClipBounds().height - size) {
            dy *= -1;
            y = prevy;
        }

        for (Paddle p : paddles) {
            if (checkXPositive(p) && checkYPositive(p) && checkYNegative(p) && x + size < p.getX() - p.getWidth() / 2 + size / 2) {
                dx *= -1;
                x = prevx;
            }
            if (checkXNegative(p) && checkYPositive(p) && checkYNegative(p) && x > p.getX() - p.getWidth() / 2 - size / 2) {
                dx *= -1;
                x = prevx;
            }
            if (checkYPositive(p) && checkXPositive(p) && checkXNegative(p) && y + size < p.getY() - p.getHeight() / 2 + size / 2) {
                dy *= -1;
                y = prevy;
                newDirection();
                Draw.offset = random.nextInt(60) - 30;
            }
            if (checkYNegative(p) && checkXPositive(p) && checkXNegative(p) && y > p.getY() - p.getHeight() / 2 - size / 2) {
                dy *= -1;
                y = prevy;
                newDirection();
                Draw.offset = random.nextInt(60) - 30;
            }
        }
        return 0;
    }

    private void newDirection() {
        if (random.nextDouble() > 0.8) {
            if (dy < 0) {
                dy = -(random.nextInt(5) + 3);
            } else {
                dy = random.nextInt(5) + 3;
            }
        }
    }

    private boolean checkXPositive(Paddle p) {
        return x + size > p.getX() - p.getWidth() / 2;
    }

    private boolean checkXNegative(Paddle p) {
        return x < p.getX() + p.getWidth() / 2;
    }

    private boolean checkYPositive(Paddle p) {
        return y + size > p.getY() - p.getHeight() / 2;
    }

    private boolean checkYNegative(Paddle p) {
        return y < p.getY() + p.getHeight() / 2;
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(255, 255, 255));
        g.fillOval(x, y, size, size);
    }

    public int getY() {
        return y;
    }

}
