package Controllers;

import java.io.IOException;

public class MyCartController {
    MainStageController mainStageController=MainStageController.getInstance();

    public void openCheckoutPane() throws IOException {
        mainStageController.openCheckoutPane();
    }
    //1. Take items from cart and populate table with the results.
    //2. Display subtotal, quantity and ensure availability of items in cart
    //3. If user clicks checkout button, switch pane to checkout and process payment information
    //4.
}
