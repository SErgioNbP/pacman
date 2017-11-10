package org.academiadecodigo.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by codecadet on 26/10/17.
 */
public class Server {

    public static void main(String[] args) {

        while (true) {

            try {

                int portNumber = 9090;

                byte[] sendBuffer = new byte[1024];

                byte[] receiveBuffer = new byte[1024];

                DatagramSocket socket = new DatagramSocket(portNumber);

                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivedPacket);

                String stringReceived = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

                System.out.println(stringReceived);

                System.out.println(receivedPacket.getPort());

                String stringToSend = stringReceived.toUpperCase();

                sendBuffer = stringToSend.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivedPacket.getAddress(), receivedPacket.getPort());

                socket.send(sendPacket);

                socket.close();


            } catch (SocketException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }

        }

    }
}
