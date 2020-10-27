import Models.FoodMenuItem;
import Models.RestaurantModel;
import Queries.RestaurantQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import javax.sql.rowset.CachedRowSet;

public class ChickFillController {

    @FXML Tab tabMain;
    @FXML Tab tabDessert;
    @FXML Tab tabDrinks;
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
            this.chick.appendFood(item);
        }
        this.chick.showFoods();
    }
}
