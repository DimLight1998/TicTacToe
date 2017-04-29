package gui_tictactoc.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 2017/04/26.
 */
class WindowGame extends JPanel {
    private JLabel[][] labels_chessboard;
    private JFrame mainFrame;


    WindowGame(Client client) {
        labels_chessboard = new JLabel[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                labels_chessboard[i][j] = new JLabel();
                labels_chessboard[i][j].setSize(120, 120);
                labels_chessboard[i][j].setName(i + "" + j);
                labels_chessboard[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                labels_chessboard[i][j].addMouseListener(client);
            }
        }

        this.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.add(labels_chessboard[i][j]);
            }
        }

        mainFrame = new JFrame("Tic-Tac-Toc");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setSize(400, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(this);
    }


    void update(int row, int column, int belong) {
        if (belong == 1) {
            labels_chessboard[row][column].setIcon(new ImageIcon("res/cross.png"));
        } else if (belong == 2) {
            labels_chessboard[row][column].setIcon(new ImageIcon("res/circle.png"));
        } else if (belong == 0) {
            labels_chessboard[row][column].setIcon(null);
        }

        labels_chessboard[row][column].setHorizontalAlignment(SwingConstants.CENTER);
    }


    void update(String information) {
        update(0, 0, Character.getNumericValue(information.charAt(0)));
        update(0, 1, Character.getNumericValue(information.charAt(1)));
        update(0, 2, Character.getNumericValue(information.charAt(2)));
        update(1, 0, Character.getNumericValue(information.charAt(3)));
        update(1, 1, Character.getNumericValue(information.charAt(4)));
        update(1, 2, Character.getNumericValue(information.charAt(5)));
        update(2, 0, Character.getNumericValue(information.charAt(6)));
        update(2, 1, Character.getNumericValue(information.charAt(7)));
        update(2, 2, Character.getNumericValue(information.charAt(8)));
    }


    void display() {
        mainFrame.setVisible(true);
    }
}
