package tetris;

public class Shape4 extends TileShape
{
    private final int colCode = 4;
    @Override
    public int getColorCode()
    {
        return colCode;
    }

    public Shape4()
    {
        super();

    }

    @Override
    protected void setShape()
    {
        grid = new int[4][4];
        grid[1][0] = colCode;
        grid[2][0] = colCode;
        grid[1][1] = colCode;
        grid[2][1] = colCode;
    }


}
