package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {
    //蛇
    private Snake snake = null;
    //糖果
    private Candy candy = null;
    //游戏状态标志量
    private boolean gameOver;
    //游戏结束原因
    private String gameOverCondition;
    //游戏计算时间间隔
    private int calculateTime;

    public GamePanel(){
        snake = new Snake();
        candy = new Candy();
        gameOver = false;
        candy.createCandy(snake.LookBody());
        calculateTime = 250;
    }
    //初始化蛇长snakeSize
    public GamePanel(int snakeSize){
        snake = new Snake(snakeSize);
        candy = new Candy();
        gameOver = false;
        candy.createCandy(snake.LookBody());
        calculateTime = 250;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBackground(g);
        drawSnake(g);
        drawCandy(g);
        drawScore(g);
    }
    //画蛇活动的背景
    public void drawBackground(Graphics g){
        //背景墙颜色
        g.setColor(new Color(24, 62, 216, 121));
        //背景墙绘制
        g.fillRect(0,0,800,600);
        //网格线颜色
        g.setColor(Color.white);
        for(int i = 0;i < 40;i++){
            for(int j = 0;j < 30;j++){
                g.drawRect(i * 20,j * 20,20,20);
            }
        }
    }
    //画蛇
    public void drawSnake(Graphics g){
        List<int[]> x = snake.LookBody();
        for(int i = 0;i < x.size();i++){
            if(i == 0){
                //蛇头红色
                g.setColor(Color.red);
            }else{
                //蛇身绿色
                g.setColor(Color.green);
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
        g.setColor(Color.cyan);
        g.fillRect(801,0,225,600);
        //字
        g.setColor(Color.black);
        g.setFont(new Font("楷体",Font.BOLD,35));
        g.drawString("贪吃蛇游戏",810,50);
        g.drawString("得分:",810,100);
        g.drawString((snake.LookBody().size() - 2) * 5 + "",900,100);
        if(gameOver){
            g.setColor(Color.red);
            g.setFont(new Font("楷体",Font.BOLD,30));
            g.drawString("游戏结束！",810,150);
            g.setFont(new Font("楷体",Font.BOLD,25));
            g.drawString("结束原因:",810,190);
            g.drawString(gameOverCondition,810,230);
        }
//        g.setColor(Color.black);
//        g.setFont(new Font("楷体",Font.BOLD,25));
//        g.drawString("说明:",810,280);
//        g.drawString("吃一颗糖得5分,",810,310);
//        g.drawString("吃满20颗则赢,",810,340);
//        g.drawString("撞墙或者撞上",810,370);
//        g.drawString("自己则失败。",810,400);
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
                Thread.sleep(calculateTime);
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
            calculateTime = 50;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            calculateTime = 250;
        }
    }
}
