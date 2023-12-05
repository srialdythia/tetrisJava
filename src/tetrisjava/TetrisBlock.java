package tetrisjava;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
    
    private int [][][] shapes;
    private int[][] shape;
    private int height, width,x,y;
    private int currentRotation;
    private Color color;
    private Color [] colors = {Color.red, Color.green, Color.blue, Color.yellow};
    
    public TetrisBlock(int[][]s){
        shape = s;
        initShapes();
    }
    
    public void spawn(int columns){
        Random r = new Random();
        currentRotation = r.nextInt(4);
        shape = shapes[currentRotation];
        x = r.nextInt(columns - getWidth());
        y -= getHeight();
        color = colors[r.nextInt(colors.length)];
    }
    public void initShapes(){
        shapes = new int[4][][];
        shapes[0] = shape;
        for(int i = 1; i < 4; i++){ //three times rotation
            int r = shape[0].length;
            int c = shape.length;
            shapes[i] = new int [r][c];
            
            for(int y = 0; y < r; y++){
                for(int x = 0; x < c; x++){
                    shapes[i][y][x] = shape[c-x-1][y];
                }   
            }
            shape = shapes[i];
        }
    }
    public Color getColor(){return color;}
    public int getX(){return x;}
    public int getY(){return y;}
    public void moveDown(){y++;}
    public void moveLeft(){x--;}
    public void moveRight(){x++;}
    public void rotate(){
        currentRotation++;
        if(currentRotation > 3){
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }
    public int getHeight(){return shape.length;}
    public int getWidth(){return shape[0].length;}
    public int[][] getShape(){return shape;}
    public int getBottomEdge(){return y + getHeight();}
    public int getRightEdge(){return x + getWidth();}
    public int getLeftEdge(){return x;}
}
