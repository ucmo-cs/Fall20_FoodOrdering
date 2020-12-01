package Controllers;

import Models.FoodMenuItem;
import Models.SQLCommands;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

import javafx.scene.control.cell.PropertyValueFactory;

public class TacoBellController extends RestaurantBaseController {

    @FXML TabPane tabPaneTacoBell;
    @FXML Tab tabMain;
    @FXML Tab tabDessert;
    @FXML Tab tabDrinks;
    @FXML TableView<FoodMenuItem> tableViewMain;
    @FXML TableView<FoodMenuItem> tableViewDessert;
    @FXML TableView<FoodMenuItem> tableViewDrinks;
    @FXML TableColumn columnMainName;
    @FXML TableColumn columnMainPrice;
    @FXML TableColumn columnMainAvailable;
    @FXML TableColumn columnDessertName;
    @FXML TableColumn columnDessertPrice;
    @FXML TableColumn columnDessertAvailable;
    @FXML TableColumn columnDrinksName;
    @FXML TableColumn columnDrinksPrice;
    @FXML TableColumn columnDrinksAvailable;

    private final static int RESTAURANT_ID = 2;

    public void initialize() throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        buildMenu(RESTAURANT_ID);
        fillTable();
    }

    public void fillTable() {
        List<FoodMenuItem> tacoFood = this.menuModel.getFoods();
        columnMainName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnMainPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
        columnMainAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("available"));

        columnDessertName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnDessertPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnDessertAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        columnDrinksName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnDrinksPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnDrinksAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        for (FoodMenuItem f : tacoFood) {
            switch (f.type) {
                case "main":
                    List<FoodMenuItem> main = tableViewMain.getItems();
                    main.add(f);
                    //tableViewMain.getItems().add(f);    break;
                case "dessert": tableViewDessert.getItems().add(f); break;
                case "drink":   tableViewDrinks.getItems().add(f);  break;
            }
        }
    }

    public void getItem(){
        if(tabPaneTacoBell.getSelectionModel().getSelectedItem().getText().equals("       Main       ")){
            FoodMenuItem foodMenuItem=tableViewMain.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItem.toString());
        }
        else if(tabPaneTacoBell.getSelectionModel().getSelectedItem().getText().equals("       Dessert       ")){
            FoodMenuItem foodMenuItem=tableViewDessert.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItem.toString());
        }
        else if(tabPaneTacoBell.getSelectionModel().getSelectedItem().getText().equals("       Drinks       ")){
            FoodMenuItem foodMenuItem=tableViewDrinks.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItem.toString());
        }
    }
}

