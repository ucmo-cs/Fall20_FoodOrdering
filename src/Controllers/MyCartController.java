package Controllers;

import Models.CartModel;
import Models.FoodMenuItem;
import com.sun.javafx.collections.MappingChange;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCartController {
    // Classes
    OrderStageController orderStageController=OrderStageController.getInstance();
    List<FoodMenuItem> cartItems=CartModel.getInstance().getCart();
    // Elements
    @FXML TableView<FoodMenuItem> tableViewCart;
    @FXML TableColumn columnCartName;
    @FXML TableColumn columnCartPrice;
    @FXML TableColumn columnCartQuantity;
    @FXML Label labelSubtotal;
    float fTotal=0;
    String sTotal="";

    public void initialize() throws SQLException {
        fillTable();
        setTotal();
    }

    public void close(){
        orderStageController.closeOrderStage();
    }

    public void openCheckout() throws IOException {
        orderStageController.openCheckoutPane();
    }

    public void fillTable() throws SQLException{
        columnCartName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnCartPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
//        columnCartQuantity.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("quantity"));

        for(FoodMenuItem f:cartItems){
            tableViewCart.getItems().add(f);
        }
    }

    public void setTotal(){
        for(FoodMenuItem f:cartItems){
            fTotal=fTotal+f.price;
        }
        sTotal=String.valueOf(fTotal);
        labelSubtotal.setText("Subtotal: $"+sTotal);
    }
    public void setQuantity(){
        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();
        for(FoodMenuItem f:cartItems){

        }
    }


}
