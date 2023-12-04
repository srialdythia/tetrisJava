package tetrisjava;

public class TetrisBlock {
    
    private int[][] shape;
    private int height, width;
    
    public TetrisBlock(int[][]s){
        shape = s;
    }
    public int getHeight(){
        return shape.length;
    }
    public int getWidth(){
        return shape[0].length;
    }
    
    
}
