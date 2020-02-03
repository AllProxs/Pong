package pong;

import javax.swing.*;
import java.awt.*;

public class Pong {

    public static JFrame frame;

    public static KeyHandler keyHandler = null;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setTitle("Pong");
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 4 * 3, Toolkit.getDefaultToolkit().getScreenSize().height / 4 * 3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.setResizable(false);

        //frame.setFocusTraversalKeysEnabled(false);

        Draw draw = new Draw();
        frame.setContentPane(draw);
        keyHandler = new KeyHandler(draw);
        frame.addKeyListener(keyHandler);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    draw.repaint();
                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.setName("Frame Updater");
        t.start();

        frame.invalidate();
    }

}
