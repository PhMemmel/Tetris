public class Controller implements ViewListener
{
    private View view;
    private Model model;

    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        this.view.addListener(this);
    }


    @Override
    public void MovementKeyPressed(Movement movement)
    {
        model.move(movement);
    }
}
