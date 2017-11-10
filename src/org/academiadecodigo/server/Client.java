package org.academiadecodigo.server;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created by codecadet on 26/10/17.
 */
public class Client {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String stringToSend = scanner.next();

        byte[] sendBuffer = stringToSend.getBytes();

        byte[] receiveBuffer = new byte [1024];

        //byte[] serverAddress = {127, 0, 0, 1};

        try {
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("192.168.0.22"), 12345);

            DatagramSocket socket = new DatagramSocket();

            socket.send(sendPacket);

            DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            socket.receive(receivedPacket);

            String receivedString = new String(receivedPacket.getData());

            System.out.println(receivedString.trim());

            socket.close();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
