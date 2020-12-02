package Controllers;

import Models.*;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class CheckoutScreenController {
    // Classes
    List<FoodMenuItem> cartItems=CartModel.getInstance().getCart();
    OrderStageController orderStageController=OrderStageController.getInstance();
    MyCartController cartController=new MyCartController();
    //Elements
    @FXML TableView<FoodMenuItem> tableViewCheckout;
    @FXML TableColumn columnCheckoutName;
    @FXML TableColumn columnCheckoutRestaurant;
    @FXML TableColumn columnCheckoutQuantity;
    @FXML TableColumn columnCheckoutPrice;
    @FXML Button buttonSubmitOrder;
    @FXML Button buttonCancel;
    public int restaurant_id;
    @FXML Label labelTotalPrice;
    @FXML Label labelCheckoutTitle;
    @FXML Label labelComplete;

    public void initialize() throws SQLException{
        fillTable();
        setLabelCheckoutTitle();
        setLabelTotalPrice();
        labelComplete.setVisible(false);
    }
    public void close() throws IOException {
        orderStageController.openCartPane();
    }
    public void fillTable() throws SQLException {
        List<FoodMenuItem> cartItems = CartModel.getInstance().getCart();
        columnCheckoutName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("name"));
        columnCheckoutPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("price"));

        for (FoodMenuItem f : cartItems) {
            tableViewCheckout.getItems().add(f);
        }
    }

    public void submitOrder() throws Exception {
        CartModel cart = CartModel.getInstance();
        User user = cart.getUser();
        RestaurantBaseController.processOrder(cart.getCart(), String.valueOf(cart.getRestaurant_id()), user);
        cart.emptyCart();
        tableViewCheckout.getItems().clear();
        labelTotalPrice.setVisible(false);
        fillTable();
        showOrderComplete();
        System.out.printf("%s %s has submitted their order\n", user.getFname(), user.getID());
    }

    public void setLabelCheckoutTitle(){
        labelCheckoutTitle.setText(CartModel.getInstance().getRestaurantName()+" checkout");
    }

    public void setLabelTotalPrice(){
        double fTotal=0;
        double salesTax=0;
        double taxRate=.0885;
        String sTotal="";
        for(FoodMenuItem f:cartItems){
            fTotal=fTotal+f.price;
        }
        salesTax=fTotal*taxRate;
        fTotal=fTotal+salesTax;
        DecimalFormat format=new DecimalFormat("##0.00");
        sTotal=format.format(fTotal);
        labelTotalPrice.setText("Total: $"+sTotal);
    }
    public void showOrderComplete(){
        labelComplete.setVisible(true);
    }
}


