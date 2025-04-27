package SnakeGame;

import java.util.List;
import java.util.Random;

public class Candy {
    //糖果在单个格子中心的x坐标,糖果不存在则为 -1
    private int x;
    //糖果在单个格子中心的y坐标,糖果不存在则为 -1
    private int y;
    //是否存在糖果
    private boolean existCandy;

    public Candy(){
        x = -1;
        y = -1;
        existCandy = false;
    }

    public boolean hasCandy() {
        return existCandy;
    }

    public void createCandy(List<int[]> snake){
        //存在糖果不需要生成
        if(existCandy){
            return ;
        }
        do {
            Random random = new Random();
            int tpx = random.nextInt(40) * 20 + 10;
            int tpy = random.nextInt(30) * 20 + 10;
            int i;
            if(snake.size() == 1200){
                break;
            }
            for(i = 0;i < snake.size();i++){
                if(tpx == snake.get(i)[0] && tpy == snake.get(i)[1]){
                    break;
                }
            }
            if(i == snake.size()){
                x = tpx;
                y = tpy;
                existCandy = true;
            }
        }while (!existCandy);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void eatCandy(){
        x = -1;
        y = -1;
        existCandy = false;
    }
}
