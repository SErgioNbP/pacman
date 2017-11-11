package org.academiadecodigo.server;

import org.academiadecodigo.pacman.Game;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.*;

/**
 * Created by codecadet on 26/10/17.
 */
public class Client implements Runnable {

    Game game;
    String stringToSend;

    public Client(Game game) {

        this.game = game;

        stringToSend = " ";

    }

    @Override
    public void run() {

        byte[] receiveBuffer = new byte[1024];

        //byte[] serverAddress = {127, 0, 0, 1};

        try {
            DatagramSocket socket = new DatagramSocket();
            System.out.println(socket.getLocalPort());

            while (true) {

                byte[] sendBuffer = stringToSend.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("localhost"), 9090);


                socket.send(sendPacket);

                System.out.println("hhhhhhhhhhhhhhh");

                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                System.out.println("waiting");

                socket.receive(receivedPacket);

                String receivedString = new String(receivedPacket.getData());

                game.updateGhostsPosition(receivedString.trim());

                System.out.println(receivedString.trim());

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
