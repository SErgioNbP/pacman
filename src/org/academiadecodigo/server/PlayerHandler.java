package org.academiadecodigo.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class PlayerHandler implements Runnable {

    private GameServer gameServer;
    private Socket clientSocket;
    private DataOutputStream out;
    private byte[] buffer;

    public PlayerHandler(Socket clientSocket, byte[] buffer, GameServer gameServer) {

        this.clientSocket = clientSocket;
        this.buffer = buffer;
        this.gameServer = gameServer;
        try {

            out = new DataOutputStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        gameServer.broadcast(buffer);
    }

    public DataOutputStream getOut() {
        return out;
    }
}
