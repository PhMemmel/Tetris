package tetris;

public abstract class TileShape {
    protected int[][] grid;
    protected int x, y;
    protected byte rotationState;

    public abstract int getColorCode();

    /*public int[][] getGrid() {
        return grid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }*/

    public TileShape() {
        grid = new int[4][4];
        x = 3;
        y = 0;
        rotationState = 1;
        setShape();
    }

    public void rotate() {
        rotationState++;
        if (rotationState > 4)
            rotationState = 1;
        setShape();
    }

    public void rotateBack() {
        rotationState--;
        if (rotationState <= 0)
            rotationState = 4;
        setShape();
    }

    public void move(Movement movement) {
        switch (movement) {
            case LEFT ->  x--;
            case DOWN, FALLDOWN -> y++;
            case RIGHT -> x++;
            case ROTATE -> rotate();
        }
    }

    public void reverseMove(Movement movement) {
        switch (movement) {
            case LEFT -> x++;
            case DOWN, FALLDOWN -> y--;
            case RIGHT -> x--;
            case ROTATE -> rotateBack();
        }
    }

    protected abstract void setShape();

}
