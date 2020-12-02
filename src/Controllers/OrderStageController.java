package Controllers;

import Models.User;
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
    User user;
    @FXML Pane paneOrder;

    public static OrderStageController getInstance(){ return instance; }

    @FXML
    public void closeOrderStage(){
        paneOrder.getChildren().clear();
    }
    public void setUser(User u) { this.user = u; }

    public void initialize() throws IOException {
        instance=this;
        openCartPane();
    }

    public void openCartStage() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/OrderStage.fxml"));
        root = loader.load();
        Scene scene=new Scene(root,655,555);
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/login.css").toExternalForm());
        cartStage.setTitle("Your Cart");
        cartStage.setScene(scene);
        cartStage.show();
    }

    public void openCartPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/MyCartScreen.fxml"));
        Pane paneCart = loader.load();
        paneOrder.getChildren().clear();
        paneOrder.getChildren().add(paneCart);
    }

    public void openCheckoutPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/CheckoutScreen.fxml"));
        root = loader.load();
        Scene scene=new Scene(root,655,555);
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/login.css").toExternalForm());
        cartStage.setTitle("Checkout");
        cartStage.setScene(scene);
        cartStage.show();
    }
}
