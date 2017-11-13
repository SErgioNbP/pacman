package org.academiadecodigo.server;

import org.academiadecodigo.gameplay.Game;

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

    public void startListening() {

        //ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("start listening");
        Thread thread = new Thread(new ClientListen(socket));
        thread.start();
    }

    public void sendServer(String string) {

        byte[] sendBuffer = string.getBytes();

        try {
            socket = new DatagramSocket();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("192.168.0.26"), 9090);

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

            try {

                while (true) {

                    byte[] receiveBuffer = new byte[1024];

                    DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                    if (receivedPacket == null) {
                        return;
                    }
                    socket.receive(receivedPacket);

                    String receivedString = new String(receivedPacket.getData());

                    System.out.println(receivedString.trim());

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