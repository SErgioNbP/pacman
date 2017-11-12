package org.academiadecodigo.server;

import org.academiadecodigo.pacman.Utils;
import org.academiadecodigo.pacman.grid.Position;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by codecadet on 26/10/17.
 */
public class Server {

    private List<DatagramPacket> addresses;
    private int portNumber = 9090;
    private volatile List<ServerGhost> serverGhosts;
    private DatagramSocket socket;
    private ExecutorService executorService;
    private Timer timer;


    public static void main(String[] args) {

        Server server = new Server();
        server.init();
        server.start();
    }

    private void init() {

        Utils.generateLists();

        timer = new Timer();

        serverGhosts = new LinkedList<>();

        for (int i = 0; i < Utils.ghostsPos.size(); i++) {

            serverGhosts.add(new ServerGhost(Utils.ghostsPos.get(i)));
        }

        executorService = Executors.newFixedThreadPool(10);

        addresses = new LinkedList<>();

    }


    private void start() {

        try {

            socket = new DatagramSocket(portNumber);

            while (addresses.size() < 2) {

                byte[] receiveBuffer = new byte[1024];

                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivedPacket);
                String string = new String(receivedPacket.getData()).trim();

                if (string.equals("START")) {
                    System.out.println("GGGGGGG");

                    if (!addressExists(receivedPacket) || addresses.size() == 0) {
                        addresses.add(receivedPacket);
                    }
                }
            }

            String player1 = "player 42 7\nenemy 42 5";
            String player2 = "player 42 5\nenemy 42 7";

            sendDirectMessage(addresses.get(0), player2);
            sendDirectMessage(addresses.get(1), player1);

            GhostHandler ghostHandler = new GhostHandler();
            timer.scheduleAtFixedRate(ghostHandler, 1000, 300);

            ListenHandler listenHandler = new ListenHandler();
            executorService.submit(listenHandler);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean addressExists(DatagramPacket packet) {

        for (DatagramPacket datagramPacket : addresses) {

            if (datagramPacket.getAddress().equals(packet.getAddress())) {

                return true;
            }
        }

        return false;
    }

    private void sendDirectMessage(DatagramPacket datagramPacket, String string) {

        byte[] sendBuffer = string.getBytes();

        for (DatagramPacket packet : addresses) {

            if (!datagramPacket.getAddress().toString().equals(packet.getAddress().toString())) {

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, packet.getAddress(), packet.getPort());

                try {
                    socket.send(sendPacket);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void broadcast(String string) {

        byte[] sendBuffer = string.getBytes();
        for (DatagramPacket packet : addresses) {
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, packet.getAddress(), packet.getPort());
            try {
                socket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String ghostPositionCoding(Position position) {

        String string = "Ghost " + position.getCol() + " " + position.getRow() + "\n";

        return string;
    }

    class ListenHandler implements Runnable {


        @Override
        public void run() {

            while (true) {

                try {


                    byte[] receiveBuffer = new byte[1024];

                    DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                    socket.receive(receivedPacket);

                    if (!addressExists(receivedPacket)) {
                        addresses.add(receivedPacket);
                        System.out.println("added second player");
                    }

                    String receivedString = new String(receivedPacket.getData()).trim();

                    if (receivedString.equals("START")) {

                        System.out.println(receivedPacket.getAddress());
                        continue;
                    }

                    sendDirectMessage(receivedPacket, receivedString);

                    //broadcast(receivedString);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    class GhostHandler extends TimerTask {


        @Override
        public void run() {


            for (ServerGhost serverGhost : serverGhosts) {
                serverGhost.move();
            }

            String stringToSend = "";

            for (ServerGhost ghost : serverGhosts) {

                stringToSend = stringToSend + ghostPositionCoding(ghost.getPosition());
            }

            broadcast(stringToSend);
        }
    }
}

