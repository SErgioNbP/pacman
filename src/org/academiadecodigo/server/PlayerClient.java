package org.academiadecodigo.server;

import java.io.*;
import java.net.Socket;

public class PlayerClient {

    public static void main(String[] args) {

        String hostname = "localhost";
        int potNumber = 9090;

        try {

            File file = new File("Map");

            Socket clientSocket = new Socket(hostname, potNumber);

            FileInputStream fileToSend = new FileInputStream(file);

            byte[] buffer = new byte[(int) file.length()];

            System.out.println(file.length());

            fileToSend.read(buffer);

            PrintStream out = new PrintStream(clientSocket.getOutputStream(), true);

            out.write(buffer);

            out.flush();

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            in.read(buffer);

            FileOutputStream filetoReceive = new FileOutputStream("Map2");

            filetoReceive.write(buffer);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
