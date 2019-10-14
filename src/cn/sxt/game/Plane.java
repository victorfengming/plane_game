package cn.sxt.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{

    // 飞机的方向
    boolean left, up, right, down;

    public void drawSelf(Graphics graphics) {
        graphics.drawImage(img, (int) x, (int) y, null);
        // 设置速度
        int speed = 3;

        if (left) {
            x -= speed;
        }
        if (right) {
            x += speed;
        }
        if (up) {
            y -= speed;
        }
        if (down) {
            y += speed;
        }

    }

    public Plane(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }


    public void addDirection(KeyEvent event) {
        System.out.println("xiaci"+ event.getKeyCode());
        switch (event.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                left = true;
                break;

            case KeyEvent.VK_UP:
                up = true;
                break;

            case KeyEvent.VK_RIGHT:
                right = true;
                break;

            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    // 按下某个键,取消响应的方向
    public void minusDirection(KeyEvent event) {
        switch (event.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                left = false;
                break;

            case KeyEvent.VK_UP:
                up = false;
                break;

            case KeyEvent.VK_RIGHT:
                right = false;

            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

}
