package gui_tictactoc.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created on 2017/04/26.
 */
public class Server extends Thread {
    private ServerSocket serverSocket;
    boolean              player_1_ready = false;
    boolean              player_2_ready = false;
    GameControl          gameControl;
    int                  turn;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        gameControl  = new GameControl();
    }


    public void run() {
        while (true) {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort());
            try {
                turn          = 1;
                Socket server = serverSocket.accept();
                System.out.println("Client from " + server.getRemoteSocketAddress());
                DataInputStream  in  = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                String infoRead = in.readUTF();

                // client tries to connect
                if (infoRead.startsWith("0")) {    // request an ID (beginning with 0)
                    if (!player_1_ready) {
                        out.writeUTF("01");    // assign ID 1
                        player_1_ready = true;
                    } else if (!player_2_ready) {
                        out.writeUTF("02");    // assign ID 2
                        player_2_ready = true;
                    } else {
                        out.writeUTF("00");    // this should not happen
                    }
                }

                // client wants to start the game
                if (infoRead.equals("10")) {    // player 1 asks whether player 2 is ready
                    if (player_2_ready) {
                        out.writeUTF("yes");
                    } else {
                        out.writeUTF("no");
                    }
                }

                if (infoRead.equals("20")) {    // player 2 asks whether player 1 is ready
                    if (player_1_ready) {
                        out.writeUTF("yes");
                    } else {
                        out.writeUTF("no");
                    }
                }

                // client wants to get information
                if (infoRead.equals("g"))    // player wants to get information
                {
                    if (gameControl.getWinner() != 0) {
                        out.writeUTF("w" + gameControl.getWinner());    // game is over
                    }
                    out.writeUTF(gameControl.getInfo());    // return information
                }

                // client wants to set chessman
                if (infoRead.startsWith("1s")) {
                    if (turn == 2) {
                        out.writeUTF("i");    // illegal
                    } else {
                        out.writeUTF("s");    // successful
                        int row    = infoRead.charAt(2);
                        int column = infoRead.charAt(3);
                        gameControl.setBelong(row, column, 1);
                        turn = 2;
                    }
                }

                if (infoRead.startsWith("2s")) {    // same as above
                    if (turn == 1) {
                        out.writeUTF("i");
                    } else {
                        out.writeUTF("s");
                        int row    = infoRead.charAt(2);
                        int column = infoRead.charAt(3);
                        gameControl.setBelong(row, column, 2);
                        turn = 1;
                    }
                }

                out.writeUTF("!");    // illegal
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
