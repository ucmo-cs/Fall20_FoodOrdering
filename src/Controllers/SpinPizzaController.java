package Controllers;

import Models.CartModel;
import Models.FoodMenuItem;
import Models.RestaurantModel;
import Models.SQLCommands;
import Queries.RestaurantQueries;
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

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SpinPizzaController extends RestaurantBaseController{

    @FXML TabPane tabPaneSpinPizza;
    @FXML Tab tabPizza;
    @FXML Tab tabSalad;
    @FXML Tab tabSide;
    @FXML TableView<FoodMenuItem>tableViewPizza;
    @FXML TableView<FoodMenuItem>tableViewSalad;
    @FXML TableView<FoodMenuItem>tableViewSide;
    @FXML TableColumn columnPizzaName;
    @FXML TableColumn columnPizzaPrice;
    @FXML TableColumn columnPizzaAvailable;
    @FXML TableColumn columnSaladName;
    @FXML TableColumn columnSaladPrice;
    @FXML TableColumn columnSaladAvailable;
    @FXML TableColumn columnSideName;
    @FXML TableColumn columnSidePrice;
    @FXML TableColumn columnSideAvailable;
    OrderStageController orderStageController=new OrderStageController();
    private RestaurantModel spin = new RestaurantModel();
    private final static int RESTAURANT_ID = 5;

    public void initialize() throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        buildMenu(RESTAURANT_ID);
        fillTable();
        this.cart=CartModel.getInstance();
    }
    public void fillTable() throws SQLException {
        List<FoodMenuItem> spinFood = this.menuModel.getFoods();
        columnPizzaName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("name"));
        columnPizzaPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("price"));
        columnPizzaAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("available"));

        columnSaladName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("name"));
        columnSaladPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("price"));
        columnSaladAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("available"));

        columnSideName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("name"));
        columnSidePrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("price"));
        columnSideAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("available"));

        for (FoodMenuItem f : spinFood) {
            switch (f.type) {
                case "pizza": tableViewPizza.getItems().add(f); break;
                case "salad": tableViewSalad.getItems().add(f); break;
                case "side": tableViewSide.getItems().add(f); break;
            }
        }
    }

    public void getItem(){
        FoodMenuItem foodMenuItem=new FoodMenuItem();
        if(tabPaneSpinPizza.getSelectionModel().getSelectedItem().getText().equals("    Pizza    ")){
            foodMenuItem=tableViewPizza.getSelectionModel().getSelectedItem();
        }
        else if(tabPaneSpinPizza.getSelectionModel().getSelectedItem().getText().equals("    Salad    ")){
            foodMenuItem=tableViewSalad.getSelectionModel().getSelectedItem();
        }
        else if(tabPaneSpinPizza.getSelectionModel().getSelectedItem().getText().equals("    Side    ")){
            foodMenuItem=tableViewSide.getSelectionModel().getSelectedItem();
        }
        this.cart.setUser(this.user);
        this.cart.setRestaurant_id(RESTAURANT_ID);
        this.cart.appendCart(foodMenuItem);
    }

    public void openCheckout() throws Exception {
        orderStageController.openCartStage();
    }
}
