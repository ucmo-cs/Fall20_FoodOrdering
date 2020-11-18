import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Queries.LoginQueries;
import Controllers.DatabaseController;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;

public class LoginController {

    // Classes
    SQLCommands sqlCommands=new SQLCommands();
    MainStageController mainStageController = MainStageController.getInstance();

    // Variables
    public Stage loginStage = new Stage();
    public User user=new User();

    @FXML public TextField textFieldUsername;
    @FXML public TextField textFieldPassword;
    @FXML public Button buttonLogin;
    @FXML public Label labelMessage;
    @FXML public RadioButton radButtonStudent;
    @FXML public RadioButton radButtonEmployee;

    public void openLogin() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Files/loginScreen.fxml"));
        loginStage.setTitle("Mule Trough Login");
        loginStage.setScene(new Scene(root, 300, 220));
        loginStage.show();
    }

    public void closeLogin(){
        Stage stage=(loginStage);
        stage.close();
    }

    public void setUser(String id, String fname,String lname,Boolean employee){
        user.setID(id);
        user.setFname(fname);
        user.setLname(lname);
        user.setEmployee(employee);
    }

    public void attemptLogin() throws Exception {
        // Create Variables

        // Check to ensure that textfields have data.
        if (textFieldUsername.getText().isEmpty()) { textFieldUsername.setText("Username Required"); return;}
        if (textFieldPassword.getText().isEmpty()) { textFieldPassword.setText("Password Required"); return;}

        // Create Variables
        String username = textFieldUsername.getText();
        String claimedPassword = textFieldPassword.getText();
        String id = null, fname = null, lname = null, truePassword = null;
        boolean restaurant_user = false;
        boolean credentials_good = false;

        // Check if given username exists in student table
        if (!StudentUserExists(username)) {

            // If username does not exist in student table, check if it exists in the restaurant users table
            if(RestaurantUserExists(username))
                restaurant_user = true;
            else
            {
                System.err.println("User does not exist");
                labelMessage.setTextFill(Color.RED);
                labelMessage.setText("Invalid Username");
            }
        }

        if (!verifyPassword(username, claimedPassword, restaurant_user)) {
            System.err.println("Invalid Password");
            labelMessage.setTextFill(Color.RED);
            labelMessage.setText("Invalid Password");
        }
        else
            credentials_good = true;

        System.out.printf("Restaurant User: %s\nSucessfull Login: %s\n", String.valueOf(restaurant_user),String.valueOf(credentials_good));
        if(credentials_good)
        {
            // Acquire user data
            String query = LoginQueries.getUserInfoQuery(username);
            CachedRowSet accountData = sqlCommands.readDataBase(2, query);

            accountData.next();
            id = accountData.getString("id");
            fname = accountData.getString("first_name");
            lname = accountData.getString("last_name");
            System.out.println(String.format("User: %s, %s: %s", lname, fname, id));
            setUser(id, fname, lname, restaurant_user);
            mainStageController.setUser(user);
            mainStageController.openTestPane();
            closeLogin();
        }
    }

    private boolean verifyPassword(String username, String claimedPassword, boolean restaurant) throws Exception {
        final String query;
        if(!restaurant)
            query = LoginQueries.getStudentPasswordQuery(username);
        else
            query = LoginQueries.getRestaurantPasswordQuery(username);

        ResultSet result = DatabaseController.readStudentLoginDatabase(query);
        result.next();
        return result.getString(1).equals(claimedPassword);
    }

    private boolean StudentUserExists(String username) throws Exception {
        String query = LoginQueries.StudentUserExistsQuery(username);
        ResultSet result = DatabaseController.readStudentLoginDatabase(query);
        result.next();
        return result.getString(1).equals("1");
    }
    private boolean RestaurantUserExists(String username) throws Exception {
        String query = LoginQueries.RestaurantUserExistsQuery(username);
        ResultSet result = DatabaseController.readStudentLoginDatabase(query);
        result.next();
        return result.getString(1).equals("1");
    }

    //Possibly unneeded?
    public void attemptloginTest() throws Exception {
        //mainStageController.openTestPane();
        closeLogin();
    }
}

