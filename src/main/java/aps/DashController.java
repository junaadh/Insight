package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Admin;
import forms.Person;
import forms.SurveyCreator;
import forms.User;
import helper.Session;
import helper.Misc.prefix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DashController implements Initializable {
  @FXML
  private Text nameField;

  @FXML
  private Text roleField;

  @FXML
  private Text idField;

  @FXML
  private Button logoButton;

  @Override
  public void initialize(URL location, ResourceBundle resource) {
    loadResources();
  }

  @FXML
  private void logoutAction() throws IOException {
    Session.getInstance().initSession();
    App.setRoot("landing");
  }

  private void loadResources() {
    BinHandler handler = new BinHandler();
    Person p = Session.getInstance().getPerson();
    String role = Session.getInstance().role();

    nameField.setText(p.getFullname());
    roleField.setText(role);

    if (role.equals("User")) {
      User u = handler.searchUsers(prefix.NID, p.getNid());
      idField.setText(u.getUserId());
    } else if (role.equals("Admin")) {
      Admin a = handler.searchAdmins(prefix.NID, p.getNid());
      idField.setText(a.getAdminId());
    } else {
      SurveyCreator sc = handler.searchSurveyCreator(prefix.NID, p.getNid());
      idField.setText(sc.getNid());
    }
  }

}
