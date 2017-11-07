package org.academiadecodigo.pacman.representation;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.pacman.grid.Position;

import java.awt.*;

public class Representation {

    static private Screen screen;
    static private ScreenWriter screenWriter;

    public Representation(Screen screen, ScreenWriter screenWriter) {

        this.screenWriter = screenWriter;
        this.screen = screen;

    }



}
