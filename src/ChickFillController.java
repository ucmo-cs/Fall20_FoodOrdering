import Models.FoodMenuItem;
import Models.MenuModel;
import Queries.RestaurantQueries;
import Queries.StudentQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import javax.sql.rowset.CachedRowSet;

public class ChickFillController extends RestaurantBaseController{

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

    private final static int RESTAURANT_ID = 1;
    private String userID;


    public void initialize() throws Exception {

        this.userID = "700655850"; // test
        SQLCommands sqlCommands = new SQLCommands();

        buildMenu(RESTAURANT_ID);

        this.menuModel.showFoods();

        /*
            ALL CODE BELOW HERE IS HOW THE ORDER PROCESS WORKS

            The only implementation difference will be getting real food IDs from user selection.
            The items of the "order" array are int types with the Food_ID of what they are

            In this test order, the user ordered two spicy sandwiches (1), one fry (6), and a milkshake (2)
         */

        int[] testOrder = {1,1,6,2};
        double total = calculateOrderTotal(testOrder); // superclass method

        // string for submitting the order to database
        String submitOrderQuery = RestaurantQueries.submitNewOrderQuery(
                this.userID,
                formatOrderForDB(testOrder),
                String.valueOf(total),
                String.valueOf(RESTAURANT_ID));
        System.out.println(submitOrderQuery);

        // do command
        sqlCommands.readDataBase(1, submitOrderQuery);

        String debitAccountQuery = StudentQueries.debitDiningDollarsQuery(this.userID, String.valueOf(total));
        System.out.println(debitAccountQuery);
        sqlCommands.readDataBase(2, debitAccountQuery);

    }
}
