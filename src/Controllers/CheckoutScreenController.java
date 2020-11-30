package Controllers;

import Models.CartModel;
import Models.FoodMenuItem;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CheckoutScreenController {
    // Classes
    OrderStageController orderStageController=OrderStageController.getInstance();
    //Elements
    @FXML TableView<FoodMenuItem> tableViewCheckout;
    @FXML TableColumn columnCheckoutName;
    @FXML TableColumn columnCheckoutRestaurant;
    @FXML TableColumn columnCheckoutQuantity;
    @FXML TableColumn columnCheckoutPrice;

    public void initialize() throws SQLException{
        fillTable();
    }

    public void openCart() throws IOException {
        orderStageController.openCartPane();
    }

    public void fillTable() throws SQLException {
        List<FoodMenuItem> cartItems= CartModel.getInstance().getCart();
        columnCheckoutName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnCheckoutRestaurant.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("restaurant"));
        columnCheckoutPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
//        columnCartQuantity.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("quantity"));

        for(FoodMenuItem f:cartItems){
            tableViewCheckout.getItems().add(f);
        }
    }
}


