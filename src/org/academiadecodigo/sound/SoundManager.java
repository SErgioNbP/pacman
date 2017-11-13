package org.academiadecodigo.sound;

import java.io.IOException;
import java.util.HashMap;

public class SoundManager {

    private static HashMap<SoundType, Sound> SOUNDMAP = new HashMap<>();

    public SoundManager() {

        for (SoundType soundType : SoundType.values()) {

            try {
                SOUNDMAP.put(soundType, new Sound(soundType.getPath()));

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void playSound(SoundType soundType){

        Sound currSound = SOUNDMAP.get(soundType);

        if(currSound == null) {
            System.out.println("No sound available");
            return;
        }

        currSound.play(true);
    }
}