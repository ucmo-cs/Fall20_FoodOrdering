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
        Boolean employee = false;


        if (!userExists(username)) {
            System.err.println("User does not exist");
            labelMessage.setTextFill(Color.RED);
            labelMessage.setText("Invalid Username");
        }

        if (!verifyPassword(username, claimedPassword)) {
            System.err.println("Invalid Password");
            labelMessage.setTextFill(Color.RED);
            labelMessage.setText("Invalid Password");
        }

        // Credentials OK
        // Acquire user data
        String query = LoginQueries.getUserInfoQuery(username);
        CachedRowSet accountData = sqlCommands.readDataBase(2, query);

        accountData.next();
        id = accountData.getString("id");
        fname = accountData.getString("first_name");
        lname = accountData.getString("last_name");
        System.out.println(String.format("User: %s, %s: %s", lname, fname, id));
        setUser(id, fname, lname, employee);
        mainStageController.openTestPane();
        closeLogin();
    }

    private boolean verifyPassword(String username, String claimedPassword) throws Exception {
        final String query = LoginQueries.getPasswordQuery(username);
        ResultSet result = DatabaseController.readStudentLoginDatabase(query);
        result.next();
        return result.getString(1).equals(claimedPassword);
    }

    private boolean userExists(String username) throws Exception {
        final String query = LoginQueries.IDExistsQuery(username);
        ResultSet result = DatabaseController.readStudentLoginDatabase(query);
        result.next();
        return result.getString(1).equals("1");
    }

    //Possibly unneeded?
    public void attemptloginTest() throws Exception {
        mainStageController.openTestPane();
        closeLogin();
    }
}

