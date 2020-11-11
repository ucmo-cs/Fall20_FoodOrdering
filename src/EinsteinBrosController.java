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
import java.util.List;

public class EinsteinBrosController extends RestaurantBaseController{

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
                case "bagel" -> tableViewBagel.getItems().add(f);
                case "shmear" -> tableViewShmear.getItems().add(f);
                case "drink" -> tableViewDrinks.getItems().add(f);
            }
        }
    }
}
