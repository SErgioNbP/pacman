package org.academiadecodigo.pacman;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;
import org.academiadecodigo.pacman.grid.Representation;
import org.academiadecodigo.pacman.objects.movables.Ghost;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileHelper {

    public static List<Position> allPositions;

    public static List<Position> walls;
    public static List<Position> path;
    public static List<Position> players;
    public static List<Position> ghosts;
    public static List<Position> apples;
    public static List<Position> points;

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

        // getGhosts
        // getPlayers
        // getPoints
        // getApples


        allPositions = new LinkedList<>();

        walls = new LinkedList<>();
        path = new LinkedList<>();
        players = new LinkedList<>();
        ghosts = new LinkedList<>();
        apples = new LinkedList<>();
        points = new LinkedList<>();

        mapRow = readFromFile("resources/map").split("\\n");

        for (int i = 0; i < mapRow.length; i++) {

            mapColumn = mapRow[i].toCharArray();

            for (int j = 0; j < mapColumn.length; j++) {

                char currentChar = mapColumn[j];
                Position position = new Position(j, i);

                if (currentChar == '0') {
                    walls.add(position);

                } else if (currentChar == '1') {
                    points.add(position);

                } else if (currentChar == '2') {
                    apples.add(position);

                } else if (currentChar == '3') {
                    ghosts.add(position);

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

        getMovables();
        getEdibles();

        allPositions.addAll(walls);
        allPositions.addAll(movables);
        allPositions.addAll(edibles);
    }

    private static void getMovables() {

        movables = new LinkedList<>();
        movables.addAll(ghosts);
        movables.addAll(players);
    }

    private static void getEdibles() {

        edibles = new LinkedList<>();
        edibles.addAll(apples);
        edibles.addAll(points);
    }

    public static char getCurrentChar(int col, int row) {

        mapColumn = mapRow[row].toCharArray();

        return mapColumn[col];
    }

    public static void decode(String receivedString, Ghost ghost) {

        String[] strings = receivedString.split(" ");

        if (strings[0].equals("Ghost")) {

            ghost.setPositionColRow(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        }
    }

    public static Ghost[] createGhosts() {

        //Ghost[] ghosts = new Ghost[ghostsPositions.size()];
        Ghost[] ghosts = new Ghost[1];

        ghosts[0] = new Ghost(new Position(42, 7));
        /*
        for (int i = 0; i < ghostsPositions.size(); i++) {
            Ghost ghost = new Ghost(ghostsPositions.get(i));
            ghosts[i] = ghost;
        }
        */

        return ghosts;

    }
}
