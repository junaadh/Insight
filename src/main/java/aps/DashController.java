package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Person;
import forms.Survey;
import forms.User;
import helper.Javax;
import helper.Session;
import helper.Misc.prefix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
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
  private TableView<Survey> searchList;

  @FXML
  private VBox mainView;

  @FXML
  private VBox defaultView;

  private Map<String, Survey> surveymap;

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
    User a = p instanceof User ? (User) p : null;
    if (a == null) {
      System.out.println(handler.searchAdmins(prefix.NID, p.getNid()));
      a = handler.searchUsers(prefix.NID, p.getNid());

    }
    roleField.setText(Session.getInstance().role());
    nameField.setText(p.getFullname());
    idField.setText(a.getUserId());

    searchList = new TableView<Survey>();
    surveymap = BinHandler.loadSurvey();
    ArrayList<Survey> map = handler.valuesToList(surveymap);
    Survey s = new Survey();

    Javax.initializeTableColumn(s.getHeaders(), searchList);
    Javax.initializeTableRow(map, searchList);

    searchList.managedProperty().bind(searchList.visibleProperty());
    searchList.visibleProperty().bind(searchField.focusedProperty().or(searchList.focusedProperty()));

    defaultView.managedProperty().bind(defaultView.visibleProperty());
    defaultView.visibleProperty().bind(searchList.visibleProperty().not());
    mainView.getChildren().add(searchList);

    searchField.setOnMouseClicked(event -> handleClick());
    searchField.textProperty().addListener(
        (observable, oldvalue, newvalue) -> Javax.initializeTableRow(handler.filter(surveymap, newvalue), searchList));
  }

  private void handleClick() {
    Survey selectedItem = searchList.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      System.out.println("Selected: " + selectedItem.getSurveyId());
    }
  }

  @FXML
  private void changeSetting() throws IOException {
    App.setRoot("settings");
  }

  @FXML
  private void switchToSurveys() throws IOException {
    App.setRoot("list");
  }

  @FXML
  private void switchToResponse() throws IOException {
    App.setRoot("response");
  }

}
