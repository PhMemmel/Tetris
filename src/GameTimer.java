import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends TimerTask implements ModelListener
{

    private Model model;
    private Timer timer;
    private int intervall;

    public GameTimer(Model model, int intervall)
    {
        this.model = model;
        this.intervall = intervall;

    }
    public void start()
    {
        timer = new Timer();
        timer.schedule(new GameTimer(model,intervall),intervall, intervall);
    }
    public synchronized void speedup()
    {
        timer.cancel();
        if(intervall>100)
            intervall -=20;
        else
            intervall = 80;
        start();
    }

    @Override
    public void run()
    {
        model.move(Movement.down);
    }

    @Override
    public void modelChanged()
    {

    }

    @Override
    public void linesCleared()
    {
        speedup();
    }

    @Override
    public void gameOver()
    {
        timer.cancel();
    }
}
