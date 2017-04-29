package gui_tictactoc;

import gui_tictactoc.server.Server;

import java.io.IOException;

/**
 * Created on 2017/04/29.
 */
public class OpenServer {
    public static void main(String args[]) throws IOException {
        new Server().startServer();
    }
}
