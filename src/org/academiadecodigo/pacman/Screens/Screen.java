package org.academiadecodigo.pacman.Screens;

import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by codecadet on 11/11/2017.
 */
public class Screen {

    //Properties
    private com.googlecode.lanterna.screen.Screen screen;
    private ScreenWriter writer;

    //Constructor
    public Screen(com.googlecode.lanterna.screen.Screen screen) {

        this.screen = screen;
        screen.setCursorPosition(null);
        writer = new ScreenWriter(this.screen);

    }

    //Methods
    public void drawScreen(ScreenType typeOfScreen) {

        switch (typeOfScreen) {

            case INITIAL_SCREEN:

                String title = "__________                                      \n" +
                        "\\______   \\_____    ____   _____ _____    ____  \n" +
                        " |     ___/\\__  \\ _/ ___\\ /     \\\\__  \\  /    \\ \n" +
                        " |    |     / __ \\\\  \\___|  Y Y  \\/ __ \\|   |  \\\n" +
                        " |____|    (____  /\\___  >__|_|  (____  /___|  /\n" +
                        "                \\/     \\/      \\/     \\/     \\/ ";

                String[] strings = title.split("\\n");

                for(int i = 0; i < strings.length; i++) {

                    screen.putString(3,1 + i,strings[i], Terminal.Color.YELLOW, Terminal.Color.BLACK);

                }

                String pacman = "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n" +
                        "▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▓▒░▒▓▒░▓\n" +
                        "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" +
                        "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n" +
                        "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "▓▓▓▓▓▓▓▓▓▓▀────▀▓▓▓▓▓▓▀─────▀▓▓▓▓▓▓▓▓▓▓▓▀─────▀▓▓▓▓▓▓▓▓\n" +
                        "▓▓▓▓▓▓▓▓▓───▀─▄▓▓▓▓▓▓▓─▀──▀──▓▓▓▓▓▓▓▓▓▓▓──▀──▀─▓▓▓▓▓▓▓▓\n" +
                        "▓▓▓▓▓▓▓▓▌────▓▓▓─▓▓─▓▓──▄─▄──▓▓─▓▓─▓▓─▓▓──▄─▄──▓▓▓▓▓▓▓▓\n" +
                        "▓▓▓▓▓▓▓▓▓─────▀▓▓▓▓▓▓▓─▀─▀─▀─▓▓▓▓▓▓▓▓▓▓▓─▀─▀─▀─▓▓▓▓▓▓▓▓\n" +
                        "▓▓▓▓▓▓▓▓▓▓▄────▄▓▓▓▓▓▓─▄─▄─▄─▓▓▓▓▓▓▓▓▓▓▓─▄─▄─▄─▓▓▓▓▓▓▓▓\n" +
                        "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" +
                        "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n" +
                        "▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▒▓▒░▓▒░▒▓▒░▓\n" +
                        "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀";

                String[] pacmanStrings = pacman.split("\\n");

                for (int i = 0; i < pacmanStrings.length; i++) {

                    screen.putString(0,8 + i,pacmanStrings[i], Terminal.Color.YELLOW, Terminal.Color.BLACK);

                }

                String instructions =
                        "                                .         .        \n" +
                                ".-..-..-,.-.-  .-.-..-. .-.-,  -|-.-.  .-.| .-. . .\n" +
                                "|-''  `'--'-'  -'|-'`-`-`-`'-   '-`-'  |-''-`-`-'-|\n" +
                                "'                '                     '        `-'";

                String[] instructionsStrings = instructions.split("\\n");

                for (int i = 0; i < instructionsStrings.length; i++) {

                    screen.putString(2,24 + i,instructionsStrings[i], Terminal.Color.YELLOW, Terminal.Color.BLACK);

                }

                screen.refresh();

                break;

            case GAME_SCREEN:



                break;

            case WINNING_FINAL_SCREEN:



                break;

            case LOSING_FINAL_SCREEN:



                break;
        }

    }

}
