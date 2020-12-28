package tetris;

import javafx.scene.paint.Color;

public class ShapeColorFX {
    private final static Color[] color = {Color.BLACK, Color.CYAN, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.MAGENTA, Color.RED};

    static Color getColor(int i) {
        if (i < 0 || i > 7)
            return Color.BLACK;
        return color[i];
    }
}
