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
    MainStageController mainStageController = MainStageController.getInstance();

    // Variables
    public Stage loginStage = new Stage();
    public User user=new User();

    @FXML public TextField textFieldUsername;
    @FXML public TextField textFieldPassword;
    @FXML public Button buttonLogin;
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
        // Check to ensure that textfields have data.
        if (validateLoginInfo()==false){ System.out.print("");}
        else {
            // Create Variables
            String username = textFieldUsername.getText();
            String claimedPassword = textFieldPassword.getText();
            String id = null, fname = null, lname = null, truePassword = null;
            Boolean employee = false;

            // Acquire user data
            String query = "select * from student.student_information where id = '" + username + "';";
            CachedRowSet accountData = sqlCommands.readDataBase(1, query);

            // Determine if user info was in CachedRow
            if (!accountData.isBeforeFirst()) {
//                labelMessage.setTextFill(Color.RED);
                displayMessage("Login Info Not found");
            } else {
                // Acquire the account's password and than compare to the given password
                while (accountData.next()) {
                    id = accountData.getString("id");
                    fname = accountData.getString("first_name");
                    lname = accountData.getString("last_name");
                    truePassword = accountData.getString("password");
                }
                // If the passwords match and the user information is valid
                if (claimedPassword.equals(truePassword)) {
                    setUser(id, fname, lname, employee);
                    labelMessage.setVisible(false);
                    mainStageController.openTestPane();
                    closeLogin();
                } else {
                    displayMessage("Login Info Not found");
                }
            }
        }// Ends else
    }// Ends attemptLogin

    public void attemptloginTest() throws Exception {
        mainStageController.openTestPane();
        closeLogin();
    }

}

