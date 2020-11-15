package Controllers;

import java.io.IOException;

public class MyCartController {
    MainStageController mainStageController=MainStageController.getInstance();

    public void openCheckoutPane() throws IOException {
        mainStageController.openCheckoutPane();
    }
}
