import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends TimerTask
{

    private Model model;
    private Timer timer;
    public GameTimer(Model model)
    {
        this.model = model;
        timer = new Timer();
    }
    public void start()
    {
        timer.schedule(this,500, 500);
    }

    @Override
    public void run()
    {
        model.move(Movement.down);
    }
}
