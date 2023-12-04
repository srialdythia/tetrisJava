package tetrisjava;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread{
    private GameArea ga;
    
    public GameThread(GameArea gameArea){
        ga = gameArea;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            ga.blockMoveDown();
        }
    }
}
