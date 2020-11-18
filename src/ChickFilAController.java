import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import java.util.concurrent.TimeUnit;

public class ChickFilAController extends RestaurantBaseController{

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


    public void initialize() throws Exception {
        buildMenu(RESTAURANT_ID);
        this.menuModel.showFoods();

        /*
            ALL CODE BELOW HERE IS HOW THE ORDER PROCESS WORKS

            The only implementation difference will be getting real food IDs from user selection.
            The items of the "order" array are int types with the Food_ID of what they are

            In this test order, the user ordered two spicy sandwiches (1), one fry (6), and a milkshake (2)
         */
        /*
        int[] testOrder = {1,1,6,2};
        double total = calculateOrderSubtotal(testOrder); // superclass method

        showNewOrders(RESTAURANT_ID);

        // restaurant employees make the order
        TimeUnit.SECONDS.sleep(3);

        // make the order ready
        int orderReadyButton = 22; // this value will be gotten from the restaurant user screen "ready" button.
                                    // when clicked, the button will populate this variable with the order ID assoc. with that button
        makeOrderReady(orderReadyButton);

        //show the ready orders
        showReadyOrders(RESTAURANT_ID);

        // user comes to pick up the food
        TimeUnit.SECONDS.sleep(3);

        // mark as picked up
        int orderPickedUpButton = 22; // same idea as above, a button will assign the value here
        makeOrderComplete(orderPickedUpButton);

        // prove the order is complete
        showReadyOrders(RESTAURANT_ID);
        */
    }
}
