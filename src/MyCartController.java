
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MyCartController{

        public void OpenCheckout() throws IOException {
                Parent checkout = FXMLLoader.load(getClass().getResource("FXML_Files/CheckoutScreen.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(checkout,1000,700);
                scene.getStylesheets().add(getClass().getResource("FXML_Files/test.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("FXML_Files/login.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
    }
}
