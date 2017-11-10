package org.academiadecodigo.server;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {


    private int portNumber;

    private ServerSocket serverSocket;

    private List<PlayerHandler> playerHandlerList;


    public GameServer(int portNumber) {

        this.portNumber = portNumber;
        playerHandlerList = new LinkedList<>();

    }

    public void start() {

        try {

            serverSocket = new ServerSocket(portNumber);

            ExecutorService executorService = Executors.newFixedThreadPool(4);

            while (true) {

                System.out.println("Waiting for Player");

                Socket playerSocket = serverSocket.accept();

                DataInputStream in = new DataInputStream(playerSocket.getInputStream());

                byte[] buffer = new byte[1679];

                in.read(buffer);

                PlayerHandler playerHandler = new PlayerHandler(playerSocket, buffer, this);

                playerHandlerList.add(playerHandler);

                executorService.submit(playerHandler);

                FileOutputStream writeFile = new FileOutputStream("Map2");

                writeFile.write(buffer);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(byte[] buffer) {

        for (PlayerHandler playerHandler : playerHandlerList){

            try {

                playerHandler.getOut().write(buffer);
                playerHandler.getOut().flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
