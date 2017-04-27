package gui_tictactoc.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 2017/04/27.
 */
public class WindowWaiting extends JPanel {
    JFrame frame;
    WindowLogin windowLogin;

    WindowWaiting(WindowLogin windowLogin) {
        this.windowLogin = windowLogin;
    }

    public void run() {
        JLabel wait = new JLabel("Wait info here",SwingConstants.CENTER);
        this.setLayout(new GridLayout(1,1));
        this.add(wait);

        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(300,100);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setContentPane(this);

        frame.setVisible(true);
    }


    public void dispose() {
        frame.setVisible(false);
        frame.dispose();
    }
}
