import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.scene.*;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainStageController {

    public Stage mainStage=new Stage();
    Parent root;
    @FXML Pane menuPane;

    public void initialize() throws IOException {
        openLoginPane();
    }

    public void openMainStage()throws Exception {
        root = FXMLLoader.load(getClass().getResource("FXML_Files/MainStage.fxml"));
        Scene scene=new Scene(root,1000,700);
        scene.getStylesheets().add(getClass().getResource("FXML_Files/CSS/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("FXML_Files/CSS/login.css").toExternalForm());
        mainStage.setTitle("Test Stage");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openTestPane() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("FXML_Files/testFrontScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openLoginPane() throws IOException {
        Pane paneLogin = FXMLLoader.load(getClass().getResource("FXML_Files/testLoginScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneLogin);
    }
}
