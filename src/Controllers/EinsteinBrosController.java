package Controllers;

import Models.FoodMenuItem;
import Models.RestaurantModel;
import Models.SQLCommands;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EinsteinBrosController extends RestaurantBaseController{

    @FXML TabPane tabPaneEinstein;
    @FXML Button buttonCheckout;
    @FXML Button buttonAddCart;
    @FXML Tab tabBagels;
    @FXML Tab tabShmear;
    @FXML Tab tabDrinks;
    @FXML TableView<FoodMenuItem> tableViewBagel;
    @FXML TableView<FoodMenuItem> tableViewShmear;
    @FXML TableView<FoodMenuItem> tableViewDrinks;
    @FXML TableColumn columnBagelsName;
    @FXML TableColumn columnBagelsPrice;
    @FXML TableColumn columnBagelsAvailable;
    @FXML TableColumn columnDrinksName;
    @FXML TableColumn columnDrinksPrice;
    @FXML TableColumn columnDrinksAvailable;
    @FXML TableColumn columnShmearName;
    @FXML TableColumn columnShmearPrice;
    @FXML TableColumn columnShmearAvailable;

    private RestaurantModel einstein = new RestaurantModel();
    private final static int RESTAURANT_ID = 4;

    public void initialize() throws Exception {
        SQLCommands sqlCommands=new SQLCommands();
        buildMenu(RESTAURANT_ID);
        fillTable();
    }

    public void fillTable() {
        List<FoodMenuItem> brosFood = this.menuModel.getFoods();
        columnBagelsName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnBagelsPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
        columnBagelsAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("available"));

        columnShmearName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnShmearPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnShmearAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        columnDrinksName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnDrinksPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnDrinksAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        for(FoodMenuItem f:brosFood) {
            switch (f.type) {
                case "bagel":   tableViewBagel.getItems().add(f);   break;
                case "shmear":  tableViewShmear.getItems().add(f);  break;
                case "drink":   tableViewDrinks.getItems().add(f);  break;
            }
        }
    }
    public void getItem(){
        if(tabPaneEinstein.getSelectionModel().getSelectedItem().getText().equals("      Bagels      ")){
            FoodMenuItem foodMenuItem=tableViewBagel.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItem.toString());
        }
        else if(tabPaneEinstein.getSelectionModel().getSelectedItem().getText().equals("      Shmear      ")){
            FoodMenuItem foodMenuItem=tableViewShmear.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItem.toString());
        }
        else if(tabPaneEinstein.getSelectionModel().getSelectedItem().getText().equals("      Drinks       ")){
            FoodMenuItem foodMenuItem=tableViewDrinks.getSelectionModel().getSelectedItem();
            System.out.println(foodMenuItem.toString());
        }
    }
}