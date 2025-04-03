import javafx.application.Application;
import javafx.stage.Stage;
import manager.SceneManager;
import view.MainMenuView;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        new MainMenuView().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}