package org.academiadecodigo.pacman;

import java.io.*;

public class FileHelper {

    public static String readFromFile() {

        // ATTENTION: method need to take a path as argument, to be able to read from different files
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

            try{

                if (reader != null)
                    reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static File writeOnFile(File file) {
        return null;
    }
}
