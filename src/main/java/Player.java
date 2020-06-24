import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

public class Player {

    public static void main(String[] args) throws InterruptedException {
        new Player().playSound();

    }
    private void playSound() throws InterruptedException {
        CountDownLatch syncLatch = new CountDownLatch(1);
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/You Shall Not Pass.wav");
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
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        syncLatch.await();
    }
}
