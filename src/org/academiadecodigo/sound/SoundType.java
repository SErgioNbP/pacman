package org.academiadecodigo.sound;

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
