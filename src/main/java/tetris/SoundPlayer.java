package tetris;

import com.goxr3plus.streamplayer.stream.*;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class SoundPlayer implements ModelListener {
    Model model;

    StreamPlayer themePlayer;
    StreamPlayer linePlayer;
    StreamPlayer rotatePlayer;
    StreamPlayer dropPlayer;

    public SoundPlayer(Model model) {

        this.model = model;

        themePlayer = new StreamPlayer();
        linePlayer = new StreamPlayer();
        rotatePlayer = new StreamPlayer();
        dropPlayer = new StreamPlayer();

        // disable extensive logging of stream players
        for (Handler h : LogManager.getLogManager().getLogger("").getHandlers()) {
            h.setLevel(Level.OFF);
        }

        try {
            themePlayer.open(getClass().getResource("theme.wav"));
            linePlayer.open(getClass().getResource("line.wav"));
            rotatePlayer.open(getClass().getResource("rotate.wav"));
            dropPlayer.open(getClass().getResource("drop.wav"));
            themePlayer.play();
        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }



        /*TinySound.init();
        music = TinySound.loadMusic("sounds/theme.wav");
        line = TinySound.loadSound("sounds/line.wav");
        rotate = TinySound.loadSound("sounds/rotate.wav");
        drop = TinySound.loadSound("sounds/drop.wav");
        music.play(true);*/
    }

    @Override
    public void modelChanged() {

    }

    @Override
    public void linesCleared() {
        try {
            linePlayer.play();
        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gameOver() {
        themePlayer.stop();
    }

    @Override
    public void rotated() {
        try {
            rotatePlayer.play();
        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void dropped() {
        try {
            dropPlayer.play();
        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }
    }
}
