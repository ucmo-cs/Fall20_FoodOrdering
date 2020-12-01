package Controllers;

import Models.CartModel;
import Models.FoodMenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyCartController {
    // Classes
    OrderStageController orderStageController=OrderStageController.getInstance();
    // Elements
    @FXML TableView<FoodMenuItem> tableViewCart;
    @FXML TableColumn columnCartName;
    @FXML TableColumn columnCartPrice;
    @FXML TableColumn columnCartQuantity;
    @FXML Label labelSubtotal;

    public void initialize() throws SQLException {
        fillTable();
    }

    public void close(){
        orderStageController.closeOrderStage();
    }

    public void openCheckout() throws IOException {
            Parent checkout = FXMLLoader.load(getClass().getResource("/FXML_Files/CheckoutScreen.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(checkout,1000,700);
            scene.getStylesheets().add(getClass().getResource("/FXML_Files/test.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/FXML_Files/login.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

    public void fillTable() throws SQLException{
        List<FoodMenuItem> cartItems=CartModel.getInstance().getCart();
        columnCartName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnCartPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
//        columnCartQuantity.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("quantity"));

        for(FoodMenuItem f:cartItems){
            tableViewCart.getItems().add(f);
        }

    }

    //1. Take items from cart and populate table with the results.
    //2. Display subtotal, quantity and ensure availability of items in cart
    //3. If user clicks checkout button, switch pane to checkout and process payment information

}
