package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.objects.fruit.Fruit;
import org.academiadecodigo.pacman.objects.fruit.powers.Apple;
import org.academiadecodigo.pacman.objects.movables.Ghost;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileHelper {

    public static List<Position> allPositions;

    public static List<Position> walls;
    public static List<Position> path;
    public static List<Position> players;
    public static List<Position> ghostsPos;
    public static List<Position> applesPos;
    public static List<Position> fruitsPos;

    public static List<Position> edibles;
    public static List<Position> movables;

    private static String[] mapRow;
    private static char[] mapColumn;

    private static String readFromFile(String path) {

        String result = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(path));
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

        allPositions = new LinkedList<>();

        walls = new LinkedList<>();
        path = new LinkedList<>();
        players = new LinkedList<>();
        ghostsPos = new LinkedList<>();
        applesPos = new LinkedList<>();
        fruitsPos = new LinkedList<>();

        mapRow = readFromFile("resources/map").split("\\n");

        for (int i = 0; i < mapRow.length; i++) {

            mapColumn = mapRow[i].toCharArray();

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
                    players.add(position);

                } else if (!walls.contains(position)) {
                    path.add(position);
                }
            }
        }

        allPositions();
    }

    private static void allPositions() {

        allPositions.addAll(walls);
        allPositions.addAll(applesPos);
        allPositions.addAll(fruitsPos);
        allPositions.addAll(ghostsPos);
        allPositions.addAll(players);
    }


    public static char getCurrentChar(int col, int row) {

        mapColumn = mapRow[row].toCharArray();

        return mapColumn[col];
    }

    public static void decode(String receivedString, Ghost ghost) {

        String[] strings = receivedString.split(" ");

        if (strings[0].equals("Ghost")) {

            ghost.setPositionColRow(Integer.parseInt(strings[1]), Integer.parseInt(strings[3]));
        }
    }

    public static List<Ghost> createGhosts() {

        List<Ghost> ghosts = new LinkedList<>();

        for (int i = 0; i < ghostsPos.size(); i++) {
            Ghost ghost = new Ghost(ghostsPos.get(i));
            ghosts.add(ghost);
        }

        return ghosts;
    }

    public static List<Apple> createApples() {

        List<Apple> apples = new LinkedList<>();

        for (int i = 0; i < applesPos.size(); i++) {
            Apple apple = new Apple(applesPos.get(i));
            apples.add(apple);
        }

        return apples;
    }

    public static List<Fruit> createFruits() {

        List<Fruit> fruits = new LinkedList<>();

        for (int i = 0; i < fruitsPos.size(); i++) {
            Fruit fruit = new Fruit(fruitsPos.get(i));
            fruits.add(fruit);
        }

        return fruits;
    }

    public static List<Position> getAllPositions() {

        generateLists();

        return allPositions;
    }
}
