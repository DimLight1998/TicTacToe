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
    boolean player_1_ready = false;
    boolean player_2_ready = false;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }


    public void run() {
        while(true) {
            System.out.println("Waiting for client on port "+ serverSocket.getLocalPort());
            try {
                Socket server = serverSocket.accept();
                System.out.println("Client from "+server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                // client tries to connect
                if(in.readUTF().startsWith("0")) {
                    if(!player_1_ready) {
                        out.writeUTF("01");
                        player_1_ready = true;
                    } else {
                        out.writeUTF("02");
                        player_2_ready = true;
                    }
                }





                out.write(1);
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
