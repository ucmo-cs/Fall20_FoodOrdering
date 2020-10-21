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

import javax.sql.rowset.CachedRowSet;

public class LoginController {

    // Classes
    SQLCommands sqlCommands=new SQLCommands();
<<<<<<< Updated upstream
    MenuController menuController=new MenuController();
=======
    MainStageController mainStageController = MainStageController.getInstance();
>>>>>>> Stashed changes

    // Variables
    public Stage loginStage = new Stage();
    public User user=new User();

    @FXML public TextField textFieldUsername;
    @FXML public TextField textFieldPassword;
    @FXML public Button buttonLogin;
    @FXML public Label labelMessage;
    @FXML public RadioButton radButtonStudent;
    @FXML public RadioButton radButtonEmployee;

<<<<<<< Updated upstream
    public void openLogin() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Files/loginScreen.fxml"));
        loginStage.setTitle("Mule Trough Login");
        loginStage.setScene(new Scene(root, 797, 700));
        loginStage.show();
    }

=======
>>>>>>> Stashed changes
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
        if(textFieldUsername.getText().isEmpty()){ textFieldUsername.setText("Username Required"); }
        else if (textFieldPassword.getText().isEmpty()) { textFieldPassword.setText("Password Required"); }
        else {

            String username = textFieldUsername.getText();
            String claimedPassword = textFieldPassword.getText();
            String id = null, fname = null, lname = null, truePassword = null;
            Boolean employee = false;

            // Acquire user data
            String query = "select * from student.student_information where id = '" + username + "';";

            CachedRowSet accountData = sqlCommands.readDataBase(1, query);

            // Determine if user info was in CachedRow
            if (!accountData.isBeforeFirst()) {
                labelMessage.setTextFill(Color.RED);
                labelMessage.setText("Invalid Username");
            } else {
                // Acquire the account's password and than compare to the given password
                while (accountData.next()) {
                    id = accountData.getString("id");
                    fname = accountData.getString("first_name");
                    lname = accountData.getString("last_name");
                    truePassword = accountData.getString("password");
                }
                if (claimedPassword.equals(truePassword)) {
                    setUser(id, fname, lname, employee);
                    menuController.openMainScreen();
                    closeLogin();
                } else {
                    labelMessage.setTextFill(Color.RED);
                    labelMessage.setText("Invalid Password");
                }
            }
        }// Ends else
    }// Ends attemptLogin

    public void attemptloginTest() throws Exception {
        menuController.openMainScreen();
        closeLogin();
    }

}

