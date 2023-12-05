package tetrisjava;

import javax.swing.JOptionPane;

public class TetrisJava {
    private static GameForm gf;
    private static MainForm mf;
    private static Leaderboard lb;
    
    public static void startGame(){
        gf.startGame();
    }
    public static void showLeaderboard(){
        lb.initTable();
        lb.setVisible(true);
    }
    public static void showMainMenu(){
        mf.setVisible(true);
    }
    public static void gameOver(int score){
        System.out.println("GAME OVER");
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
        
        
        mf.setVisible(true);    
    }
    
}
