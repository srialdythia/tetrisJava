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
    
    public GameArea(JPanel placeholder, int columns){
        placeholder.setVisible(false);
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        setBounds(placeholder.getBounds());
        
        gridColumns = columns;
        gridSize = getBounds().width/gridColumns;
        gridRows = getBounds().height/gridSize;
        

    }
    
    public void spawnBlock(){
        Random r = new Random();
        int index = r.nextInt(blocks.length);
        block = new TetrisBlock(blocks[index].getShape());
        block.spawn(gridColumns);
    }
    
    public boolean checkRight(){
        if (block.getRightEdge() == gridColumns) return false;
        return true;
    }
    
    public boolean checkLeft(){
        if (block.getLeftEdge() == 0) return false;
        return true;
    }
    public boolean checkBottom(){
        if (block.getBottomEdge() == gridRows) return false;
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
    
    public void drawBackground(Graphics g){
        for(int r=0; r < gridRows; r++){
            for(int c=0; c < gridColumns; c++){
                int x = c*gridSize;
                int y = r*gridSize;
                g.setColor(Color.black);
                g.drawRect(x,y, gridSize, gridSize);
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
                    g.setColor(Color.blue);
                    g.fillRect(x, y, gridSize, gridSize);
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBlock(g);
        drawBackground(g);
    }
    
}
