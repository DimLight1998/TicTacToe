package gui_tictactoc.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 2017/04/26.
 */
public class WindowGame extends JPanel {
    private JLabel [] [] labels_chessboard;
    private JFrame mainFrame;


    public WindowGame(Client client) {
        labels_chessboard = new JLabel[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                labels_chessboard[i][j] = new JLabel(new ImageIcon("src/res/cross.png"));
                labels_chessboard[i][j].setSize(120,120);
                labels_chessboard[i][j].setName(i + "" + j);
                labels_chessboard[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                labels_chessboard[i][j].addMouseListener(client);
            }
        }

        this.setLayout(new GridLayout(3,3));

        for(int i = 0;i<3;i++){
            for (int j = 0; j < 3; j++) {
                this.add(labels_chessboard[i][j]);
            }
        }


        mainFrame = new JFrame("Tic-Tac-Toc");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setSize(400,400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(this);
    }


    public void display() {
        mainFrame.setVisible(true);
    }


}
