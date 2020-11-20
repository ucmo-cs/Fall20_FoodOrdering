package Controllers;

import Models.FoodMenuItem;
import Models.SQLCommands;
import Models.RestaurantModel;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class ChickFillController extends RestaurantBaseController{

    @FXML TabPane tabPaneChick;
    @FXML Tab tabMain;
    @FXML Tab tabDessert;
    @FXML Tab tabDrinks;
    @FXML TableView<FoodMenuItem> tableViewMain;
    @FXML TableView<FoodMenuItem> tableViewDessert;
    @FXML TableView<FoodMenuItem> tableViewDrinks;
    @FXML TableView<FoodMenuItem> tableViewSide;
    @FXML TableColumn columnMainName;
    @FXML TableColumn columnMainPrice;
    @FXML TableColumn columnMainAvailable;
    @FXML TableColumn columnDessertName;
    @FXML TableColumn columnDessertPrice;
    @FXML TableColumn columnDessertAvailable;
    @FXML TableColumn columnDrinksName;
    @FXML TableColumn columnDrinksPrice;
    @FXML TableColumn columnDrinksAvailable;

    private RestaurantModel chick = new RestaurantModel();
    private final static int RESTAURANT_ID = 1;

    public void initialize() throws Exception {
        buildMenu(RESTAURANT_ID);
        fillTable();
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

        for(FoodMenuItem f:chickFood){
            switch (f.type) {
                case "main":    tableViewMain.getItems().add(f);    break;
                case "dessert": tableViewDessert.getItems().add(f); break;
                case "drink":   tableViewDrinks.getItems().add(f);  break;
                case "side":    tableViewSide.getItems().add(f);    break;
            }
        }
    }
    public void getItem(){
        if(tabPaneChick.getSelectionModel().getSelectedItem().getText().equals("    Main   ")) {
            FoodMenuItem foodMenuItemMain = tableViewMain.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItemMain.toString());
        }
        else if(tabPaneChick.getSelectionModel().getSelectedItem().getText().equals("   Dessert   ")){
            FoodMenuItem foodMenuItemDessert=tableViewDessert.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItemDessert.toString());
        }
        else if(tabPaneChick.getSelectionModel().getSelectedItem().getText().equals("   Drinks   ")){
            FoodMenuItem foodMenuItemDrink=tableViewDrinks.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItemDrink.toString());
        }
    }
}

