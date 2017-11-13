package org.academiadecodigo.gameplay;

import org.academiadecodigo.gameplay.grid.Position;
import org.academiadecodigo.gameplay.objects.fruit.Fruit;
import org.academiadecodigo.gameplay.objects.fruit.powers.Apple;
import org.academiadecodigo.gameplay.objects.movables.Ghost;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    static List<Position> walls;
    public static List<Position> path;
    private static List<Position> fruitsPos;
    private static List<Position> applesPos;
    public static List<Position> ghostsPos;

    private static String readFromFile() {

        String result = "";
        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader("resources/gameLevel"));
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
                }

                if (!walls.contains(position)) {
                    path.add(position);
                }
            }
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
}
