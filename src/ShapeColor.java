import java.awt.*;

public class ShapeColor
{
    private final static Color[] color = {Color.black, Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.magenta, Color.red};
    static Color getColor(int i)
    {
        if(i<0 || i>7)
            return Color.black;
        return color[i];
    }
}
