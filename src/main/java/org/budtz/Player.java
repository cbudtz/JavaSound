package org.budtz;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

public class Player {
    /**
     *
     * @param resourceName filename of file in resourceFolder
     */
    public void playSoundAsync(String resourceName){
        new Thread(()->this.playSoundBlocking(resourceName)).start();
    }

    public void playSoundBlocking(String resourceName) {
        CountDownLatch syncLatch = new CountDownLatch(1);
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + resourceName);
        try (AudioInputStream stream = AudioSystem.getAudioInputStream(resourceAsStream)) {
            Clip clip = AudioSystem.getClip();
            // Listener which allow method return once sound is completed
            clip.addLineListener(e -> {
                if (e.getType() == LineEvent.Type.STOP) {
                    syncLatch.countDown();
                }
            });
            clip.open(stream);
            clip.start();
            syncLatch.await();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
