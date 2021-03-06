package gui_tictactoe.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created on 2017/04/26.
 */
public class Server extends Thread implements ActionListener {
    private ServerSocket serverSocket;
    private boolean      player_1_ready = false;
    private boolean      player_2_ready = false;
    private boolean      someone_finish = false;
    private GameControl  gameControl;
    private WindowServer windowServer;


    public Server() throws IOException {
        gameControl  = new GameControl();
        windowServer = new WindowServer(this);
    }


    public void run() {
        int turn = 1;
        while (true) {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort());
            try {
                Socket server = serverSocket.accept();
                DataInputStream  in  = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                String infoRead = in.readUTF();

                // client tries to connect
                if (infoRead.startsWith("0")) {    // request an ID (beginning with 0)
                    if (!player_1_ready) {
                        out.writeUTF("1");    // assign ID 1
                        player_1_ready = true;
                    } else if (!player_2_ready) {
                        out.writeUTF("2");    // assign ID 2
                        player_2_ready = true;
                    } else {
                        out.writeUTF("0");    // this should not happen
                    }
                }

                // client wants to start the game
                if (infoRead.equals("1a")) {    // player 1 asks whether player 2 is ready
                    if (player_2_ready) {
                        out.writeUTF("yes");
                    } else {
                        out.writeUTF("no");
                    }
                }

                if (infoRead.equals("2a")) {    // player 2 asks whether player 1 is ready
                    if (player_1_ready) {
                        out.writeUTF("yes");
                    } else {
                        out.writeUTF("no");
                    }
                }

                // client wants to get information
                if (infoRead.startsWith("g"))    // player wants to get information
                {
                    if (infoRead.endsWith("!")) {    // forced
                        out.writeUTF(gameControl.getInfo());
                    } else {
                        if (gameControl.getWinner() != 0) {
                            out.writeUTF("w" + gameControl.getWinner());    // game is over
                        }
                        out.writeUTF(gameControl.getInfo());    // return information
                    }
                }

                // client wants to set chessman
                if (infoRead.startsWith("1s")) {
                    if (turn == 2) {
                        out.writeUTF("i");    // illegal
                    } else {
                        int row    = Character.getNumericValue(infoRead.charAt(2));
                        int column = Character.getNumericValue(infoRead.charAt(3));

                        if (gameControl.getBelong(row, column) != 0) {
                            out.writeUTF("i");
                        } else {
                            gameControl.setBelong(row, column, 1);
                            turn = 2;
                            out.writeUTF("s");
                        }
                    }
                }

                if (infoRead.startsWith("2s")) {    // same as above
                    if (turn == 1) {
                        out.writeUTF("i");
                    } else {
                        int row    = Character.getNumericValue(infoRead.charAt(2));
                        int column = Character.getNumericValue(infoRead.charAt(3));

                        if (gameControl.getBelong(row, column) != 0) {
                            out.writeUTF("i");
                        } else {
                            gameControl.setBelong(row, column, 2);
                            turn = 1;
                            out.writeUTF("s");
                        }
                    }
                }

                if (infoRead.startsWith("f")) {
                    if (!someone_finish) {
                        someone_finish = true;
                    } else {
                        server.close();
                        break;
                    }
                }

                out.writeUTF("!");    // illegal
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        windowServer.showEnd();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        windowServer.dispose();
    }


    public void startServer() {
        windowServer.display();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            try {
                serverSocket = new ServerSocket(windowServer.getPort());
                windowServer.showRunning();
                start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
