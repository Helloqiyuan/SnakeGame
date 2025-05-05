package SnakeGame;

import javax.swing.*;

public class Test extends JFrame{
    private final GamePanel mp2;
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
        setSize(1025, 638);
        //设置可变窗口大小
        setResizable(false);
        //主线程:不操作时默认往一个方向移动
        new Thread(mp2::gameThread).start();
        //胜利线程:  判断是否胜利
        new Thread(mp2::victoryThread).start();
        //吃糖线程:  判断是否吃掉糖
        new Thread(mp2::eatThread).start();
        //撞墙线程: 判断是否撞墙
        new Thread(mp2::crashWallThread).start();
        //撞到自己的身体线程
        new Thread(mp2::crashSelfThread).start();
        //显示时间线程
        new Thread(mp2::timeThread).start();
        //刷新线程,按照一定时间间隔刷新游戏界面 (刷新率):100
        new Thread(mp2::refreshThread).start();
        //设置可见性
        setVisible(true);
    }
}
