package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController {
   @FXML
   WebView map;
   WebEngine engine;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = map.getEngine();
//        engine.load(String.valueOf(getClass().getResource("FXML_Files/index.html")));
        engine.load("https://cdn-map1.nucloud.com/nucloudmap/index.html?map=258&layer=Academic%20Buildings");
    }
}
