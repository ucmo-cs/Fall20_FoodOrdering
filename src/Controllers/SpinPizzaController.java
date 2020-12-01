package Controllers;

import Models.FoodMenuItem;
import Models.RestaurantModel;
import Models.SQLCommands;
import Queries.RestaurantQueries;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SpinPizzaController extends RestaurantBaseController{

    @FXML Tab tabMain;
    @FXML Tab tabDessert;
    @FXML Tab tabDrinks;
    @FXML TableView<FoodMenuItem>tableViewMain;
    @FXML TableView<FoodMenuItem>tableViewDesserts;
    @FXML TableView<FoodMenuItem>tableViewDrinks;
    @FXML TableColumn columnMainName;
    @FXML TableColumn columnMainPrice;
    @FXML TableColumn columnMainAvailable;
    @FXML TableColumn columnDessertName;
    @FXML TableColumn columnDessertPrice;
    @FXML TableColumn columnDessertAvailable;
    @FXML TableColumn columnDrinksName;
    @FXML TableColumn columnDrinksPrice;
    @FXML TableColumn columnDrinksAvailable;

    private RestaurantModel spin = new RestaurantModel();
    private final static int RESTAURANT_ID = 5;

    public void initialize() throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        buildMenu(RESTAURANT_ID);
        fillTable();
    }
    public void fillTable() throws SQLException {
        List<FoodMenuItem> spinFood = this.menuModel.getFoods();
        columnMainName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("name"));
        columnMainPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("price"));
        columnMainAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("available"));

        columnDessertName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("name"));
        columnDessertPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("price"));
        columnDessertAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("available"));

        columnDrinksName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("name"));
        columnDrinksPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("price"));
        columnDrinksAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem, String>("available"));

        for (FoodMenuItem f : spinFood) {
            switch (f.type) {
                case "main": tableViewMain.getItems().add(f); break;
                case "dessert": tableViewDesserts.getItems().add(f); break;
                case "drink": tableViewDrinks.getItems().add(f); break;
            }
        }
    }
    public void OpenCheckout() throws IOException {
        Parent checkout = FXMLLoader.load(getClass().getResource("/FXML_Files/CheckoutScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(checkout,1000,700);
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/login.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
