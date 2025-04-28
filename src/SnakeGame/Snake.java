package SnakeGame;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    //蛇头在格子中心的x坐标
    private int x;
    //蛇头在格子中心的y坐标
    private int y;
    //蛇将要去的方向 0 1 2 3上右下左
    private int direction;
    //初始长度
    private int originSize;
    //一步走多少像素
    private int step = 20;
    //蛇身体的坐标
    private List<int[]> body;

    //默认蛇长为2
    public Snake() {
        this(2);
    }
    //自定义蛇长snakeSize
    public Snake(int snakeSize){
        this.x = 410;
        this.y = 310;
        this.originSize = snakeSize;
        this.direction = 1;
        body = new ArrayList<>();
        body.add(new int[]{x,y});
        for(int i = 0;i < snakeSize - 1;i++){
            addTail();
        }
    }
    public void snakeIdle(){
        switch (direction) {
            //上右下左
            case 0 -> y -= step;
            case 1 -> x += step;
            case 2 -> y += step;
            case 3 -> x -= step;
        }
        body.addFirst(new int[]{x,y});
        body.removeLast();
    }


    public void setDirection(int direction) {
        if(this.direction == 0 && direction == 2 ||
                this.direction == 1 && direction == 3 ||
                this.direction == 2 && direction == 0 ||
                this.direction == 3 && direction == 1){
            return ;
        }

        this.direction = direction;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public int getOriginSize() {
        return originSize;
    }

    public List<int[]> LookBody() {
        return new ArrayList<>(body);
    }
    public boolean headFrontIsBody(){
        for(int i = 1;i < body.size() - 1;i++){
            if(body.get(i)[0] == x && body.get(i)[1] == y){
                return true;
            }
        }
        return false;
    }
    public void addTail() {
        if(body.size() == 1){
            switch (direction){
                case 0 -> body.add(new int[]{x,y + 20});
                case 1 -> body.add(new int[]{x - 20,y});
                case 2 -> body.add(new int[]{x,y - 20});
                case 3 -> body.add(new int[]{x + 20,y});
            }
        }else {
            int[] last = body.getLast();
            if (body.get(body.size() - 2)[1] < last[1]) {
                body.add(new int[]{last[0], last[1] + 20});
                //System.out.println("0");
            } else if (body.get(body.size() - 2)[0] > last[0]) {
                body.add(new int[]{last[0] - 20, last[1]});
                //System.out.println("1");
            } else if (body.get(body.size() - 2)[1] > last[1]) {
                body.add(new int[]{last[0], last[1] - 20});
                //System.out.println("2");
            } else if (body.get(body.size() - 2)[0] < last[0]) {
                body.add(new int[]{last[0] + 20, last[1]});
                //System.out.println("3");
            }
        }
    }
}

