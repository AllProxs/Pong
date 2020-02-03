package pong;

import javax.swing.*;
import java.awt.*;

public class Draw extends JComponent {

    private Ball ball = null;

    private Paddle[] paddles = null;
    private int[] points = new int[2];

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
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        String s = points[0] + " : " + points[1];
        g.drawString(s, g.getClipBounds().width / 2 - g.getFontMetrics().stringWidth(s) / 2, 40);

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
        if (Math.random() > 0.70) {
            difference = 0;
        }
        movePaddle(1, difference);

        if (value != 0) {
            ball = null;
            if (value > 0) {
                points[0]++;
            } else {
                points[1]++;
            }
        }
    }

    public void movePaddle(int index, int direction) {
        paddles[index].move(direction);
    }

}
