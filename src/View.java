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
        setBackground(Color.darkGray);
        frame.add(this);
        frame.setSize(650,800);
        frame.setResizable(false);
        frame.setLocation(400,50);
        this.addKeyListener(this);
        frame.setVisible(true);
        this.setFocusable(true);
        this.grabFocus();
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        var gameover = model.isGameOver();
        int scale = 35;
        for(int i = 0; i<10;i++)
        {
            for (int j = 4; j < 24; j++)
            {
                int colorCode =model.getField(i,j);
                graphics.setColor(gameover && colorCode>0 ? Color.lightGray : ShapeColor.getColor(colorCode));
                graphics.fillRect(i*scale+scale,(j-3)*scale,scale,scale);
                graphics.setColor(Color.darkGray);
                graphics.drawRect(i*scale+scale,(j-3)*scale,scale,scale);
            }
        }

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                int colorCode =model.getNextShapeField(i,j);
                graphics.setColor(colorCode == 0 ? Color.darkGray : ShapeColor.getColor(colorCode));
                graphics.fillRect(i*scale+13 * scale,j*scale+scale,scale,scale);
                graphics.setColor(Color.darkGray);
                graphics.drawRect(i*scale+ 13 * scale,j*scale+scale,scale,scale);
            }
        }
        graphics.setFont(new Font("Times",10,24));
        graphics.setColor(Color.red);
        graphics.drawString("Score: "+model.getScore(),13*scale, 5*scale);

    }


    public void addListener(ViewListener listener)
    {
        listeners.add(listener);
    }

    @Override
    public void modelChanged()
    {
        repaint();
    }

    @Override
    public void linesCleared()
    {

    }

    @Override
    public void gameOver()
    {

    }

    @Override
    public void rotated()
    {

    }

    @Override
    public void dropped()
    {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent)
    {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
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
            case 38:
                notifyListeners(Movement.rotate);
                break;
            case 32:
                notifyListeners(Movement.falldown);
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
