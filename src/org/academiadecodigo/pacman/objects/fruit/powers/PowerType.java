package org.academiadecodigo.pacman.objects.fruit.powers;

public enum PowerType {
    DOUBLE_POINTS("Double Points!"),
    IMMUNE("Immunity!"),
    EDIBLE_GHOSTS("Eat Ghosts!");

    private String message;

    PowerType(String message) {

        this.message = message;

    }

    public static PowerType randomPowerType() {

        return values()[(int) (Math.random() * values().length)];

    }

    @Override
    public String toString() {
        return message;
    }
}
