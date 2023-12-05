/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tetrisjava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Leaderboard extends javax.swing.JFrame {

    private DefaultTableModel model;
    private String fileName = "leaderboard.txt";
    
    public Leaderboard() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lbTable = new javax.swing.JTable();
        mainMenuBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(lbTable);

        mainMenuBtn.setText("MainMenu");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainMenuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addGap(237, 237, 237))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(mainMenuBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mainMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuBtnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        TetrisJava.showMainMenu();
    }//GEN-LAST:event_mainMenuBtnActionPerformed

    public void initTable(){
        model = (DefaultTableModel) lbTable.getModel();
        sortLeaderboard();
        readData2Txt();
    }
    
    private void sortLeaderboard(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(lbTable.getModel());
        lbTable.setRowSorter(sorter);

        List<RowSorter.SortKey>sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
    }
    
    private void readData2Txt(){
        model.setRowCount(0);
        FileReader file;
        try{
            file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            
            String line = "";
            while((line = reader.readLine()) != null){
                String name = line.split(",")[0];
                int score = Integer.valueOf(line.split(",")[1]);
                model.addRow(new Object [] {name,score});
            }
            reader.close();
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Leaderboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Leaderboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void writeData2Txt(){
        FileWriter file;
        try{
            file = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(file);
            
            for(int row = 0; row < lbTable.getRowCount(); row++){
                String s = "";
                for(int col = 0; col < lbTable.getColumnCount(); col++){
                    s += model.getValueAt(row, col);
                    if (col==0){s+=",";}
                    if (col==1){s+="\n";}
                }
                writer.write(s);
            }
            writer.close();
            file.close();
        } catch (IOException except){
            except.printStackTrace();
        }
    }
    public void addRowLeaderboard(String name, int score){
        model.addRow(new Object [] {name, score});
        writeData2Txt();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Leaderboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lbTable;
    private javax.swing.JButton mainMenuBtn;
    // End of variables declaration//GEN-END:variables
}
