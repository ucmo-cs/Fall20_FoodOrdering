package Controllers;

import Models.CartModel;
import Models.FoodMenuItem;
import Models.User;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Button;
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
    @FXML Button buttonSubmitOrder;

    public int restaurant_id;

    public void initialize() throws SQLException{
        fillTable();
        //this.
        //this.user = this.cart.getUser();
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

    public void submitOrder() throws Exception {
        CartModel cart = CartModel.getInstance();
        User user = cart.getUser();
        RestaurantBaseController.processOrder(cart.getCart(), String.valueOf(cart.getRestaurant_id()), user);
        System.out.printf("%s %s has submitted their order\n", user.getFname(), user.getID());
    }
}


