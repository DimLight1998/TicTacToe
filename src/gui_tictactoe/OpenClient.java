package gui_tictactoe;

import gui_tictactoe.client.Client;

import java.io.IOException;

/**
 * Created on 2017/04/29.
 */
public class OpenClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Client().run();
    }
}
