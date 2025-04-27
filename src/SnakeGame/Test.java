package SnakeGame;

import javax.swing.*;

public class Test extends JFrame{
    private GamePanel mp2 = null;
    public static void main(String[] args) {
        new Test();
        //BigInteger x = 123;
    }
    public Test(){
        mp2 = new GamePanel();
        //添加画板
        add(mp2);
        //标题
        setTitle("贪吃蛇");
        //添加键盘监听
        addKeyListener(mp2);
        //设置X的默认操作
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //设置窗口大小
        setSize(1025,635);
        //设置可变窗口大小
        setResizable(false);
        //游戏计算线程 包括蛇吃糖 蛇移动 蛇撞到了墙或者撞到了自己的身体
        new Thread(()->mp2.gameThread()).start();
        //刷新线程,按照一定时间间隔刷新游戏界面
        new Thread(()->mp2.refreshThread()).start();
        //设置可见性
        setVisible(true);
    }
}
