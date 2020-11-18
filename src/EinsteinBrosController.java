import Models.FoodMenuItem;
import Models.MenuModel;
import Queries.RestaurantQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import javax.sql.rowset.CachedRowSet;

public class EinsteinBrosController extends RestaurantBaseController{

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

    private final static int RESTAURANT_ID = 4;

    public void initialize() throws Exception {
        buildMenu(RESTAURANT_ID);
        this.menuModel.showFoods();
    }
}
