import Models.FoodMenuItem;
import Models.RestaurantModel;
import Queries.RestaurantQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.awt.*;

public class EinsteinBrosController {

    @FXML Button buttonCheckout;
    @FXML Button buttonAddCart;
    @FXML Tab tabBagels;
    @FXML Tab tabShmear;
    @FXML Tab tabDrinks;
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
        while(foods.next())
        {
            FoodMenuItem item = new FoodMenuItem(
                    foods.getString(1),
                    foods.getString(2),
                    foods.getString(3),
                    foods.getString(4),
                    foods.getString(5),
                    foods.getString(6));
            this.einstein.appendFood(item);
        }
        this.einstein.showFoods();
    }
}
