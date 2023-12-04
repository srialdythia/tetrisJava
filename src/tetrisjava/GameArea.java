package tetrisjava;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel{
    
    private int gridColumns,gridRows,gridSize;
    private TetrisBlock block;
    
    public GameArea(JPanel placeholder, int columns){
        placeholder.setVisible(false);
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        setBounds(placeholder.getBounds());
        
        gridColumns = columns;
        gridSize = getBounds().width/gridColumns;
        gridRows = getBounds().height/gridSize;
        
        int[][] shape ={{1,0},{1,0},{1,1}};
        block = new TetrisBlock(shape);
    }
    
    public void blockMoveRight(){
        block.moveRight();
        repaint();
    }
    public void blockMoveLeft(){
        block.moveLeft();
        repaint();
    }
    public void blockMoveDown(){
        block.moveDown();
        repaint();
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
