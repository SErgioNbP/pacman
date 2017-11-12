package org.academiadecodigo.pacman.screens;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by codecadet on 11/11/2017.
 */
public class Screen {

    //Properties
    private com.googlecode.lanterna.screen.Screen screen;

    //Constructor
    public Screen(com.googlecode.lanterna.screen.Screen screen) {

        this.screen = screen;
        screen.setCursorPosition(null);

    }

    //Methods
    public void drawScreen(ScreenType typeOfScreen) {

        switch (typeOfScreen) {

            case INITIAL_SCREEN:

                String title =
                        "__________                                      \n" +
                        "\\______   \\_____    ____   _____ _____    ____  \n" +
                        " |     ___/\\__  \\ _/ ___\\ /     \\\\__  \\  /    \\ \n" +
                        " |    |     / __ \\\\  \\___|  Y Y  \\/ __ \\|   |  \\\n" +
                        " |____|    (____  /\\___  >__|_|  (____  /___|  /\n" +
                        "                \\/     \\/      \\/     \\/     \\/ ";

                String[] strings = title.split("\\n");

                for(int i = 0; i < strings.length; i++) {

                    screen.putString(13,1 + i,strings[i], Terminal.Color.YELLOW, Terminal.Color.BLACK);

                }

                String pacman =
                        "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n" +
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

                    screen.putString(10,8 + i,pacmanStrings[i], Terminal.Color.YELLOW, Terminal.Color.BLACK);

                }

                String instructions =
                                ";-.                            .             .             .         \n" +
                                "|  )                           |             |             |         \n" +
                                "|-'  ;-. ,-. ,-. ,-.   ,-. ;-. |-  ,-. ;-.   |-  ,-.   ;-. | ,-: . . \n" +
                                "|    |   |-' `-. `-.   |-' | | |   |-' |     |   | |   | | | | | | | \n" +
                                "'    '   `-' `-' `-'   `-' ' ' `-' `-' '     `-' `-'   |-' ' `-` `-| \n" +
                                "                                                       '         `-' ";

                String[] instructionsStrings = instructions.split("\\n");

                for (int i = 0; i < instructionsStrings.length; i++) {

                    screen.putString(3,24 + i,instructionsStrings[i], Terminal.Color.YELLOW, Terminal.Color.BLACK);

                }

                screen.refresh();

                break;

            case WINNING_FINAL_SCREEN:

                screen.clear();

                String youWord =
                        " __      __                  \n" +
                        "/  \\    /  |                 \n" +
                        "$$  \\  /$$/______   __    __ \n" +
                        " $$  \\/$$//      \\ /  |  /  |\n" +
                        "  $$  $$//$$$$$$  |$$ |  $$ |\n" +
                        "   $$$$/ $$ |  $$ |$$ |  $$ |\n" +
                        "    $$ | $$ \\__$$ |$$ \\__$$ |\n" +
                        "    $$ | $$    $$/ $$    $$/ \n" +
                        "    $$/   $$$$$$/   $$$$$$/  ";

                String[] firstWinStrings = youWord.split("\\n");

                for (int i = 0; i < firstWinStrings.length; i++) {

                    screen.putString(14,5 + i,firstWinStrings[i], Terminal.Color.GREEN, Terminal.Color.BLACK);

                }

                String win =
                        " __       __  __           \n" +
                        "/  |  _  /  |/  |          \n" +
                        "$$ | / \\ $$ |$$/  _______  \n" +
                        "$$ |/$  \\$$ |/  |/       \\ \n" +
                        "$$ /$$$  $$ |$$ |$$$$$$$  |\n" +
                        "$$ $$/$$ $$ |$$ |$$ |  $$ |\n" +
                        "$$$$/  $$$$ |$$ |$$ |  $$ |\n" +
                        "$$$/    $$$ |$$ |$$ |  $$ |\n" +
                        "$$/      $$/ $$/ $$/   $$/ ";

                String[] winStrings = win.split("\\n");

                for (int i = 0; i < winStrings.length; i++) {

                    screen.putString(15,15 + i,winStrings[i], Terminal.Color.GREEN, Terminal.Color.BLACK);

                }

                screen.refresh();

                break;

            case LOSING_FINAL_SCREEN:

                screen.clear();

                String you =
                        "▓██   ██▓ ▒█████   █    ██ \n" +
                        " ▒██  ██▒▒██▒  ██▒ ██  ▓██▒\n" +
                        "  ▒██ ██░▒██░  ██▒▓██  ▒██░\n" +
                        "  ░ ▐██▓░▒██   ██░▓▓█  ░██░\n" +
                        "  ░ ██▒▓░░ ████▓▒░▒▒█████▓ \n" +
                        "   ██▒▒▒ ░ ▒░▒░▒░ ░▒▓▒ ▒ ▒ \n" +
                        " ▓██ ░▒░   ░ ▒ ▒░ ░░▒░ ░ ░ \n" +
                        " ▒ ▒ ░░  ░ ░ ░ ▒   ░░░ ░ ░ \n" +
                        " ░ ░         ░ ░     ░     \n" +
                        " ░ ░                       ";

                String[] youStrings = you.split("\\n");


                for (int i = 0; i < youStrings.length; i++) {

                    screen.putString(14,5 + i,youStrings[i], Terminal.Color.RED, Terminal.Color.BLACK);

                }

                String lose =
                        " ██▓     ▒█████    ██████ ▓█████ \n" +
                        "▓██▒    ▒██▒  ██▒▒██    ▒ ▓█   ▀ \n" +
                        "▒██░    ▒██░  ██▒░ ▓██▄   ▒███   \n" +
                        "▒██░    ▒██   ██░  ▒   ██▒▒▓█  ▄ \n" +
                        "░██████▒░ ████▓▒░▒██████▒▒░▒████▒\n" +
                        "░ ▒░▓  ░░ ▒░▒░▒░ ▒ ▒▓▒ ▒ ░░░ ▒░ ░\n" +
                        "░ ░ ▒  ░  ░ ▒ ▒░ ░ ░▒  ░ ░ ░ ░  ░\n" +
                        "  ░ ░   ░ ░ ░ ▒  ░  ░  ░     ░   \n" +
                        "    ░  ░    ░ ░        ░     ░  ░";

                String[] loseStrings = lose.split("\\n");

                for (int i = 0; i < loseStrings.length; i++) {

                    screen.putString(11,15 + i, loseStrings[i], Terminal.Color.RED, Terminal.Color.BLACK);

                }

                screen.refresh();

                break;
        }

    }

}
