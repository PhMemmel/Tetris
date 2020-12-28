package tetris;

public interface ModelListener
{
    void modelChanged();
    void linesCleared();
    void gameOver();
    void rotated();
    void dropped();
}
