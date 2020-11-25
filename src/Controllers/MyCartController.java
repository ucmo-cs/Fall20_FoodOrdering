package Controllers;

import Models.FoodMenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;

public class MyCartController {
    // Classes
    OrderStageController orderStageController=OrderStageController.getInstance();
    // Elements
    @FXML TableView<FoodMenuItem> tableViewCart;
    @FXML TableColumn columnCartName;
    @FXML TableColumn columnCartPrice;
    @FXML TableColumn columnCartQuantity;
    @FXML Label labelSubtotal;

    public void close(){
        orderStageController.closeOrderStage();
    }

    public void openCheckout() throws IOException {
        orderStageController.openCheckoutPane();
    }
    //1. Take items from cart and populate table with the results.
    //2. Display subtotal, quantity and ensure availability of items in cart
    //3. If user clicks checkout button, switch pane to checkout and process payment information

}
