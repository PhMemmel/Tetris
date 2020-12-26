import org.w3c.dom.UserDataHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class TileFactory
{
    Random random;
    private Class[] shapes;
    private static TileFactory instance = new TileFactory();
    public static TileFactory getInstance()
    {
        return instance;
    }
    private TileFactory()
    {
        random = new Random();
        shapes = new Class[] {Shape1.class, Shape2.class, Shape3.class, Shape4.class, Shape5.class, Shape6.class, Shape7.class};
    }
    public TileShape getNextShape()
    {
        var i = random.nextInt(shapes.length);
        try
        {
            return (TileShape) shapes[i].getDeclaredConstructor().newInstance();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
