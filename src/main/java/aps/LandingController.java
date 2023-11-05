package aps;

import javafx.fxml.FXML;
import java.io.IOException;

public class LandingController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
