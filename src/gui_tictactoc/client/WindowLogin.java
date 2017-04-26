package gui_tictactoc.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created on 2017/04/26.
 */
public class WindowLogin extends JPanel implements ActionListener {
    private JLabel label_address;
    private JLabel label_port;
    private JTextField text_address;
    private JTextField text_port;
    private JButton button_start;
    private JButton button_exit;


    public WindowLogin() {
        JFrame mainFrame = new JFrame("Login");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setContentPane(this);
        mainFrame.setResizable(false);
        mainFrame.setSize(new Dimension(300, 180));

        JPanel panel_address = new JPanel(new FlowLayout());
        JPanel panel_port = new JPanel(new FlowLayout());
        JPanel panel_buttons = new JPanel(new FlowLayout());

        label_address = new JLabel("IP Address:");
        label_port = new JLabel("Port Number:");
        text_address = new JTextField(10);
        text_port = new JTextField(10);
        button_start = new JButton("Start");
        button_exit = new JButton("Exit");

        button_start.addActionListener(this);
        button_exit.addActionListener(this);

        panel_address.add(label_address);
        panel_address.add(text_address);
        panel_port.add(label_port);
        panel_port.add(text_port);
        panel_buttons.add(button_start);
        panel_buttons.add(button_exit);

        mainFrame.getContentPane().add(panel_address);
        mainFrame.getContentPane().add(panel_port);
        mainFrame.getContentPane().add(panel_buttons);

        mainFrame.setLocationRelativeTo(null);

        mainFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            // TODO
            System.out.println("Hello");
        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }
}
