package org.academiadecodigo.server;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.movables.Ghost;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 26/10/17.
 */
public class Server {

    public static void main(String[] args) {


        List<ServerGhost> serverGhosts = new LinkedList<>();

        for (int i = 0; i < 5; i++) {

            serverGhosts.add(new ServerGhost(new Position(42, 7)));
        }

        DatagramSocket socket = null;

        try {

            socket = new DatagramSocket(9090);

        } catch (Exception e) {
            System.out.println("111");

        }

        while (true) {

            System.out.println("moving");
            for (ServerGhost serverGhost : serverGhosts) {
                System.out.println("kkkkk");
                serverGhost.move();

                try {

                    int portNumber = 9090;

                    byte[] sendBuffer;

                    byte[] receiveBuffer = new byte[1024];


                    DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                    socket.receive(receivedPacket);

                    //String stringReceived = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

                    String stringToSend = "";

                    for (ServerGhost ghost : serverGhosts) {

                        stringToSend = stringToSend + ghostPositionCoding(ghost.getPosition());
                    }

                    sendBuffer = stringToSend.getBytes();
                    System.out.println(receivedPacket.getPort());

                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivedPacket.getAddress(), receivedPacket.getPort());

                    socket.send(sendPacket);


                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String ghostPositionCoding(Position position) {

        String string = "Ghost " + position.getCol() + " " + position.getRow() + "\n";

        return string;
    }
}
    
