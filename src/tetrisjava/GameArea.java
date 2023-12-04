package tetrisjava;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel{
    
    private int width,heigth,gridColumns,gridRows,gridSize;
    
    public GameArea(JPanel placeholder, int columns){
        placeholder.setVisible(false);
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        setBounds(placeholder.getBounds());
        
        gridColumns = columns;
        gridSize = getBounds().width/gridColumns;
        gridRows = getBounds().height/gridSize;
        System.out.println("grid size: " + gridSize);
        System.out.println("column: " + gridColumns);
        System.out.println("row: " + gridRows);

        
    }
    
    public void drawBackground(Graphics g){
        for(int r=0; r<gridRows; r++){
            for(int c=0; c<gridColumns; c++){
                int x = c*gridSize;
                int y = r*gridSize;
                g.setColor(Color.black);
                g.drawRect(c*gridSize,r*gridSize, gridSize, gridSize);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
    }
    
}
