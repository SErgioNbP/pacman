package org.academiadecodigo.server;

import javafx.stage.Screen;

public class test {

    public static void main(String[] args) {

        //7GameServer gameServer = new GameServer(9090);
        //gameServer.start();

        GhostServer ghostServer = new GhostServer();

        ghostServer.move();
    }
}
