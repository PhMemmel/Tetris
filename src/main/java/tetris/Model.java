package tetris;

import java.util.ArrayList;

public class Model {
    private ArrayList<ModelListener> listeners;
    private int[][] grid;
    private TileFactory tileFactory;
    TileShape currentTile, nextTile;
    private boolean gameOver;
    private int score;

    public Model() {
        listeners = new ArrayList<>();
        grid = new int[10][24];
        tileFactory = TileFactory.getInstance();
        currentTile = tileFactory.getNextShape();
        nextTile = tileFactory.getNextShape();
        gameOver = false;
        score = 0;
    }

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    private void notifyGameoverListeners() {
        for (var l : listeners)
            l.gameOver();
    }

    public void move(Movement movement) {
        if (gameOver)
            return;
        currentTile.move(movement);
        if (currentTileIntercepts()) {
            currentTile.reverseMove(movement);
            if (movement == Movement.DOWN || movement == Movement.FALLDOWN)
                freezeTile();
        } else if (movement == Movement.ROTATE) {
            notifyRotateListeners();
        } else if (movement == Movement.FALLDOWN) {
            score++;
            move(movement);
        }
        notifyModelListeners();
    }

    private boolean currentTileIntercepts() {
        int x = currentTile.x;
        int y = currentTile.y;
        var tilegrid = currentTile.grid;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tilegrid[i][j] == 0)
                    continue;
                if ((x + i < 0 || x + i > 9 || j + y > 23))
                    return true;
                if (grid[i + x][j + y] != 0)
                    return true;
            }
        }
        return false;
    }

    private void notifyModelListeners() {
        for (var l : listeners)
            l.modelChanged();
    }

    private void notifyDropListeners() {
        for (var l : listeners)
            l.dropped();
    }

    private void freezeTile() {
        score++;
        int x = currentTile.x;
        int y = currentTile.y;
        int c = currentTile.getColorCode();
        var tilegrid = currentTile.grid;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tilegrid[i][j] != 0) {
                    grid[i + x][j + y] = c;
                    if (i + y < 4)
                        gameOver = true;
                }
            }
        }
        if (gameOver)
            notifyGameoverListeners();
        checkLine();
        currentTile = nextTile;
        nextTile = tileFactory.getNextShape();
        notifyModelListeners();
        notifyDropListeners();
    }

    private void checkLine() {
        int linesCleared = 0;
        for (int i = 23; i >= 0; i--) {
            boolean linecomplete = true;
            for (int j = 0; j < 10; j++) {
                if (grid[j][i] == 0) {
                    linecomplete = false;
                    break;
                }
            }
            if (linecomplete) {
                clearLine(i);
                linesCleared++;
                i++;
            }
        }
        switch (linesCleared) {
            case 1:
                score += 40;
                break;
            case 2:
                score += 100;
                break;
            case 3:
                score += 300;
                break;
            case 4:
                score += 1200;
                break;
            default:
        }
        if (linesCleared > 0)
            notifyLinesListeners();
    }

    private void notifyRotateListeners() {
        for (var l : listeners)
            l.rotated();
    }

    private void notifyLinesListeners() {
        for (var l : listeners)
            l.linesCleared();
    }

    private void clearLine(int l) {
        for (int i = l; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                grid[j][i] = grid[j][i - 1];
            }
        }
    }

    public int getField(int x, int y) {
        int xOff = x - currentTile.x;
        int yOff = y - currentTile.y;

        if (xOff > -1 && xOff < 4 && yOff > -1 && yOff < 4)
            return grid[x][y] + currentTile.grid[xOff][yOff];
        return grid[x][y];
    }

    public int getNextShapeField(int x, int y) {
        if (x < 0 || x > 3 || y < 0 || y > 3)
            return 0;
        return nextTile.grid[x][y];
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public int getScore() {
        return score;
    }


}
