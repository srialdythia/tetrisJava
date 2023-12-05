package tetrisjava;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread{
    private GameArea ga;
    private GameForm gf;
    
    private int lineVal,scoreVal,levelVal;
    private int linePerLvl = 5;
    
    public GameThread(GameArea gameArea, GameForm gameForm){
        ga = gameArea;
        gf = gameForm;
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
            
            
            // clear line
            int line = ga.clearLine();
            lineVal += line;
            gf.setLineVal(lineVal);
            
            // score
            scoreVal += ga.countPoints(line, levelVal);
            gf.setScoreVal(scoreVal);

            // level
            int lvl = Math.floorDiv(lineVal, linePerLvl);
            System.out.println("lvl " + lvl);
            System.out.println("levelVal " + levelVal);
            if(lvl > levelVal){
                levelVal = lvl;
                gf.setLevelVal(levelVal);
            }
        }
        // gameOver
        if(ga.isGameOver()){
            System.out.println("GAME OVER");
//            Tetris.saveScore();
        }
    }
}
