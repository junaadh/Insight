package aps;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingController implements Initializable {
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    helper.Session.getInstance().initSession();
  }

  @FXML
  private void switchToLogin() throws IOException {
    App.setRoot("login");
  }
}
