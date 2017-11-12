package org.academiadecodigo.server;

import org.academiadecodigo.pacman.Game;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private Game game;
    private DatagramSocket socket;

    public Client(Game game) {

        this.game = game;
        socket = null;

    }

    public void startListening(){

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new ClientListen(socket));
    }

    public void sendServer(String string) {

        byte[] sendBuffer = string.getBytes();


        try {
            socket = new DatagramSocket();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("localhost"), 9090);

            socket.send(sendPacket);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class ClientListen implements Runnable {

        private DatagramSocket socket;

        public ClientListen(DatagramSocket socket) {

            this.socket = socket;
        }


        @Override
        public void run() {

            byte[] receiveBuffer = new byte[1024];

            try {


                while (true) {


                    DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                    socket.receive(receivedPacket);

                    String receivedString = new String(receivedPacket.getData());

                    game.updatePosition(receivedString.trim());
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();

            } catch (SocketException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

