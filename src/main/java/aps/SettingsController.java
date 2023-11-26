package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import helper.Session;
import db.BinHandler;
import forms.Admin;
import forms.Person;
import forms.SurveyCreator;
import forms.User;
import helper.Hasher;
import helper.Misc.prefix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;

public class SettingsController implements Initializable {

  @FXML
  private PasswordField oldPass;

  @FXML
  private PasswordField newPass;

  @FXML
  private PasswordField confirmPass;

  @FXML
  private ComboBox<String> selector;

  BinHandler handler = new BinHandler();
  Map<String, Person> usermap = BinHandler.loadPerson();
  Person loggedIn = Session.getInstance().getPerson();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ArrayList<Person> list = handler.valuesToList(usermap);
    ObservableList<String> nids = FXCollections.observableArrayList();
    for (Person p : list) {
      nids.add(p.getNid());
    }

    if (Session.getInstance().isAdmin()) {
      selector.setItems(nids);

    } else {
      selector.setItems(FXCollections.observableArrayList(loggedIn.getNid()));
    }
    ;
  }

  @FXML
  private void changeAction() throws IOException {
    if (infoCheck()) {
      if (loggedIn.getIsAdmin()) {
        App.setRoot("adminDash");
      } else if (loggedIn.getIsSurveyCreator()) {
        App.setRoot("scDash");
      } else {
        App.setRoot("dash");
      }
    } else {
      System.out.println("Error");
    }

  }

  @FXML
  private void goBack() throws IOException {
    boolean sc = Session.getInstance().isSurveyCreator();
    boolean a = Session.getInstance().getPerson().getIsAdmin();
    if (sc) {
      App.setRoot("scDash");
    } else if (a) {
      App.setRoot("adminDash");
    } else {
      App.setRoot("dash");
    }
  }

  @FXML
  private void logoutAction() throws IOException {
    Session.getInstance().initSession();
    App.setRoot("landing");
  }

  private boolean infoCheck() {
    String nid = selector.getValue().toString();

    if (nid.isBlank()) {
      return false;
    }
    Person selected = handler.searchPerson(prefix.NID, nid);
    if (selected == null) {
      return false;
    }

    String oldPassText = oldPass.getText();
    String newPassText = newPass.getText();
    String confirmText = confirmPass.getText();

    boolean oldPassValid = !oldPassText.isBlank() && Hasher.verifyHash(oldPassText, selected.getPassword());
    boolean newPassValid = !newPassText.isBlank() && newPassText.length() > 8;
    boolean confirmValid = !confirmText.isBlank() && confirmText.equals(newPassText);

    boolean err = oldPassValid && newPassValid && confirmValid;

    if (err) {
      String hash = Hasher.genSalted(newPassText);

      if (selected.getIsAdmin()) {
        Admin a = handler.searchAdmins(prefix.NID, selected.getNid());
        Admin ab = handler.updateAdmin(a, a.getPassword(), hash, prefix.PASSWORD);
        if (ab != null) {
          return true;
        }
      } else if (selected.getIsSurveyCreator()) {
        System.out.println("sc here to change pass");
        SurveyCreator sc = handler.searchSurveyCreator(prefix.NID, selected.getNid());
        SurveyCreator updated = handler.updateSurveyCreator(sc, sc.getPassword(), hash, prefix.PASSWORD);
        if (updated != null) {
          System.out.println("success update pass for sc");
          return true;
        }
      } else {
        User u = handler.searchUsers(prefix.NID, selected.getNid());
        User updated = handler.updateUser(u, u.getPassword(), hash, prefix.PASSWORD);
        if (updated != null) {
          return true;
        }
      }
    }
    return true;
  }
}
