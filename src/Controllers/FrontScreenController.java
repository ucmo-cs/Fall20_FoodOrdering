package Controllers;

import java.io.IOException;

public class FrontScreenController {
    private MainStageController mainStageController=MainStageController.getInstance();

    public void openTacoBell() throws IOException {
        mainStageController.openTacoBellMenu();
    }
    public void openEinsteinBros() throws IOException {
        mainStageController.openEinsteinBrosMenu();
    }
    public void openChickFillet() throws IOException {
        mainStageController.openChickFilletMenu();
    }
    public void openStarbucks() throws IOException {
        mainStageController.openStarbucksMenu();
    }
    public void openSpinPizza() throws IOException {
        mainStageController.openSpinPizzaMenu();
    }
}
