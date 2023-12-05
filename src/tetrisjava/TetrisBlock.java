package tetrisjava;

public class TetrisBlock {
    
    private int [][][] shapes;
    private int[][] shape;
    private int height, width,x,y;
    private int currentRotation;
    
    public TetrisBlock(int[][]s){
        shape = s;
        System.out.println(s != null);
        initShapes();
    }
    
    public void initShapes(){
        shapes = new int[4][][];
        for(int i = 0; i < 4; i++){ //three times rotation
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
