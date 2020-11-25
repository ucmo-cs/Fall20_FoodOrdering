package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class OrderStageController {
    static OrderStageController instance = null;
    public Stage cartStage=new Stage();
    Parent root;
    @FXML Pane paneOrder;

    public static OrderStageController getInstance(){ return instance; }

    public void closeOrderStage(){
        paneOrder.getChildren().clear();
    }

    public void initialize() throws IOException {
        instance=this;
        openCartPane();
    }

    public void openCartStage() throws Exception{
        root= FXMLLoader.load(getClass().getResource("/FXML_Files/OrderStage.fxml"));
        Scene scene=new Scene(root,655,555);
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/login.css").toExternalForm());
        cartStage.setTitle("Placeholder");
        cartStage.setScene(scene);
        cartStage.show();
    }

    public void openCartPane() throws IOException {
        Pane paneCart=FXMLLoader.load(getClass().getResource("/FXML_Files/MyCartScreen.fxml"));
        paneOrder.getChildren().clear();
        paneOrder.getChildren().add(paneCart);
    }

    public void openCheckoutPane() throws IOException {
        Pane paneCheckout=FXMLLoader.load(getClass().getResource("/FXML_Files/CheckoutScreen.FXML"));
        paneOrder.getChildren().clear();
        paneOrder.getChildren().add(paneCheckout);
    }
}
