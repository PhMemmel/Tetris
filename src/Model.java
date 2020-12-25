import java.awt.*;
import java.util.ArrayList;

public class Model
{
    private ArrayList<ModelListener> listeners;
    private int[][] grid;
    TileShape currentTile;
    public Model()
    {
        listeners = new ArrayList<ModelListener>();
        grid = new int[10][24];
        currentTile = new Shape1();
    }
    public void addListener(ModelListener listener)
    {
        listeners.add(listener);
    }


    public void move(Movement movement)
    {
        currentTile.move(movement);
        notifyListeners();
    }
    private void notifyListeners()
    {
        for(var l : listeners)
            l.modelChanged();
    }

    public int getField(int x, int y)
    {
        int xOff = x- currentTile.x;
        int yOff = y - currentTile.y;

        System.out.println(x+ " " + y + " " + xOff + " " + yOff);
        if(xOff>-1 && xOff<4 && yOff>-1 && yOff<4)
            return grid[x][y] + currentTile.grid[xOff][yOff];
        return grid[x][y];
    }
}
