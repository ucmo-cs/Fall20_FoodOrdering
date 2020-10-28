import Models.FoodMenuItem;
import Models.RestaurantModel;
import Queries.RestaurantQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import javax.sql.rowset.CachedRowSet;

public class StarBucksController {

    @FXML Tab tabDrinks;
    @FXML Tab tabBakery;
    @FXML TableColumn columnDrinksName;
    @FXML TableColumn columnDrinksPrice;
    @FXML TableColumn columnDrinksAvailable;
    @FXML TableColumn columnBakeryName;
    @FXML TableColumn columnBakeryPrice;
    @FXML TableColumn columnBakeryAvailable;

    private RestaurantModel bucks = new RestaurantModel();
    private final static int RESTAURANT_ID = 3;

    public void initialize() throws Exception {
        String getFoodsQuery = RestaurantQueries.getFoodsByRestaurantIDQuery(String.valueOf(RESTAURANT_ID));
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
            this.bucks.appendFood(item);
        }
        this.bucks.showFoods();
    }

}
