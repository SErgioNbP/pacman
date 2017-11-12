package org.academiadecodigo.pacman.sounds;

/**
 * Created by codecadet on 21/10/17.
 */
public enum SoundType {
    BEGINNING("beginning"),
    CHOMP("chomp"),
    EAT_FRUIT("eatfruit"),
    EAT_GHOST("eatghost"),
    DEATH("death");



    private String path;

    SoundType(String path) {
        this.path = "/audios/" + path + ".wav";
    }

    public String getPath() {
        return path;
    }
}
