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

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        for(int i = 0; i<10;i++)
        {
            for (int j = 0; j < 24; j++)
            {
                graphics.setColor(ShapeColor.getColor(model.getField(i,j)));
                graphics.fillRect(i*20+20,j*20+20,20,20);
            }
        }
    }


    public void addListener(ViewListener listener)
    {
        listeners.add(listener);
    }

    @Override
    public void modelChanged()
    {
        paint(getGraphics());
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
            case 32:
                notifyListeners(Movement.rotate);
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
