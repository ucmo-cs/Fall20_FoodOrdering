package Controllers;

import Queries.LoginQueries;
import Models.User;
import Models.SQLCommands;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

    @FXML public ToggleButton toggleButtonStudent;
    @FXML public ToggleButton toggleButtonEmployee;
    @FXML public Label labelMessage;

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

    public void displayMessage(String message){
        labelMessage.setVisible(true);
        labelMessage.setText(message);
    }

    public boolean validateLoginInfo(){
        int len;
        int maxLen=9;
        String illegalChar="[^0-9]";
        String alphabet=".*[a-z].*";
        // Checks if the data is empty
        if(textFieldUsername.getText().isEmpty()||textFieldPassword.getText().isEmpty()){
            displayMessage("Login Info Required");
            return false;
        }
        // Checks if username is greater or less than 9 characters long
        else if((len=textFieldUsername.getLength())!=maxLen){
            displayMessage("Invalid Username Length");
            return false;
        }
        else if((len=textFieldPassword.getLength())>maxLen){
            displayMessage("Password Too Long");
            return false;
        }
        // checks if username contains letters
        else if(textFieldUsername.getText().matches(alphabet)){
            displayMessage("Username Cannot Have Letters");
            return false;
        }
        // Checks if username or password contains dangerous characters
        else if(textFieldUsername.getText().contains(illegalChar)||textFieldPassword.getText().contains(illegalChar)){
            displayMessage("Cannot Use ' \" # - % & * ");
            return false;
        }
        return true;
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
                displayMessage("Invalid Username");
            }
        }

        if (!verifyPassword(username, claimedPassword, restaurant_user)) {
            System.err.println("Invalid Password");
            displayMessage("Invalid Password");
        }
        else
            credentials_good = true;

        System.out.printf("Restaurant User: %s\nSucessfull Login: %s\n", String.valueOf(restaurant_user),String.valueOf(credentials_good));
        if(credentials_good)
        {
            if(!restaurant_user) {
                // Acquire user data
                String query = LoginQueries.getUserInfoQuery(username);
                CachedRowSet accountData = sqlCommands.readDataBase(2, query);

                accountData.next();
                setUser(accountData.getString("id"),
                        accountData.getString("first_name"),
                        accountData.getString("last_name"),
                        false);
                mainStageController.setUser(this.user);
                mainStageController.openFrontScreen();
            } else {
                // Acquire restaurant user data
                String query = LoginQueries.getRestaurantUserInfoQuery(username);
                CachedRowSet accountData = sqlCommands.readDataBase(1, query);
                accountData.next();
                setUser(accountData.getString("id"), accountData.getString("name"), "Employee",true);
                mainStageController.setUser(this.user);
                mainStageController.openEmployeeView();
            }
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

    public void attemptloginTest() throws Exception {
        mainStageController.openFrontScreen();
        closeLogin();
    }

}

