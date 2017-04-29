package gui_tictactoc.client;


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
public class Client implements ActionListener, MouseListener {
    private WindowLogin windowLogin;
    private WindowGame  windowGame;
    private InetAddress address;
    private int         port;
    private boolean     readyToConnect = false;
    private int         playerID;


    public Client() {
        windowLogin = new WindowLogin(this);
        windowGame  = new WindowGame(this);
    }


    public void run() throws InterruptedException, IOException {
        windowLogin.display();

        // check to login
        while (!readyToConnect) {
            Thread.sleep(100);
        }

        windowLogin.dispose();

        // wait for starting
        WindowWaiting windowWaiting = new WindowWaiting();
        windowWaiting.run();

        Socket           clientSocket = new Socket(address, port);
        DataOutputStream out          = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream  in           = new DataInputStream(clientSocket.getInputStream());

        out.writeUTF("0");                           // request an ID
        playerID = Integer.valueOf(in.readUTF());    // ID get
        clientSocket.close();

        // wait for another player
        String resp;
        do {
            clientSocket = new Socket(address, port);
            out          = new DataOutputStream(clientSocket.getOutputStream());
            in           = new DataInputStream(clientSocket.getInputStream());
            Thread.sleep(100);
            out.writeUTF(playerID + "a");
            resp = in.readUTF();
            clientSocket.close();
        } while (resp.equals("no"));

        windowWaiting.dispose();    // game start

        windowGame.display();
        boolean gameOver = false;
        int     winner   = 0;

        while (!gameOver) {
            Thread.sleep(100);
            clientSocket = new Socket(address, port);
            out          = new DataOutputStream(clientSocket.getOutputStream());
            in           = new DataInputStream(clientSocket.getInputStream());
            out.writeUTF("g");
            resp = in.readUTF();

            if (resp.startsWith("w")) {
                winner   = Character.getNumericValue(resp.charAt(1));
                gameOver = true;
            } else {
                // simple information request
                windowGame.update(resp);
            }

            clientSocket.close();
        }

        clientSocket = new Socket(address, port);
        out          = new DataOutputStream(clientSocket.getOutputStream());
        in           = new DataInputStream(clientSocket.getInputStream());
        out.writeUTF("g!");
        resp = in.readUTF();
        windowGame.update(resp);
        clientSocket.close();


        if (winner == playerID) {
            JOptionPane.showMessageDialog(windowGame, "You win !");
        } else if (winner == 3) {
            JOptionPane.showMessageDialog(windowGame, "Draw !");
        } else {
            JOptionPane.showMessageDialog(windowGame, "You lose !");
        }

        clientSocket = new Socket(address, port);
        out          = new DataOutputStream(clientSocket.getOutputStream());
        out.writeUTF("f");
        clientSocket.close();

        System.exit(0);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            try {
                address        = InetAddress.getByName(windowLogin.getAddress());
                port           = Integer.parseInt(windowLogin.getPort());
                readyToConnect = true;
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(windowLogin, "Invalid information.");
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        String send = playerID + "s" + e.getComponent().getName();
        try {
            Socket           clientSocket = new Socket(address, port);
            DataOutputStream out          = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream  in           = new DataInputStream(clientSocket.getInputStream());
            out.writeUTF(send);

            String resp = in.readUTF();
            if (resp.equals("s")) {
                int row    = Character.getNumericValue(send.charAt(2));
                int column = Character.getNumericValue(send.charAt(3));
                windowGame.update(row, column, playerID);
            }

            clientSocket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {}


    @Override
    public void mouseReleased(MouseEvent e) {}


    @Override
    public void mouseEntered(MouseEvent e) {}


    @Override
    public void mouseExited(MouseEvent e) {}
}
