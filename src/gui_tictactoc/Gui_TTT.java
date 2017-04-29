package gui_tictactoc;

import gui_tictactoc.client.Client;
import gui_tictactoc.server.Server;

import java.io.IOException;

/**
 * Created on 2017/04/26.
 */
public class Gui_TTT {
    public static void main(String[] args) throws InterruptedException, IOException {
        if (args[0].equals("client")) {
            Client client = new Client();
            client.run();
        } else {
            Server server = new Server();
            server.startServer();
        }
    }
}
