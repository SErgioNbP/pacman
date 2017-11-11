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
    ExecutorService executorService;
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

            while (true) {

                byte[] receiveBuffer = new byte[1024];

                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivedPacket);

                if (new String(receivedPacket.getData()).trim().equals("START")) {

                    GhostHandler ghostHandler = new GhostHandler(receivedPacket.getAddress(), receivedPacket.getPort());
                    timer.scheduleAtFixedRate(ghostHandler, 5000, 500);

                    if (!addressExists(receivedPacket)) {
                        addresses.add(receivedPacket);
                    }
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean addressExists(DatagramPacket packet) {

        return addresses.contains(packet);
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

                    int portNumber = 9090;

                    byte[] receiveBuffer = new byte[1024];

                    DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                    socket.receive(receivedPacket);

                    String receivedString = new String(receivedPacket.getData()).trim();

                    System.out.println(receivedString);

                    String[] strings = receivedString.split(" ");

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    class GhostHandler extends TimerTask {

        private InetAddress ip;
        private int portNumber;

        public GhostHandler(InetAddress ip, int portNumber) {
            this.ip = ip;
            this.portNumber = portNumber;
        }

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

