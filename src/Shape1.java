public class Shape1 extends TileShape
{
    public Shape1()
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
                grid[0][1] = 1;
                grid[1][1] = 1;
                grid[2][1] = 1;
                grid[3][1] = 1;
                break;
            case 2:
                grid[2][0] = 1;
                grid[2][1] = 1;
                grid[2][2] = 1;
                grid[2][3] = 1;
                break;
            case 3:
                grid[0][2] = 1;
                grid[1][2] = 1;
                grid[2][2] = 1;
                grid[3][2] = 1;
                break;
            case 4:
                grid[1][0] = 1;
                grid[1][1] = 1;
                grid[1][2] = 1;
                grid[1][3] = 1;
                break;
            default:
        }
    }


}
