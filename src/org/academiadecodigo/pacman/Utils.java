package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.fruit.powers.Apple;
import org.academiadecodigo.pacman.objects.movables.Ghost;
import org.academiadecodigo.pacman.objects.movables.Player;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static List<Position> walls;
    public static List<Position> path;
    public static List<Position> fruitsPos;
    public static List<Position> applesPos;
    public static List<Position> ghostsPos;
    public static List<Position> playersPos;


    private static String readFromFile() {

        String result = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("resources/map"));
            String line = reader.readLine();

            while (line != null) {
                result += line + "\n";
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {

                if (reader != null)
                    reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void generateLists() {

        walls = new LinkedList<>();
        path = new LinkedList<>();
        playersPos = new LinkedList<>();
        ghostsPos = new LinkedList<>();
        applesPos = new LinkedList<>();
        fruitsPos = new LinkedList<>();

        String[] mapRow = readFromFile().split("\\n");

        for (int i = 0; i < mapRow.length; i++) {

            char[] mapColumn = mapRow[i].toCharArray();

            for (int j = 0; j < mapColumn.length; j++) {

                char currentChar = mapColumn[j];
                Position position = new Position(j, i);

                if (currentChar == '0') {
                    walls.add(position);

                } else if (currentChar == '1') {
                    fruitsPos.add(position);

                } else if (currentChar == '2') {
                    applesPos.add(position);

                } else if (currentChar == '3') {
                    ghostsPos.add(position);

                } else if (currentChar == '6') {
                    playersPos.add(position);

                }

                if (!walls.contains(position)) {
                    path.add(position);
                }
            }
        }
    }

    public static void decode(String receivedString, Ghost ghost) {

        String[] strings = receivedString.split(" ");

        if (strings[0].equals("Ghost")) {

            ghost.setPositionColRow(Integer.parseInt(strings[1]), Integer.parseInt(strings[3]));
        }
    }

    public static List<Ghost> createGhosts() {

        List<Ghost> ghosts = new LinkedList<>();

        for (Position position : ghostsPos) {
            Ghost ghost = new Ghost(position);
            ghosts.add(ghost);
        }

        return ghosts;
    }

    public static List<Apple> createApples() {

        List<Apple> apples = new LinkedList<>();

        for (Position position : applesPos) {
            Apple apple = new Apple(position);
            apples.add(apple);
        }

        return apples;
    }

    public static List<Fruit> createFruits() {

        List<Fruit> fruits = new LinkedList<>();

        for (Position position : fruitsPos) {
            Fruit fruit = new Fruit(position);
            fruits.add(fruit);
        }

        return fruits;
    }

    public static List<Player> createPlayers() {

        List<Player> players = new LinkedList<>();

        for (Position position : playersPos) {
            Player player = new Player(position);
            players.add(player);
        }

        return players;
    }
}
