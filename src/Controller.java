public class Controller implements ViewListener
{
    private View view;
    private Model model;
    private GameTimer gameTimer;
    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        this.view.addListener(this);
        gameTimer = new GameTimer(this.model);
        gameTimer.start();
    }


    @Override
    public void MovementKeyPressed(Movement movement)
    {
        model.move(movement);
    }


}
