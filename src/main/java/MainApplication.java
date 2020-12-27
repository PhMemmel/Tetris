import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class MainApplication extends Application {
    Model model;
    ViewFXCanvas canvas;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

        initializeMVC();


        stage.setTitle("Tetris");
        Group root = new Group();
        root.getChildren().add(canvas);

        stage.setScene(new Scene(root));
        stage.setOnCloseRequest( e -> {
            try {
                stop();
                System.exit(0);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        stage.show();

    }

    private void initializeMVC() {
        model = new Model();
        canvas = new ViewFXCanvas(model, 600, 850);

        Controller controller = new Controller(canvas, model);
        SoundPlayer soundPlayer = new SoundPlayer(model);
        model.addListener(soundPlayer);
    }


}
