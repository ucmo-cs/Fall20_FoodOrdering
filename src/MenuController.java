import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import javax.sql.rowset.CachedRowSet;

public class MenuController {

    SQLCommands sqlCommands = new SQLCommands();

    @FXML Label labelEinsteinBros;
    @FXML TabPane tabpaneEinsteinMenu;
    @FXML Label labelTacoBell;
    @FXML TabPane tabpaneTacoBellMenu;
    @FXML Button buttonTacoBell;
    @FXML Button buttonEinsteinBros;
    @FXML Label labelUserID;
    @FXML Label labelUserFname;

    public void setUserData(){
        // Placeholder code. Will replace in the future.
        labelUserFname.setText("Tester");
        labelUserID.setText("700123456");
    }

    public void initialize() throws Exception {
        setUserData();
    }

    public void openMainScreen() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Files/menuScreen.fxml"));
        Stage mainStage = new Stage();
        mainStage.setTitle("Mule Trough");
        mainStage.setScene(new Scene(root, 900, 600));

        mainStage.show();
    }

    public CachedRowSet readFoodsTable() throws Exception{
        String query = "select * from restaurant.foods;";
        try {
            CachedRowSet cachedRowSet = sqlCommands.readDataBase(1,query);
            return cachedRowSet;
        } catch (Exception e) {
            System.err.print("ERROR!\nFunction: readFoodsTable\nDescription: Issue reading FoodsTable");
            throw e;
        }
    }

    public void fillFoodsTable() throws Exception {
        // Wroking on this particular code
        CachedRowSet foodData=readFoodsTable();
        while (foodData.next()) {

        }
    }

    public void switchTacoBell(){
        labelEinsteinBros.setVisible(false);
        tabpaneEinsteinMenu.setVisible(false);

        labelTacoBell.setVisible(true);
        tabpaneTacoBellMenu.setVisible(true);
    }

    public void switchEinsteinBros(){
        labelEinsteinBros.setVisible(true);
        tabpaneEinsteinMenu.setVisible(true);

        labelTacoBell.setVisible(false);
        tabpaneTacoBellMenu.setVisible(false);
    }
}
