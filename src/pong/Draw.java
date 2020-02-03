package pong;

import javax.swing.*;
import java.awt.*;

public class Draw extends JComponent {

    private Ball ball = null;

    private Paddle[] paddles = null;

    public static int offset = 0;

    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        if (paddles == null) {
            paddles = new Paddle[2];
            paddles[0] = new Paddle(g.getClipBounds().height, 40, 20, 120);
            paddles[1] = new Paddle(g.getClipBounds().height, g.getClipBounds().width - 40, 20, 120);
        }
        if (ball == null) {
            ball = new Ball(g.getClipBounds().width / 2 - 15 / 2, g.getClipBounds().height / 2 - 15 / 2);
        }

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(10, 10, 10));
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);

        ball.draw(g);
        int value = ball.update(g, paddles);

        for (Paddle p : paddles) {
            p.draw(g);
        }

        Pong.keyHandler.update();

        int difference = paddles[1].getY() - ball.getY() - offset;
        if (difference > 0) {
            difference = 1;
        } else if (difference < 0) {
            difference = -1;
        }
        if (Math.random() > 0.75) {
            difference = 0;
        }
        movePaddle(1, difference);

        if (value != 0) {
            ball = null;
        }
    }

    public void movePaddle(int index, int direction) {
        paddles[index].move(direction);
    }

}
