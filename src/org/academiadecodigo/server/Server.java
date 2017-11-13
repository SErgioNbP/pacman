package org.academiadecodigo.server;

import org.academiadecodigo.pacman.Utils;
import org.academiadecodigo.pacman.grid.Position;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        executorService = Executors.newCachedThreadPool();

        addresses = new LinkedList<>();

    }


    private void start() {

        int startCount = 0;

        try {

            socket = new DatagramSocket(portNumber);

            String string = "";

            while (startCount < 2 || addresses.size() < 2) {

                byte[] receiveBuffer = new byte[1024];

                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivedPacket);

                string = new String(receivedPacket.getData()).trim();

                System.out.println("sending message");

                broadcast("blabla");

                if (string.equals("START")) {

                    startCount++;
                }
                System.out.println(startCount);

                if (!addressExists(receivedPacket) || addresses.size() == 0) {
                    System.out.println(addresses.size());
                    addresses.add(receivedPacket);
                }
            }

            GhostHandler ghostHandler = new GhostHandler();
            timer.scheduleAtFixedRate(ghostHandler, 1000, 300);

            ListenHandler listenHandler = new ListenHandler();
            executorService.submit(listenHandler);
            System.out.println("about to broadcast");


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

                    System.out.println("entered here");

                    broadcast("GAMESTART");

                    byte[] receiveBuffer = new byte[1024];

                    DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                    socket.receive(receivedPacket);

                    if (!addressExists(receivedPacket)) {
                        addresses.add(receivedPacket);
                        System.out.println("added second player");
                    }

                    String receivedString = new String(receivedPacket.getData()).trim();


                    System.out.println(addresses.size());

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
