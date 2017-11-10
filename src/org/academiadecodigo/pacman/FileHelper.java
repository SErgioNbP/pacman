package org.academiadecodigo.pacman;

import org.academiadecodigo.pacman.grid.Position;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileHelper {

    public static List<Position> allPositions = new LinkedList<>();

    public static List<Position> path = new LinkedList<>();
    public static List<Position> walls = new LinkedList<>();
    public static List<Position> ghosts = new LinkedList<>();
    public static List<Position> points = new LinkedList<>();
    public static List<Position> apples = new LinkedList<>();
    public static List<Position> players = new LinkedList<>();
    public static List<Position> edibles;
    public static List<Position> movables;

    public static char currentChar;

    public static String readFromFile() {

        String path = "Map";
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

        String[] mapRow = readFromFile().split("\\n");

        for (int i = 0; i < mapRow.length; i++) {

            char[] mapColumn = mapRow[i].toCharArray();

            for (int j = 0; j < mapColumn.length; j++) {

                currentChar = mapColumn[j];
                Position position = new Position(j, i);

                if (currentChar == '1') {
                    walls.add(position);

                } else if (!walls.contains(position)) {
                    path.add(position);

                } else if (currentChar == 'P') {
                    players.add(position);

                } else if (currentChar == 'G') {
                    ghosts.add(position);

                } else if (currentChar == '') {
                    apples.add(position);

                } else if (currentChar == '.') {
                    points.add(position);
                }
            }

            allPositions();
        }
    }

    public static void allPositions() {

        getMovables();
        getEdibles();

        allPositions = new LinkedList<>();
        allPositions.addAll(walls);
        allPositions.addAll(movables);
        allPositions.addAll(edibles);
    }

    private static void getMovables() {

        movables = new LinkedList<>();
        movables.addAll(ghosts);
        movables.addAll(players);
    }

    public static void getEdibles() {

        edibles = new LinkedList<>();
        edibles.addAll(apples);
        edibles.addAll(points);
    }
}
