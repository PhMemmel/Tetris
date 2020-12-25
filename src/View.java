import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class View extends Canvas implements ModelListener, KeyListener
{
    Model model;
    Frame frame;
    ArrayList<ViewListener> listeners;
    public View(Model model)
    {
        this.model = model;
        this.model.addListener(this);
        listeners = new ArrayList<>();
        frame = new Frame("Tetris");
        frame.add(this);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setLocation(300,300);
        this.addKeyListener(this);
        frame.setVisible(true);
    }
    public void addListener(ViewListener listener)
    {
        listeners.add(listener);
    }

    @Override
    public void modelChanged()
    {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent)
    {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        System.out.println(keyEvent.getKeyCode());
        switch (keyEvent.getKeyCode())
        {
            case 37:
                notifyListeners(Movement.left);
                break;
            case 40:
                notifyListeners(Movement.down);
                break;
            case 39:
                notifyListeners(Movement.right);
                break;
            default:
        }
    }

    private void notifyListeners(Movement movement)
    {
        for(var l : listeners)
            l.MovementKeyPressed(movement);
    }
    @Override
    public void keyReleased(KeyEvent keyEvent)
    {

    }
}
