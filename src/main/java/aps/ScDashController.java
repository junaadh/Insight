package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Person;
import forms.Survey;
import forms.SurveyCreator;
import helper.Javax;
import helper.Session;
import helper.Misc.prefix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScDashController implements Initializable {
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

  @FXML
  private void createSurvey() throws IOException {
    App.setRoot("surveyCreation");
  }

  private void loadResources() {
    BinHandler handler = new BinHandler();

    Person p = Session.getInstance().getPerson();
    SurveyCreator a = p instanceof SurveyCreator ? (SurveyCreator) p : null;
    if (a == null) {
      a = handler.searchSurveyCreator(prefix.NID, p.getNid());
    }
    roleField.setText(Session.getInstance().role());
    nameField.setText(p.getFullname());
    idField.setText(a.getScId());

    searchList = new TableView<Survey>();
    surveymap = BinHandler.loadSurvey();
    ArrayList<Survey> map = handler.valuesToList(surveymap);

    // Javax.initializeTableColumn(map.get(0).getHeaders(), searchList);
    // Javax.initializeTableRow(map, searchList);

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
      System.out.println("Selected: " + selectedItem.getsurveyId());
    }
  }

}
