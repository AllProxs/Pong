package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private Draw draw;
    private boolean moveUp = false;
    private boolean moveDown = false;

    public KeyHandler(Draw draw) {
        this.draw = draw;
    }

    @Override
    public void keyTyped (KeyEvent e){


    }

    @Override
    public void keyPressed (KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            moveUp = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            moveDown = true;
        }
    }

    @Override
    public void keyReleased (KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            moveUp = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            moveDown = false;
        }
    }

    public void update() {
        int direction = 0;
        if (moveUp) {
            direction += 1;
        }
        if (moveDown) {
            direction -= 1;
        }
        draw.movePaddle(0, direction);
    }

}
