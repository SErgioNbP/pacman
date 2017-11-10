package org.academiadecodigo.server;

public class GhostServer {


    public void move(){

        Server server = new Server();

        for (int i = 0; i < 5; i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String string = "Ghost " + 42 + " " + (7 - i);

            server.broadcast(string);
            System.out.println(string);
        }
    }
}
