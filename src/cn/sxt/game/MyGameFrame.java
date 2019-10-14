package cn.sxt.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 飞机游戏的主窗口
 * @author 秋叶夏风
 */
public class MyGameFrame extends Frame {
//    JFrame还是有闪烁的问题,还是用Frame吧,加入双缓冲技术就行了

    //    加载图片对象
    Image plane_img = GameUtil.getImage("images/planesm.png");
    Image bg = GameUtil.getImage("images/bgsm.png");

    Plane plane = new Plane(plane_img,250,250);
    int plane_x = 500;
    int plane_y = 250;

    // 画炮弹出来
    Shell[] shells = new Shell[50];

    @Override
    public void paint(Graphics g) {

        g.drawImage(bg, 0, 0, null);
        g.drawImage(plane_img, plane_x, plane_y, null);

        // 画飞机
        plane.drawSelf(g);

        // 循环画炮弹
        for (int i = 0; i < shells.length; i++) {
            shells[i].draw(g);
            boolean p = shells[i].getRect().intersects(plane.getRect());
            if (p) {
                System.out.println("相撞了");
            }
        }

    }

    /*
     * 线程:这一个程序要是想要跑起来需要多个线程
     * 很多线程来配合的
     *
     * 内部类可以直接使用外部的属性
     * 定义在另一个类的里面,这个类就可以使用属性和方法,随便用的那种
     * */

    /**
     * 帮助我们反复重画窗口
     */
    class PaintThread extends Thread{

        @Override
        public void run() {
            while (true) {

//                System.out.println("窗口重画一次");

                // 重画方法
                repaint();
                // 暂停一下
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 定义键盘监听的内部类
     */

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
//            System.out.println("anxia"+e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
//            System.out.println("songkai"+e.getKeyCode());
        }
    }

    /**
     * 初始化窗口
     */
    public void launchFrame() {
        this.setTitle("秋叶夏风_程序员作品");
        this.setVisible(true);
        // 给窗口设置大小
        this.setSize(500,500);
        // 设置位置
        this.setLocation(200,100);
        // 匿名内部类
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 启动线程,窗口的线程
        new PaintThread().start();
        // 启动键盘监听
        addKeyListener(new KeyMonitor());


        // 生成初始化炮弹50个
        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }

    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
//
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

}
