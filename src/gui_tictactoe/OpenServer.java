package gui_tictactoe;

import gui_tictactoe.server.Server;

import java.io.IOException;

/**
 * Created on 2017/04/29.
 */
public class OpenServer {
    public static void main(String args[]) throws IOException {
        new Server().startServer();
    }
}
