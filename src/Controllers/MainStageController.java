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
    private static MainStageController instance = null;
    private Stage mainStage=new Stage();
    private User user;
    @FXML Pane menuPane;
    @FXML Button buttonHome;
    @FXML Button buttonCart;
    @FXML Button buttonLogout;

    public static MainStageController getInstance() {
        return instance;
    }

    public void initialize() throws IOException {
        instance = this;
        openLoginPane();
    }
    public void setUser(User u) {
        this.user = u;
        System.out.printf("Logged in as %s %s %s\n", this.user.getFname(), this.user.getLname(), this.user.getID());
    }

    public void openMainStage()throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/MainStage.fxml"));
        Scene scene=new Scene(root,1000,700);
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/test.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/FXML_Files/login.css").toExternalForm());
        mainStage.setTitle("UCMO Food Ordering (Mule Trough)");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void closeMainStage(){
        menuPane.getChildren().clear();
        mainStage.close();
    }

    public void openCartPane() throws IOException {
        Pane paneCart=FXMLLoader.load(getClass().getResource("/FXML_Files/MyCartScreen.fxml"));
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneCart);
    }

    public void openEmployeeView() throws IOException {
        Pane paneEmployee=FXMLLoader.load(getClass().getResource("/FXML_Files/EmployeeViewPoint.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneEmployee);
        buttonLogout.setVisible(true);
    }

    public void openCheckoutPane() throws IOException {
        Pane paneCheckout=FXMLLoader.load(getClass().getResource("/FXML_Files/CheckoutScreen.fxml"));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneCheckout);
    }

    public void openTacoBellMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/TacoBellScreen.fxml"));
        Pane paneTest = loader.load();
        TacoBellController tbell = loader.getController();
        tbell.setUser(this.user);
//        tbell.showUser();
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openEinsteinBrosMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/EinsteinBrosScreen.fxml"));
        Pane paneTest = loader.load();
        EinsteinBrosController einstein = loader.getController();
        einstein.setUser(this.user);
//        einstein.showUser();
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openStarbucksMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/StarBucksPane.fxml"));
        Pane paneTest = loader.load();
        StarBucksController bux = loader.getController();
        bux.setUser(this.user);
//        bux.showUser();
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openChickFilAMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/ChickFilScreen.fxml"));
        Pane paneTest = loader.load();
        ChickFillController chick = loader.getController();
        chick.setUser(this.user);
//        chick.showUser();
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openSpinPizzaMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/SpinPizzaScreen.fxml"));
        Pane paneTest = loader.load();
        SpinPizzaController spin = loader.getController();
        spin.setUser(this.user);
//        spin.showUser();
        buttonHome.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openFrontScreen() throws IOException {
        Pane paneTest=FXMLLoader.load(getClass().getResource("/FXML_Files/FrontScreen.fxml"));
        buttonHome.setVisible(false);
        buttonCart.setVisible(true);
        buttonLogout.setVisible(true);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneTest);
    }

    public void openLoginPane() throws IOException {
        Pane paneLogin = FXMLLoader.load(getClass().getResource("/FXML_Files/LoginScreen.fxml"));
        buttonHome.setVisible(false);
        buttonCart.setVisible(false);
        buttonLogout.setVisible(false);
        menuPane.getChildren().clear();
        menuPane.getChildren().add(paneLogin);
    }
}
