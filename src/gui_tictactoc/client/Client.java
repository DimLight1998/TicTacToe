package gui_tictactoc.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

/**
 * Created on 2017/04/26.
 */
public class Client implements ActionListener {
    WindowLogin windowLogin;
    WindowGame windowGame;
    InetAddress address;
    int port;
    boolean readyToConnect = false;
    boolean connected = false;

    public Client() {
        windowLogin = new WindowLogin(this);
    }


    public void run() throws InterruptedException {
        windowLogin.display();
        while(!readyToConnect){
            Thread.sleep(100);
        }

        System.out.println("I am ready to connect !");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            try {
                address = InetAddress.getByName(windowLogin.getAddress());
                port = Integer.parseInt(windowLogin.getPort());
                windowLogin.dispose();
                readyToConnect = true;
            } catch(Exception e1) {
                JOptionPane.showMessageDialog(windowLogin, "Error info here");
            }
        }
    }
}
