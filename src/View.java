import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class View extends JPanel implements ModelListener, KeyListener
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
        this.setFocusable(true);
        this.grabFocus();
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        for(int i = 0; i<10;i++)
        {
            for (int j = 0; j < 24; j++)
            {
                int colorCode =model.getField(i,j);
                graphics.setColor(j<4 && colorCode == 0 ? Color.lightGray : ShapeColor.getColor(colorCode));
                graphics.fillRect(i*20+20,j*20+20,20,20);
                graphics.setColor(Color.darkGray);
                graphics.drawRect(i*20+20,j*20+20,20,20);
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
        if(model.isGameOver())
            System.exit(0);
        repaint();
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
            case 38:
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
