package Controllers;

import Models.CartModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class PopUpMessageController {

    public Stage stagePopUp=new Stage();
    Parent root;
    int restaurantID;
    @FXML Label labelWarning;
    @FXML Button buttonContinue;
    @FXML Button buttonCancel;

    public void initialize(){
        setLabelWarning(restaurantID);
    }

    public void setLabelWarning(int restaurantID){
        String restaurantName;
        String warningText;
        switch (restaurantID){
            case 1 -> restaurantName="Chick-Fil-A";
            case 2 -> restaurantName="Taco Bell";
            case 3 -> restaurantName="Starbucks";
            case 4 -> restaurantName="Einstein Bros";
            case 5 -> restaurantName="Spin! Pizza";
            default -> throw new IllegalStateException("Unexpected value: " + restaurantID);
        }
        warningText="Alert, you have "+ CartModel.getInstance().getNumberOfItems()+" item(s) currently in your cart from "+restaurantName+" " +
                "and only one restaurant's items can be in your cart at a time. If you continue then the current items in your cart will be removed.";
        labelWarning.setText(warningText);
    }

    public void openPopUp() throws IOException {
        root= FXMLLoader.load(getClass().getResource("/FXML_Files/PopUpMessage.fxml"));
        Scene scene=new Scene(root,375,175);
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/login.css").toExternalForm());
        stagePopUp.setTitle("ALERT");
        stagePopUp.setScene(scene);
        stagePopUp.show();
    }

    public boolean buttonContinue(){
        return true;
    }

    public boolean buttonCancel(){
        return false;
    }
}
