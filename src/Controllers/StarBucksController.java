package Controllers;

import Models.CartModel;
import Models.FoodMenuItem;
import Models.RestaurantModel;
import Models.SQLCommands;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StarBucksController extends RestaurantBaseController {

    @FXML TabPane tabPaneStarbucks;
    @FXML Tab tabDrinks;
    @FXML Tab tabBakery;
    @FXML TableView<FoodMenuItem> tableViewDrinks;
    @FXML TableView<FoodMenuItem> tableViewBakery;
    @FXML TableColumn columnDrinksName;
    @FXML TableColumn columnDrinksPrice;
    @FXML TableColumn columnDrinksAvailable;
    @FXML TableColumn columnBakeryName;
    @FXML TableColumn columnBakeryPrice;
    @FXML TableColumn columnBakeryAvailable;
    OrderStageController orderStageController=new OrderStageController();
    private RestaurantModel bucks = new RestaurantModel();
    private final static int RESTAURANT_ID = 3;

    public void initialize() throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        buildMenu(RESTAURANT_ID);
        fillTable();
        this.cart=CartModel.getInstance();
    }

    public void fillTable() throws SQLException {
        List<FoodMenuItem> starFood=this.menuModel.getFoods();
        columnDrinksName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnDrinksPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
        columnDrinksAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("available"));

        columnBakeryName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnBakeryPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
        columnBakeryAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("available"));

        for(FoodMenuItem f:starFood){
            switch (f.type) {
                case "drink":   tableViewDrinks.getItems().add(f);  break;
                case "bakery":  tableViewBakery.getItems().add(f);  break;
                default:        System.err.println("bad food");     break;
            }
        }
    }

    public void getItem(){
        FoodMenuItem foodMenuItem=new FoodMenuItem();
        if(tabPaneStarbucks.getSelectionModel().getSelectedItem().getText().equals("       Drinks       ")){
            foodMenuItem=tableViewDrinks.getSelectionModel().getSelectedItem();
        }
        else if(tabPaneStarbucks.getSelectionModel().getSelectedItem().getText().equals("      Bakery      ")){
            foodMenuItem=tableViewBakery.getSelectionModel().getSelectedItem();
        }
        this.cart.setUser(this.user);
        this.cart.setRestaurant_id(RESTAURANT_ID);
        this.cart.appendCart(foodMenuItem);
    }

    public void openCheckout() throws Exception {
        orderStageController.openCartStage();
    }
}
