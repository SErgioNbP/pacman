package org.academiadecodigo.server;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created by codecadet on 26/10/17.
 */
public class Client {

    public static void main(String[] args) {


        String stringToSend = " ";


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

                System.out.println(receivedString.trim());

            }

            //socket.close();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
