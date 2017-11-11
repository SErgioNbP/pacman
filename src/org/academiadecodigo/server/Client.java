package org.academiadecodigo.server;

import org.academiadecodigo.pacman.Game;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.*;

public class Client implements Runnable {

    private Game game;
    private volatile String stringToSend;

    public Client(Game game) {

        this.game = game;
        stringToSend = "";
    }

    @Override
    public void run() {

        byte[] receiveBuffer = new byte[1024];

        try {

            DatagramSocket socket = new DatagramSocket();

            while (true) {

                byte[] sendBuffer = stringToSend.getBytes();
                if (!stringToSend.equals("")) {

                    System.out.println("lih");
                }


                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("localhost"), 9090);

                socket.send(sendPacket);
                System.out.println(new String(sendPacket.getData()));

                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivedPacket);

                String receivedString = new String(receivedPacket.getData());

                game.updateGhostsPosition(receivedString.trim());

                stringToSend = "";
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (SocketException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStringToSend(String stringToSend) {
        this.stringToSend = stringToSend;
    }
}
