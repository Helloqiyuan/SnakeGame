package SnakeGame;

import javax.swing.*;

public class Test extends JFrame{
    private GamePanel mp2;
    public static void main(String[] args) {
        new Test();
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
        setSize(1025,638);
        //设置可变窗口大小
        setResizable(false);
        //游戏计算线程 包括蛇吃糖 蛇移动 蛇撞到了墙或者撞到了自己的身体 (游戏刻)
        new Thread(()->mp2.gameThread()).start();
        //时间线程,按照一定时间间隔刷新游戏界面 (游戏进行的时间):1000
        new Thread(()->mp2.timeThread()).start();
        //刷新线程,按照一定时间间隔刷新游戏界面 (刷新率):100
        new Thread(()->mp2.refreshThread()).start();
        //设置可见性
        setVisible(true);
    }
}
