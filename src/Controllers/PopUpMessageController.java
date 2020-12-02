package Controllers;

import Models.CartModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class PopUpMessageController {
    static PopUpMessageController instance=null;
    MainStageController mainStageController=MainStageController.getInstance();
    public Stage stagePopUp=new Stage();
    Parent root;
    @FXML Label labelWarning;
    @FXML Button buttonContinue;
    @FXML Button buttonCancel;
    boolean run;

    public void initialize(){
        instance=this;
        setLabelWarning();
    }

    public void setLabelWarning(){
        String restaurantName=CartModel.getInstance().getRestaurantName();
        String warningText;
        warningText="Alert, you have "+ CartModel.getInstance().getNumberOfItems()+" item(s) currently in your cart from "+restaurantName;
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

    // These buttons will return a value to the MainStageController
    // that sets the 'run' integer to one of two values.
    public void buttonContinue(){ mainStageController.setRun(0); }
    public void buttonCancel(){ mainStageController.setRun(1); }
}
