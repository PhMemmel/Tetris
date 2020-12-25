import java.awt.*;
import java.util.ArrayList;

public class Model
{
    private ArrayList<ModelListener> listeners;
    private int[][] grid;
    private TileFactory tileFactory;
    TileShape currentTile;
    private boolean gameOver;
    public Model()
    {
        listeners = new ArrayList<ModelListener>();
        grid = new int[10][24];
        tileFactory = TileFactory.getInstance();
        currentTile = tileFactory.getNextShape();
        gameOver = false;
    }
    public void addListener(ModelListener listener)
    {
        listeners.add(listener);
    }


    public void move(Movement movement)
    {
        currentTile.move(movement);
        if(currentTileIntercepts())
        {
            currentTile.reverseMove(movement);
            if(movement == Movement.down)
                freezeTile();
        }
        notifyListeners();
    }
    private boolean currentTileIntercepts()
    {
        int x = currentTile.x;
        int y = currentTile.y;
        var tilegrid = currentTile.grid;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(tilegrid[i][j]==0)
                    continue;
                if((x+i<0 || x+i>9 || j+y>23 ))
                    return true;
                if(grid[i+x][j+y]!=0)
                    return true;
            }
        }
        return false;
    }
    private void notifyListeners()
    {
        for(var l : listeners)
            l.modelChanged();
    }
    private void freezeTile()
    {
        int x = currentTile.x;
        int y = currentTile.y;
        int c = currentTile.getColorCode();
        var tilegrid = currentTile.grid;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(tilegrid[i][j]!=0)
                {
                    grid[i + x][j + y] = c;
                    if(i+y<4)
                        gameOver = true;
                }
            }
        }
        checkLine();
        currentTile = tileFactory.getNextShape();
        notifyListeners();
    }

    private void checkLine()
    {
        for(int i=23;i>=0;i--)
        {
            boolean linecomplete = true;
            for(int j=0;j<10 && linecomplete;j++)
            {
                if(grid[j][i] == 0)
                    linecomplete=false;
            }
            if(linecomplete)
            {
                clearLine(i);
                i++;
            }
        }
    }
    private void clearLine(int l)
    {
        for(int i = l; i>0;i--)
        {
            for(int j=0;j<10;j++)
            {
                grid[j][i] = grid[j][i-1];
            }
        }
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

    public boolean isGameOver()
    {
        return gameOver;
    }
}
