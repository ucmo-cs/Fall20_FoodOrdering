import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        LoginController loginController=new LoginController();
        MainStageController mainStageController=new MainStageController();
        mainStageController.openMainStage();

    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}