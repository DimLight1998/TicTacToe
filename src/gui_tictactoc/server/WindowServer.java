package gui_tictactoc.server;

import javax.swing.*;

/**
 * Created on 2017/04/29.
 */
class WindowServer extends JPanel{
    private JFrame mainFrame = new JFrame("server");
    private JTextField text_port =new JTextField(10);
    private JLabel lable_running = new JLabel("");


    WindowServer(Server server) {
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setContentPane(this);
        mainFrame.setResizable(false);
        mainFrame.setSize(300,180);

        lable_running.setSize(20,2);

        JButton button_start = new JButton("Start");

        JLabel label_port = new JLabel("Port");
        this.add(label_port);
        this.add(text_port);
        this.add(button_start);
        this.add(lable_running);

        button_start.addActionListener(server);
    }


    void display() {
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    int getPort() {
        return Integer.valueOf(text_port.getText());
    }


    void showRunning() {
        lable_running.setText("Server running...");
    }
}
