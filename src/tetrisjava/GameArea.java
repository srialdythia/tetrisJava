package tetrisjava;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import tetrisBlock.*;


public class GameArea extends JPanel{
    
    private int gridColumns,gridRows,gridSize;
    private TetrisBlock block;
    private TetrisBlock [] blocks = {new IShape(),new JShape(),new LShape(),new OShape(),new SShape(),new TShape(),new ZShape()};
    private Color [][] background;
    private boolean controls;
    
    public GameArea(JPanel placeholder, int columns){
        placeholder.setVisible(false);
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        setBounds(placeholder.getBounds());
        
        gridColumns = columns;
        gridSize = getBounds().width/gridColumns;
        gridRows = getBounds().height/gridSize;
    }
    
    public void initBackground(){
        background = new Color[gridRows][gridColumns];
    }
    
    public void spawnBlock(){
        controls = true;
        Random r = new Random();
        int index = r.nextInt(blocks.length);
        block = new TetrisBlock(blocks[index].getShape());
        block.spawn(gridColumns);
    }
    public void pullDownBackground(int currRow){
        for (int row = currRow; row > -1; row--){
            for(int col = 0; col < gridColumns; col++){
                if(row == 0) break;
                Color topCol = background[row-1][col];
                background[row][col] = topCol;
            }
        }
    }
    public int countPoints(int clearLine, int level){
        if (clearLine == 1) return 40*(level+1);
        if (clearLine == 2) return 100 * (level+1);
        if (clearLine == 3) return 300 * (level+1);
        if (clearLine >= 4) return 1200 * (level+1);
        return 0;
    }
    public int clearLine(){
        int clearLine = 0;
        // bottom to top, left to right;
        for (int row = gridRows-1; row > -1; row--){
            for(int col = 0; col < gridColumns; col++){
//                System.out.println(row + " " + col);
                if (background[row][col] == null) break;// if there is rect with no color in line, break
                if (col == gridColumns - 1){ // this line should be clear
                    // pulldown background
                    pullDownBackground(row);
                    // increase clearline
                    clearLine++;
                    // after pulldown, check for current row in the next iter 
                    row += 1;
                }
            }
        }
        repaint();
        return clearLine;
    }
    public boolean checkRight(){
        if (block.getRightEdge() == gridColumns) return false;
        
        int [][] shape = block.getShape();
        int height = block.getHeight();
        int width = block.getWidth();
        int posY = block.getY();
        int posX = block.getX();
        
        // loop from bottom to up, left to right
        for(int r = height-1; r > -1; r--){
            for (int c = width-1; c > -1; c--){
                if (shape[r][c] == 1){
                    int x = c + posX;
                    int y = r + posY;
                    if(y<0) return true;
                    if(background[y][x+1] != null) return false;
                }
            }
        }
        return true;
    }
    public boolean checkLeft(){
        if (block.getLeftEdge() == 0) return false;
        
        int [][] shape = block.getShape();
        int height = block.getHeight();
        int width = block.getWidth();
        int posY = block.getY();
        int posX = block.getX();
        
        // loop from bottom to up, right to left
        for(int r=height-1; r>-1; r--){
            for (int c = 0; c < width; c++){
                if (shape[r][c] == 1){
                    int x = c + posX;
                    int y = r + posY;
                    if(y<0) return true;
                    if(background[y][x-1] != null) return false;
                }
            }
        }
        return true;
    }
    public boolean checkBottom(){
        if (block.getBottomEdge() == gridRows) return false;

        int [][] shape = block.getShape();
        int height = block.getHeight();
        int width = block.getWidth();
        int posY = block.getY();
        int posX = block.getX();
        
        // loop from bottom to up, right to left
        for(int r=height-1; r>-1; r--){
            for (int c = 0; c < width; c++){
                if (shape[r][c] == 1){
                    int x = c + posX;
                    int y = r + posY;
                    if(y+1 < 0) return true;
                    if(background[y+1][x] != null) return false;
                }
            }
        }
        return true;
    }
    public boolean isBlockingBackground(){
        int [][] shape = block.getShape();
        int height = block.getHeight();
        int width = block.getWidth();
        int posY = block.getY();
        int posX = block.getX();
        
        // loop from bottom to up, right to left
        for(int r=height-1; r>-1; r--){
            for (int c = 0; c < width; c++){
                if (shape[r][c] == 1){
                    int x = c + posX;
                    int y = r + posY;
                    if(y<0) break;
                    if(background[y][x] != null) {
                        return true;
                    }
                }
            }
        }        
        return false;
    }
    public void blockRotate(){
        if(!controls) return;
        
        int currRotate = block.getRotation();
        
        block.rotate();
        if(block.getBottomEdge() > gridRows){
            block.setRotation(currRotate);
        }
        if (block.getRightEdge() > gridColumns){
            // moveLeft until block.getRightEdge - gridRows == 0
            while(block.getRightEdge() - gridColumns != 0){
                block.moveLeft();
            }
        }
        if (isBlockingBackground()){ // if block blocking background
            // rotate back
            block.setRotation(currRotate);
        }
       
        repaint();
    }
    public void blockMoveRight(){
        if(!controls) return;
        if(!checkRight()) return;
        block.moveRight();
        repaint();
    }
    public void blockMoveLeft(){
        if(!controls) return;
        if(!checkLeft()) return;
        block.moveLeft();
        repaint();
    }
    public boolean blockMoveDown(){
        if(!checkBottom()){ 
            controls = false;
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }
    public boolean isGameOver(){
        if(block.getY() < 0) return true;
        return false;
    }
    public void block2Background(){
        int [][] shape = block.getShape();
        int height= block.getHeight();
        int width = block.getWidth();
        int xPos = block.getX();
        int yPos = block.getY();
        Color color = block.getColor();
        
        for(int r=0; r<height; r++){
            for(int c=0; c<width; c++){
                int x = xPos + c;
                int y = yPos + r;
                if (y<0) break;
                if(shape[r][c] == 1){
                    background[y][x] = color;
                }
            }
        }
    }
    public void drawBackground(Graphics g){
        for(int r=0; r < gridRows; r++){
            for(int c=0; c < gridColumns; c++){
                Color color = background[r][c];
                if (color != null){
                    int x = c*gridSize;
                    int y = r*gridSize;
                    g.setColor(color);
                    g.fillRect(x,y, gridSize, gridSize);
                    g.setColor(Color.black);
                    g.drawRect(x, y, gridSize, gridSize);
                }
            }
        }
    }
    public void drawBlock(Graphics g){
        int [][] shape = block.getShape();
        int xPos = block.getX(); // grid point
        int yPos = block.getY(); // grid point
        
        for(int r=0; r < block.getHeight(); r++){
            for(int c=0; c < block.getWidth(); c++){
                if(shape[r][c] == 1){
                    int x = (c + xPos) *gridSize; // grid size
                    int y = (r + yPos) * gridSize; // grid size
                    g.setColor(block.getColor());
                    g.fillRect(x, y, gridSize, gridSize);
                    g.setColor(Color.black);
                    g.drawRect(x, y, gridSize, gridSize);
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        drawBlock(g);
    }
    
}
