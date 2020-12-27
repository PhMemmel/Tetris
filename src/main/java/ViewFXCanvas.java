import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.ArrayList;

public class ViewFXCanvas extends Canvas implements ModelListener {
    Model model;
    ArrayList<ViewListener> listeners;

    public ViewFXCanvas(Model model, int width, int height) {
        super(width, height);
        this.model = model;
        model.addListener(this);
        setFocusTraversable(true);
        listeners = new ArrayList<>();
        setOnKeyPressed((e) -> keyPressed(e.getCode()));
    }

    public void paintComponent(GraphicsContext graphics) {
        var gameover = model.isGameOver();
        int scale = 35;
        for (int i = 0; i < 10; i++) {
            for (int j = 4; j < 24; j++) {
                int colorCode = model.getField(i, j);
                graphics.setFill(gameover && colorCode > 0 ? Color.LIGHTGRAY : ShapeColorFX.getColor(colorCode));
                graphics.fillRect(i * scale + scale, (j - 3) * scale, scale, scale);
                graphics.setStroke(Color.DARKGRAY);
                graphics.strokeRect(i * scale + scale, (j - 3) * scale, scale, scale);


            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int colorCode = model.getNextShapeField(i, j);
                graphics.setFill(colorCode == 0 ? Color.LIGHTGRAY : ShapeColorFX.getColor(colorCode));
                graphics.fillRect(i * scale + 13 * scale, j * scale + scale, scale, scale);
                graphics.setStroke(Color.DARKGRAY);
                graphics.strokeRect(i * scale + 13 * scale, j * scale + scale, scale, scale);


            }
        }
        graphics.setFont(new Font("Times", 24));
        graphics.setFill(Color.RED);
        graphics.fillText("Score: " + model.getScore(), 13 * scale, 7 * scale);

    }


    public void addListener(ViewListener listener) {

        listeners.add(listener);
    }

    @Override
    public void modelChanged() {
        // complete repaint currently only needed for text
        // TODO make it more efficient: repaint completely and only paint boxes with tile
        getGraphicsContext2D().clearRect(
                0, 0, getWidth(), getHeight());
        Platform.runLater(() -> paintComponent(getGraphicsContext2D()));

    }

    @Override
    public void linesCleared() {

    }

    @Override
    public void gameOver() {

    }

    @Override
    public void rotated() {

    }

    @Override
    public void dropped() {

    }


    public void keyPressed(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT:
                notifyListeners(Movement.LEFT);
                break;
            case DOWN:
                notifyListeners(Movement.DOWN);
                break;
            case RIGHT:
                notifyListeners(Movement.RIGHT);
                break;
            case UP:
                notifyListeners(Movement.ROTATE);
                break;
            case SPACE:
                notifyListeners(Movement.FALLDOWN);
                break;
            default:
        }
    }


    private void notifyListeners(Movement movement) {
        for (var l : listeners)
            l.MovementKeyPressed(movement);
    }

}
