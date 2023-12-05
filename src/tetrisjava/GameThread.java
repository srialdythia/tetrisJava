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
            ga.spawnBlock();
            while(ga.blockMoveDown()){
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ga.block2Background();
            if(ga.isGameOver()){break;}
            ga.clearLine();
        }
        // gameOver
        if(ga.isGameOver()){
            System.out.println("GAME OVER");
//            Tetris.saveScore();
        }
    }
}
