package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {
    //蛇
    private Snake snake;
    //糖果
    private Candy candy;
    //游戏状态标志量
    private boolean gameOver;
    //游戏结束原因
    private String gameOverCondition;
    //游戏计算时间刻
    private int tickTime;
    //游戏时间
    private int hour,minute,second;
    //游戏时间字符串
    private String timeString;

    public GamePanel(){
        snake = new Snake(10);
        candy = new Candy();
        gameOver = false;
        candy.createCandy(snake.LookBody());
        tickTime = 200;
        hour = 0;
        minute = 0;
        second = 0;
        timeString = "00:00:00";
    }
    @Override
    public void paint(Graphics g) {
        long startTime = System.currentTimeMillis();
        super.paint(g);
        drawBackground(g);
        drawSnake(g);
        drawCandy(g);
        drawScore(g);
        long endTime = System.currentTimeMillis();
        System.out.println("paint time:" + (endTime - startTime));
    }
    //画蛇活动的背景
    public void drawBackground(Graphics g){
        //背景网格墙
        int c;
        for(int i = 0;i < 40;i++){
            c = i % 2;
            for(int j = 0;j < 30;j++){
                if(c == 0){
                    g.setColor(new Color(252, 178, 79));
                }else{
                    g.setColor(new Color(251, 193, 78));
                }
                c = (c + 1) % 2;
                g.fillRect(i * 20,j * 20,20,20);
            }
        }
    }
    //画蛇
    public void drawSnake(Graphics g){
        List<int[]> x = snake.LookBody();
        for(int i = x.size() - 1;i >= 0;i--){
            if(i == 0){
                //蛇头红色
                g.setColor(new Color(124, 252, 0));
            }else{
                //蛇身绿色
                g.setColor(new Color(50, 205, 50));
            }
            g.fillRect(x.get(i)[0] - 10,x.get(i)[1] - 10,20,20);
            g.setColor(Color.black);
            g.drawRect(x.get(i)[0] - 10,x.get(i)[1] - 10,20,20);
        }
    }
    //画糖果
    public void drawCandy(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(candy.getX() - 10, candy.getY() - 10, 20, 20);
        g.setColor(Color.black);
        g.drawOval(candy.getX() - 10, candy.getY() - 10, 20, 20);
    }

    public void drawScore(Graphics g){
        //说明信息的背景色
        g.setColor(new Color(240, 240, 240));
        g.fillRect(801,0,225,600);
        //渲染字体
        g.setColor(Color.black);
        g.setFont(new Font("楷体",Font.BOLD,35));
        g.drawString("贪吃蛇游戏",810,50);
        g.drawString(timeString,830,90);
        g.drawString("得分:",810,130);
        g.drawString((snake.LookBody().size() - snake.getOriginSize()) * 5 + "",915,130);
        if(gameOver){
            g.setColor(Color.red);
            g.setFont(new Font("楷体",Font.BOLD,30));
            g.drawString("游戏结束！",810,170);
            g.setFont(new Font("楷体",Font.BOLD,25));
            g.drawString("结束原因:",810,210);
            g.drawString(gameOverCondition,810,250);
        }
//        g.setColor(Color.black);
//        g.setFont(new Font("楷体",Font.BOLD,25));
//        g.drawString("说明:",810,280);
//        g.drawString("吃一颗糖得5分,",810,310);
//        g.drawString("吃满20颗则赢,",810,340);
//        g.drawString("撞墙或者撞上",810,370);
//        g.drawString("自己则失败。",810,400);
    }
    public void timeThread(){
        while(!gameOver){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(second == 59){
                if(minute == 59){
                    second = 0;
                    minute = 0;
                    hour ++;
                }else{
                    second = 0;
                    minute ++;
                }
            }else{
                second ++;
            }
            timeString = String.format("%02d:%02d:%02d",hour,minute,second);
        }
    }
    public void gameThread(){
        while(!gameOver){
            //闲逛
            snake.snakeIdle();
            //吃糖
            if(snake.getX() == candy.getX() && snake.getY() == candy.getY()) {
                //System.out.println("meet");
                snake.addTail();
                candy.eatCandy();
                candy.createCandy(snake.LookBody());
            }
            //结束条件1:蛇长达上限(默认铺满全屏算赢)
            if(snake.LookBody().size() == 2 + 40 * 30){
                gameOverCondition = "胜利！";
                //游戏结束
                gameOver = true;
                repaint();
            }
            //结束条件2:撞墙
            if(snake.getX() < 0 || snake.getX() > 800 || snake.getY() < 0 || snake.getY() > 600){
                gameOverCondition = "撞墙了！";
                //游戏结束
                gameOver = true;
                repaint();
            }
            //结束条件3:撞上自己了
            if(snake.headFrontIsBody()){
                gameOverCondition = "撞上自己了！";
                //游戏结束
                gameOver = true;
                repaint();
            }
            try{
                Thread.sleep(tickTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void refreshThread(){
        while (!gameOver){
            repaint();
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            snake.setDirection(0);
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.setDirection(1);
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.setDirection(2);
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.setDirection(3);
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            tickTime = 50;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            tickTime = 200;
        }
    }
}
