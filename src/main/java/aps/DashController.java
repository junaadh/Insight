package aps;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Admin;
import forms.Person;
import forms.SurveyCreator;
import forms.User;
import helper.Session;
import helper.Misc.prefix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DashController implements Initializable {
  @FXML
  private Text nameField;

  @FXML
  private Text roleField;

  @FXML
  private Text idField;

  @FXML
  private TextField searchField;

  @FXML
  private ListView<String> searchList;

  @FXML
  private VBox mainView;

  @FXML
  private VBox defaultView;

  private Map<String, User> usermap;

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

    ObservableList userKeys = FXCollections.observableArrayList(usermap.keySet());
    FilteredList<String> filtered = new FilteredList<String>(userKeys);

    searchList.setItems(filtered);
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      updateSearchResult(newValue, filtered);
    });

    searchList.managedProperty().bind(searchList.visibleProperty());
    searchList.visibleProperty().bind(searchField.focusedProperty());
    mainView.getChildren().add(searchList);

    searchList = new ListView<String>();
    usermap = BinHandler.loadUser();

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

  private void updateSearchResult(String query, FilteredList<String> filtered) {
    filtered.setPredicate(userKey -> {
      return userKey.toLowerCase().contains(query.toLowerCase());
    });
  }

}
