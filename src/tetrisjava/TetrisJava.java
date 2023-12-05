package tetrisjava;

public class TetrisJava {
    private static GameForm gf;
    private static MainForm mf;
    
    public static void startGame(){
        gf.startGame();
    }
    public static void showMainMenu(){
        mf.setVisible(true);
    }
    public static void gameOver(){
        System.out.println("GAME OVER");
    }
    
    public static void main(String[] args) {
        System.out.println("START GAME");
        mf = new MainForm();
        gf = new GameForm();
        
        mf.setVisible(true);    
    }
    
}
