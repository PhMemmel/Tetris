import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
public class SoundPlayer implements ModelListener
{
    Model model;

    private Sound line, rotate, drop;
    private Music music;
    public SoundPlayer(Model model)
    {
        this.model = model;
        TinySound.init();
        music = TinySound.loadMusic("theme.wav");
        line = TinySound.loadSound("line.wav");
        rotate = TinySound.loadSound("rotate.wav");
        drop = TinySound.loadSound("drop.wav");
        music.play(true);
    }
    @Override
    public void modelChanged()
    {

    }

    @Override
    public void linesCleared()
    {
        line.play();
    }

    @Override
    public void gameOver()
    {
        music.stop();
        TinySound.shutdown();
    }

    @Override
    public void rotated()
    {
        rotate.play();
    }


    @Override
    public void dropped()
    {
        drop.play();
    }
}
