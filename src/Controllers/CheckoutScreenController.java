package Controllers;

import Models.FoodMenuItem;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import java.awt.*;
import java.io.IOException;

public class CheckoutScreenController {
    // Classes
    OrderStageController orderStageController=OrderStageController.getInstance();
    //Elements
    @FXML TableView<FoodMenuItem> tableViewCheckout;
    @FXML TableColumn columnCheckoutName;
    @FXML TableColumn columnCheckoutRestaurant;
    @FXML TableColumn columnCheckoutQuantity;
    @FXML TableColumn columnCheckoutPrice;

    public void initialize(){

    }

    public void openCart() throws IOException {
        orderStageController.openCartPane();
    }
}


