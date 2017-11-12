package org.academiadecodigo.pacman.objects.fruit.powers;

public enum PowerType {
    DOUBLE_POINTS,
    IMMUNE,
    EDIBLEGHOSTS;

    public static PowerType randomPowerType() {

        return values()[(int) (Math.random() * values().length)];

    }
}
