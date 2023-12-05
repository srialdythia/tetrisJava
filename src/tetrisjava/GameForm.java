
package tetrisjava;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GameForm extends javax.swing.JFrame {

    private GameArea ga; // extends JPanel it means GameArea is a JPanel
    private GameThread gt; // extends Thread it means GameThread is a Thread
    
    public GameForm() {
        initComponents();
        ga = new GameArea(placeholder, 10);
        // add custom JPanel to GameForm
        add(ga);
        initControls();
    }
    
    public void startGame(){
        // initValue
        scoreValue.setText(String.valueOf(0));
        levelValue.setText(String.valueOf(0));
        lineValue.setText(String.valueOf(0));

        
        // initBackground
        ga.initBackground();
        // add game thread
        gt = new GameThread(ga,this);
        gt.start();
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        placeholder = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        lineLabel = new javax.swing.JLabel();
        scoreValue = new javax.swing.JLabel();
        levelValue = new javax.swing.JLabel();
        lineValue = new javax.swing.JLabel();
        mainMenuBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        placeholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        placeholder.setPreferredSize(new java.awt.Dimension(200, 300));

        javax.swing.GroupLayout placeholderLayout = new javax.swing.GroupLayout(placeholder);
        placeholder.setLayout(placeholderLayout);
        placeholderLayout.setHorizontalGroup(
            placeholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        placeholderLayout.setVerticalGroup(
            placeholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        scoreLabel.setText("Score:");

        levelLabel.setText("Level:");

        lineLabel.setText("Line:");

        scoreValue.setText("0");

        levelValue.setText("0");

        lineValue.setText("0");

        mainMenuBtn.setText("MainMenu");
        mainMenuBtn.setFocusable(false);
        mainMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainMenuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(placeholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scoreValue, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(levelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lineValue, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scoreValue)
                            .addComponent(scoreLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(levelValue)
                            .addComponent(levelLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lineValue)
                            .addComponent(lineLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainMenuBtn)
                            .addComponent(placeholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mainMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuBtnActionPerformed
        gt.interrupt();
        setVisible(false);
        TetrisJava.showMainMenu();
    }//GEN-LAST:event_mainMenuBtnActionPerformed

    
   public void initControls(){
        // take keyStroke
        InputMap im = getRootPane().getInputMap();
        ActionMap am = getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        am.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.blockMoveRight();
            }
        });
        
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        am.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.blockMoveLeft();
            }
        });
        
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        am.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.blockMoveDown();
            }
        });        
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        am.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.blockRotate();
            }
        });
   }
   
   public void setScoreVal(int score){
       scoreValue.setText(String.valueOf(score));
   }
   public void setLevelVal(int level){
       levelValue.setText(String.valueOf(level));
   }
   public void setLineVal(int line){
       lineValue.setText(String.valueOf(line));
   }
   
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel levelValue;
    private javax.swing.JLabel lineLabel;
    private javax.swing.JLabel lineValue;
    private javax.swing.JButton mainMenuBtn;
    private javax.swing.JPanel placeholder;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel scoreValue;
    // End of variables declaration//GEN-END:variables
}
