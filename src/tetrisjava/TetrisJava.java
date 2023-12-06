package tetrisjava;

import javax.swing.JOptionPane;

public class TetrisJava {
    private static GameForm gf;
    private static MainForm mf;
    private static Leaderboard lb;
    private static AudioPlayer ap;

    
    public static void startGame(){
        startTetrisSound();
        gf.startGame();
    }
    public static void showLeaderboard(){
        lb.initTable();
        lb.setVisible(true);
    }
    public static void showMainMenu(){
        mf.setVisible(true);
    }
    public static void startTetrisSound(){
        ap.startTetrisSound();
    }
    public static void stopTetrisSound(){
        ap.stopTetrisSound();
    }
    public static void startClearLineSound(){
        ap.startClearLineSound();
    }
    public static void startLevelUpSound(){
        ap.startLevelUpSound();
    }
    public static void startGameOverSound(){
        ap.startGameOverSound();
    }    
    public static void gameOver(int score){
        stopTetrisSound();
        startGameOverSound();
        gf.setVisible(false);
        String playerName = JOptionPane.showInputDialog("input your name: ");
        
        if(playerName == null) {showMainMenu();}
        showLeaderboard();
        lb.addRowLeaderboard(playerName, score);
        
    }
    
    public static void main(String[] args) {
        System.out.println("START GAME");
        mf = new MainForm();
        gf = new GameForm();
        lb = new Leaderboard();
        ap = new AudioPlayer();
        
        mf.setVisible(true);    
    }
    
}
