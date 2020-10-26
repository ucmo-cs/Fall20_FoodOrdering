import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.scene.*;
import java.io.IOException;

public class MainStageController {
    static MainStageController instance = null;
    public Stage mainStage=new Stage();
    Parent root;
    @FXML Pane menuPane;

    public static MainStageController getInstance() {
        return instance;
    }

    public void initialize() throws IOException {
        instance = this;
        openLoginPane();
    }



    public void openMainStage()throws Exception {
        root = FXMLLoader.load(getClass().getResource("FXML_Files/MainStage.fxml"));
        Scene scene=new Scene(root,1000,700);
//        scene.getStylesheets().add(getClass().getResource("FXML_Files/CSS/test.css").toExternalForm());
//        scene.getStylesheets().add(getClass().getResource("FXML_Files/CSS/login.css").toExternalForm());
        mainStage.setTitle("Test Stage");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void closeMainStage(){
        menuPane.getChildren().clear();
        mainStage.close();
    }

    public void openTacoBellMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/TacoBellScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openEinsteinBrosMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/EisteinBrosScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openStarbucksMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/StarBucksPane.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openChickFilletMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/ChickFilScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openSpinPizzaMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/SpinPizzaScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openTestPane() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/FrontScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openLoginPane() throws IOException {
        Pane paneLogin = FXMLLoader.load(getClass().getResource("FXML_Files/LoginScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneLogin);
    }
}
