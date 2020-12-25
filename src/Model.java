import java.util.ArrayList;

public class Model
{
    private ArrayList<ModelListener> listeners;
    public Model()
    {
        listeners = new ArrayList<ModelListener>();
    }
    public void addListener(ModelListener listener)
    {
        listeners.add(listener);
    }
}
