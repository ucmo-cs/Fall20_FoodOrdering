import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.scene.*;
import java.io.IOException;

public class MainStageController {
    static MainStageController instance = null;
    public Stage mainStage=new Stage();
    Parent root;
    @FXML Pane menuPane;
    @FXML Button buttonHome;

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
        scene.getStylesheets().add(getClass().getResource("FXML_Files/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("FXML_Files/login.css").toExternalForm());
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
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openEinsteinBrosMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/EisteinBrosScreen.fxml"));
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openStarbucksMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/StarBucksPane.fxml"));
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openChickFilletMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/ChickFilScreen.fxml"));
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openSpinPizzaMenu() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/SpinPizzaScreen.fxml"));
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openFrontScreen() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/FrontScreen.fxml"));
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openLoginPane() throws IOException {
        Pane paneLogin = FXMLLoader.load(getClass().getResource("FXML_Files/LoginScreen.fxml"));
        buttonHome.setVisible(false);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneLogin);
    }
}
