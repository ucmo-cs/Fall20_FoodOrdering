package Controllers;

import Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.List;

public class ChickFillController extends RestaurantBaseController{
    @FXML TabPane tabPaneChick;
    @FXML Button buttonCheckout;
    @FXML Tab tabMain;
    @FXML Tab tabDessert;
    @FXML Tab tabDrinks;
    @FXML Tab tabSides;
    @FXML TableView<FoodMenuItem> tableViewMain;
    @FXML TableView<FoodMenuItem> tableViewDessert;
    @FXML TableView<FoodMenuItem> tableViewDrinks;
    @FXML TableView<FoodMenuItem> tableViewSides;
    @FXML TableColumn columnMainName;
    @FXML TableColumn columnMainPrice;
    @FXML TableColumn columnMainAvailable;
    @FXML TableColumn columnDessertName;
    @FXML TableColumn columnDessertPrice;
    @FXML TableColumn columnDessertAvailable;
    @FXML TableColumn columnDrinksName;
    @FXML TableColumn columnDrinksPrice;
    @FXML TableColumn columnDrinksAvailable;
    @FXML TableColumn columnSidesName;
    @FXML TableColumn columnSidesPrice;
    @FXML TableColumn columnSidesAvailable;

    OrderStageController orderStageController=new OrderStageController();
    private RestaurantModel chick = new RestaurantModel();
    private final static int RESTAURANT_ID = 1;

    public void initialize() throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        buildMenu(RESTAURANT_ID);
        fillTable();
        this.cart = CartModel.getInstance();
    }

    public void fillTable() throws SQLException {
        List<FoodMenuItem> chickFood = this.menuModel.getFoods();
        columnMainName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnMainPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
        columnMainAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("available"));

        columnDessertName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnDessertPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnDessertAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        columnDrinksName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnDrinksPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnDrinksAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        columnSidesName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnSidesPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnSidesAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        for(FoodMenuItem f:chickFood){
            switch (f.type) {
                case "main":    tableViewMain.getItems().add(f);        break;
                case "dessert": tableViewDessert.getItems().add(f);     break;
                case "drink":   tableViewDrinks.getItems().add(f);      break;
                case "side":   tableViewSides.getItems().add(f);      break;
            }
        }
    }
    public void getItem(){
        FoodMenuItem foodMenuItem=new FoodMenuItem();
        if(tabPaneChick.getSelectionModel().getSelectedItem().getText().equals("    Main   ")) {
            foodMenuItem= tableViewMain.getSelectionModel().getSelectedItem();
        }
        else if(tabPaneChick.getSelectionModel().getSelectedItem().getText().equals("   Dessert   ")){
            foodMenuItem=tableViewDessert.getSelectionModel().getSelectedItem();
        }
        else if(tabPaneChick.getSelectionModel().getSelectedItem().getText().equals("   Drinks   ")){
            foodMenuItem=tableViewDrinks.getSelectionModel().getSelectedItem();
        }
        else if(tabPaneChick.getSelectionModel().getSelectedItem().getText().equals("   Sides   ")){
            foodMenuItem=tableViewSides.getSelectionModel().getSelectedItem();
        }
        this.cart.setUser(this.user);
        this.cart.setRestaurant_id(RESTAURANT_ID);
        this.cart.appendCart(foodMenuItem);
    }

    public void openCheckout() throws Exception {
        orderStageController.openCartStage();
    }

}

