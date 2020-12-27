import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class SoundPlayer implements ModelListener {
    Model model;

    private Sound line, rotate, drop;
    private Music music;

    public SoundPlayer(Model model) {
        this.model = model;
        TinySound.init();
        music = TinySound.loadMusic("sounds/theme.wav");
        line = TinySound.loadSound("sounds/line.wav");
        rotate = TinySound.loadSound("sounds/rotate.wav");
        drop = TinySound.loadSound("sounds/drop.wav");
        music.play(true);
    }

    @Override
    public void modelChanged() {

    }

    @Override
    public void linesCleared() {
        line.play();
    }

    @Override
    public void gameOver() {
        music.stop();
        TinySound.shutdown();
    }

    @Override
    public void rotated() {
        rotate.play();
    }


    @Override
    public void dropped() {
        drop.play();
    }
}
