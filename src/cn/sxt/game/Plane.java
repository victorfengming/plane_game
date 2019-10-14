package cn.sxt.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{

    // 飞机的方向
    boolean left, up, right, down;

    public void drawSelf(Graphics graphics) {
        graphics.drawImage(img, (int) x, (int) y, null);
        x++;

    }

    public Plane(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public void addDirection(KeyEvent event) {
        switch (event.getKeyCode()) {
            case 37:
                left = true;
                break;

            case 38:
                up = true;
                break;

            case 39:
                right = true;

            case 40:
                down = true;
                break;
        }
    }

}
