package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Person;
import helper.Hasher;
import helper.Session;
import helper.Misc.prefix;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginController implements Initializable {

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  @FXML
  private void loginAction() throws IOException {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username.isBlank() || password.isBlank()) {
      Alert loginAlert = new Alert(AlertType.ERROR);
      loginAlert.setHeaderText("Invalid login Details");
      loginAlert.setContentText("Username and password cannot be empty");
      loginAlert.showAndWait();
    }

    BinHandler handler = new BinHandler();
    Person person = null;
    person = handler.searchPerson(prefix.USERNAME, username);

    if (person == null || !Hasher.verifyHash(password, person.getPassword())) {
      Alert loginAlert = new Alert(AlertType.ERROR);
      loginAlert.setHeaderText("User not found");
      loginAlert.setContentText("Please enter valid username and password or signup");
      loginAlert.showAndWait();
    }

    Session.getInstance().setPerson(person);
    if (person.getIsAdmin()) {
      App.setRoot("dash");
    } else if (person.getIsSurveyCreator()) {
      App.setRoot("sCreator");
    } else {
      App.setRoot("primary");
    }

  }

  @FXML
  private void switchtoRegistration() throws IOException {
    App.setRoot("signup");
  }
}
