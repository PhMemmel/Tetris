package tetris;

public class Controller implements ViewListener {
    private ViewFXCanvas view;
    private Model model;
    private GameTimer gameTimer;

    public Controller(ViewFXCanvas view, Model model) {
        this.view = view;
        this.model = model;
        view.addListener(this);
        gameTimer = new GameTimer(model, 500);
        model.addListener(gameTimer);
        gameTimer.start();
    }


    @Override
    public void MovementKeyPressed(Movement movement) {
        model.move(movement);
    }


}
