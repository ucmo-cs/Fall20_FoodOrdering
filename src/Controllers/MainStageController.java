package Controllers;

import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.scene.*;
import java.io.IOException;

public class MainStageController {
    static MainStageController instance = null;
    public Stage mainStage=new Stage();
    OrderStageController orderStageController=new OrderStageController();
    Parent root;
    User user;
    @FXML Pane paneMenu;
    @FXML Button buttonHome;
    @FXML Button buttonCart;
    @FXML Button buttonLogout;
    @FXML Button buttonMap;

    public static MainStageController getInstance() {
        return instance;
    }

    public void initialize() throws IOException {
        instance = this;
        openLoginPane();
    }
    public void setUser(User u)
    {
        this.user = u;
    }
    public void openMainStage()throws Exception {
        root = FXMLLoader.load(getClass().getResource("/FXML_Files/MainStage.fxml"));
        Scene scene=new Scene(root,1000,700);
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/login.css").toExternalForm());
        mainStage.setTitle("UCMO Food Ordering (Mule Trough)");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void closeMainStage(){
        paneMenu.getChildren().clear();
        mainStage.close();
    }

    public void openCartPane() throws Exception {
        Pane paneTest = FXMLLoader.load(getClass().getResource("/FXML_Files/MyCartScreen.fxml"));
        buttonHome.setVisible(true);
        buttonCart.setVisible(true);
        buttonMap.setVisible(true);
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneTest);
    }

    public  void openMapView() throws IOException{
        Pane paneMap=FXMLLoader.load(getClass().getResource("/FXML_Files/Map.fxml"));
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneMap);
    }

    public void openEmployeeView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/EmployeeViewPoint.fxml"));
        Pane paneEmployee = loader.load();
        EmployeeViewPointController controller = loader.getController();
        controller.setUser(this.user);
        controller.showUser();
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneEmployee);
        buttonLogout.setVisible(true);
    }

    public void openTacoBellMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/TacoBellScreen.fxml"));
        Pane paneTest=loader.load();
        TacoBellController controller = loader.getController();
        controller.setUser(this.user);
        controller.showUser();
        buttonHome.setVisible(true);
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneTest);
    }

    public void openEinsteinBrosMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/EinsteinBrosScreen.fxml"));
        Pane paneTest=loader.load();
        EinsteinBrosController controller = loader.getController();
        controller.setUser(this.user);
        controller.showUser();
        buttonHome.setVisible(true);
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneTest);
    }

    public void openStarbucksMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/StarBucksPane.fxml"));
        Pane paneTest=loader.load();
        StarBucksController controller = loader.getController();
        controller.setUser(this.user);
        controller.showUser();
        buttonHome.setVisible(true);
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneTest);
    }

    public void openChickFilletMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/ChickFilScreen.fxml"));
        Pane paneTest=loader.load();
        ChickFillController controller = loader.getController();
        controller.setUser(this.user);
        controller.showUser();
        buttonHome.setVisible(true);
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneTest);
    }

    public void openSpinPizzaMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/SpinPizzaScreen.fxml"));
        Pane paneTest=loader.load();
        SpinPizzaController controller = loader.getController();
        controller.setUser(this.user);
        controller.showUser();
        buttonHome.setVisible(true);
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneTest);
    }

    public void openFrontScreen() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("/FXML_Files/FrontScreen.fxml"));
        buttonHome.setVisible(true);
        buttonCart.setVisible(true);
        buttonMap.setVisible(true);
        buttonLogout.setVisible(true);
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneTest);
    }

    public void openLoginPane() throws IOException {
        Pane paneLogin = FXMLLoader.load(getClass().getResource("/FXML_Files/LoginScreen.fxml"));
        buttonHome.setVisible(false);
        buttonCart.setVisible(false);
        buttonMap.setVisible((false));
        buttonLogout.setVisible(false);
        paneMenu.getChildren().clear();
        paneMenu.getChildren().add(paneLogin);
    }
}
