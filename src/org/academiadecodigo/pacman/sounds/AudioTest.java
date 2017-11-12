package org.academiadecodigo.pacman.sounds;

/**
 * Created by codecadet on 12/11/2017.
 */
public class AudioTest {

    public static void main(String[] args) {

        SoundManager soundManager = new SoundManager();

        Thread thread = new Thread(new SoundThread(SoundType.DEATH));
        thread.run();

    }

    public static class SoundThread implements Runnable {

        private SoundType soundType;

        public SoundThread(SoundType soundType) {

            this.soundType = soundType;

        }

        @Override
        public void run() {

            SoundManager.playSound(soundType);

        }

    }
}
