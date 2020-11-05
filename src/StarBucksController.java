import Models.FoodMenuItem;
import Models.MenuModel;
import Queries.RestaurantQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import javax.sql.rowset.CachedRowSet;

public class StarBucksController extends RestaurantBaseController{

    @FXML Tab tabDrinks;
    @FXML Tab tabBakery;
    @FXML TableColumn columnDrinksName;
    @FXML TableColumn columnDrinksPrice;
    @FXML TableColumn columnDrinksAvailable;
    @FXML TableColumn columnBakeryName;
    @FXML TableColumn columnBakeryPrice;
    @FXML TableColumn columnBakeryAvailable;

    private final static int RESTAURANT_ID = 3;

    public void initialize() throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        buildMenu(RESTAURANT_ID);
        this.menuModel.showFoods();
    }

}
