package Controllers;

import Models.CartModel;
import Models.FoodMenuItem;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class CheckoutScreenController {
    // Classes
    List<FoodMenuItem> cartItems=CartModel.getInstance().getCart();
    OrderStageController orderStageController=OrderStageController.getInstance();
    //Elements
    @FXML TableView<FoodMenuItem> tableViewCheckout;
    @FXML TableColumn columnCheckoutName;
    @FXML TableColumn columnCheckoutRestaurant;
    @FXML TableColumn columnCheckoutQuantity;
    @FXML TableColumn columnCheckoutPrice;
    @FXML Label labelTotalPrice;
    @FXML Label labelCheckoutTitle;

    public void initialize() throws SQLException{
        fillTable();
        setLabelCheckoutTitle();
        setLabelTotalPrice();
    }

    public void openCart() throws IOException {
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

    public void setLabelCheckoutTitle(){
        String restaurantName="";
        int id=CartModel.getInstance().getRestaurant_id();
        switch (id){
            case 1: restaurantName="Chick-Fil-A";
                    break;
            case 2: restaurantName="Taco Bell";
                    break;
            case 3: restaurantName="Starbucks";
                    break;
            case 4: restaurantName="Einstein Bros";
                    break;
            case 5: restaurantName="Spin! Pizza";
                    break;
            default:restaurantName="Welcome to";
        }
        labelCheckoutTitle.setText(restaurantName+" checkout");
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
        DecimalFormat format=new DecimalFormat("###.00");
        sTotal=format.format(fTotal);
        labelTotalPrice.setText("Total: $"+sTotal);
    }
}



