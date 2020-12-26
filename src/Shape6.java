public class Shape6 extends TileShape
{
    private final int colCode = 6;
    @Override
    public int getColorCode()
    {
        return colCode;
    }

    public Shape6()
    {
        super();

    }

    @Override
    protected void setShape()
    {
        if(rotationState<1 || rotationState > 4)
            return;
        grid = new int[4][4];
        switch (rotationState)
        {
            case 1:
                grid[1][0] = colCode;
                grid[0][1] = colCode;
                grid[1][1] = colCode;
                grid[2][1] = colCode;
                break;
            case 2:
                grid[1][0] = colCode;
                grid[1][1] = colCode;
                grid[2][1] = colCode;
                grid[1][2] = colCode;
                break;
            case 3:
                grid[0][1] = colCode;
                grid[1][1] = colCode;
                grid[2][1] = colCode;
                grid[1][2] = colCode;
                break;
            case 4:
                grid[1][0] = colCode;
                grid[0][1] = colCode;
                grid[1][1] = colCode;
                grid[1][2] = colCode;
                break;
            default:
        }
    }


}
