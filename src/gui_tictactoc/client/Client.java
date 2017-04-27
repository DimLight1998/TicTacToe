package gui_tictactoc.client;

import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created on 2017/04/26.
 */
public class Client implements ActionListener,MouseListener {
    WindowLogin windowLogin;
    WindowGame windowGame;
    InetAddress address;
    int port;
    boolean readyToConnect = false;
    boolean connected = false;
    int playerID;

    public Client() {
        windowLogin = new WindowLogin(this);
        windowGame = new WindowGame(this);
    }


    public void run() throws InterruptedException, IOException {
        windowLogin.display();

        while(!readyToConnect){
            Thread.sleep(100);
        }

        windowLogin.dispose();

        WindowWaiting windowWaiting = new WindowWaiting();
        windowWaiting.run();

        Socket clientSocket = new Socket(address,port);
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        out.writeUTF("0");
        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        System.out.println("you are player "+in.readUTF());

        windowWaiting.dispose();

        System.out.println("I am connnected !");

        windowGame.display();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            try {
                address = InetAddress.getByName(windowLogin.getAddress());
                port = Integer.parseInt(windowLogin.getPort());
                readyToConnect = true;
            } catch(Exception e1) {
                JOptionPane.showMessageDialog(windowLogin, "Error info here");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(1);
        System.out.println(e.getComponent().getName());
        System.out.println(1);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
