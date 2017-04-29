package gui_tictactoe.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 2017/04/27.
 */
class WindowWaiting extends JPanel {
    private JFrame frame;


    void run() {
        JLabel wait =
            new JLabel("Please wait for another player to join in.", SwingConstants.CENTER);
        this.setLayout(new GridLayout(1, 1));
        this.add(wait);

        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setContentPane(this);

        frame.setVisible(true);
    }


    void dispose() {
        frame.setVisible(false);
        frame.dispose();
    }
}
