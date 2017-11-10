package org.academiadecodigo.server;

import org.academiadecodigo.pacman.FileHelper;
import org.academiadecodigo.pacman.Game;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created by codecadet on 26/10/17.
 */
public class Client {

    public static void main(String[] args) {

        String stringToSend = "start";

        Game game = new Game();

        byte[] sendBuffer = stringToSend.getBytes();

        byte[] receiveBuffer = new byte[1024];

        //byte[] serverAddress = {127, 0, 0, 1};

        try {

            game.init();
            while (true) {

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("localhost"), 9090);

                DatagramSocket socket = new DatagramSocket();

                socket.send(sendPacket);

                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivedPacket);

                String receivedString = new String(receivedPacket.getData());

                game.updatePositions(receivedString.trim());

                System.out.println(receivedString.trim());

                socket.close();
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
