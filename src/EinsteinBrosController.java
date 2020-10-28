import Models.FoodMenuItem;
import Models.RestaurantModel;
import Queries.RestaurantQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.awt.*;
import java.sql.SQLException;

public class EinsteinBrosController {

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
        String getFoodsQuery = RestaurantQueries.getFoodsByID(String.valueOf(RESTAURANT_ID));
        SQLCommands sqlCommands = new SQLCommands();
        CachedRowSet foods = sqlCommands.readDataBase(1, getFoodsQuery);
        fillTable(foods);
        this.einstein.showFoods();
    }

    public void fillTable(CachedRowSet foods) throws SQLException {
        // Set the cell values for the table
        columnBagelsName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("name"));
        columnBagelsPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("price"));
        columnBagelsAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("available"));

        columnShmearName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnShmearPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnShmearAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        columnDrinksName.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Name"));
        columnDrinksPrice.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Price"));
        columnDrinksAvailable.setCellValueFactory(new PropertyValueFactory<FoodMenuItem,String>("Available"));

        while(foods.next()){
            FoodMenuItem item = new FoodMenuItem(
                    foods.getString(1),
                    foods.getString(2),
                    foods.getString(3),
                    foods.getString(4),
                    foods.getString(5),
                    foods.getString(6));
            this.einstein.appendFood(item);

            switch (item.type) {
                case "bagel" -> tableViewBagel.getItems().add(item);
                case "shmear" -> tableViewShmear.getItems().add(item);
                case "drink" -> tableViewDrinks.getItems().add(item);
            }
        }
    }
}
