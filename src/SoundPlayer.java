import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SoundPlayer implements ModelListener
{
    Model model;

    public SoundPlayer(Model model)
    {
        this.model = model;
        try
        {
            var audioin = AudioSystem.getAudioInputStream(new File("./sounds/theme.wav").getAbsoluteFile());
            var clip = AudioSystem.getClip();
            clip.open(audioin);
             clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e)
        {
            System.out.println(e);
        }

    }
    @Override
    public void modelChanged()
    {

    }

    @Override
    public void linesCleared()
    {

        playSound("sounds/line.wav");
    }

    @Override
    public void gameOver()
    {

    }

    @Override
    public void rotated()
    {
        playSound("./sounds/rotate.wav");

    }

    private void playSound(String s)
    {
        try
        {
            var audioin = AudioSystem.getAudioInputStream(new File(s).getAbsoluteFile());
            var clip = AudioSystem.getClip();
            while (clip.isRunning())
                Thread.sleep(10);
            clip.open(audioin);
            clip.start();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    @Override
    public void dropped()
    {
        playSound("./sounds/drop.wav");
    }
}
