package tetrisjava;

public class TetrisBlock {
    
    private int[][] shape;
    private int height, width,x,y;
    
    public TetrisBlock(int[][]s){
        shape = s;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void moveDown(){
        y++;
    }
    public int getHeight(){
        return shape.length;
    }
    public int getWidth(){
        return shape[0].length;
    }
    public int[][] getShape(){
        return shape;
    }
    
    
}
