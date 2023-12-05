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
    
    public GameArea(JPanel placeholder, int columns){
        placeholder.setVisible(false);
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        setBounds(placeholder.getBounds());
        
        gridColumns = columns;
        gridSize = getBounds().width/gridColumns;
        gridRows = getBounds().height/gridSize;
        
        background = new Color[gridRows][gridColumns];
    }
    
    public void spawnBlock(){
        Random r = new Random();
        int index = r.nextInt(blocks.length);
        block = new TetrisBlock(blocks[index].getShape());
        block.spawn(gridColumns);
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
                    if(y<0) return true;
                    if(background[y+1][x] != null) return false;
                }
            }
        }
        return true;
    }
    public void blockRotate(){
        block.rotate();
        repaint();
    }
    public void blockMoveRight(){
        if(!checkRight()) return;
        block.moveRight();
        repaint();
    }
    public void blockMoveLeft(){
        if(!checkLeft()) return;
        block.moveLeft();
        repaint();
    }
    public boolean blockMoveDown(){
        if(!checkBottom()) return false;
        block.moveDown();
        repaint();
        return true;
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
