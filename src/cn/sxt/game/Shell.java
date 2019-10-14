package cn.sxt.game;

import java.awt.*;

/**
 * 炮弹类
 * @author victor
 */
public class Shell extends GameObject{

    double degree;  //弧度

    public Shell() {
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 3;

        degree = Math.random()*Math.PI*2;
    }

    // 画炮弹
    public void draw(Graphics graphics) {
        // 保护现场
        Color c = graphics.getColor();

        graphics.setColor(Color.yellow);

        graphics.fillOval((int)x,(int)y,width,height);

        // 炮弹沿着任意角度去飞︿(￣︶￣)︿
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);

        if (x < 0 || x > Constant.GAME_WIDTH-width) {
            degree = Math.PI - degree;
        }
        if (y < 42 || y > Constant.GAME_HEIGHT-height) {
            degree = -degree;
        }

        // 还原现场
        graphics.setColor(c);
    }
}
