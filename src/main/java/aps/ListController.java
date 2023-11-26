package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Survey;
import helper.Javax;
import helper.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class ListController implements Initializable {

  @FXML
  private TableView<Survey> survey;

  BinHandler handler = new BinHandler();
  Map<String, Survey> map = BinHandler.loadSurvey();
  ArrayList<Survey> list = handler.valuesToList(map);

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Survey s = new Survey();
    Javax.initializeTableColumn(s.getHeaders(), survey);
    Javax.initializeTableRow(list, survey);

    survey.setOnMouseClicked(event -> switchToSurvey(survey.getSelectionModel().getSelectedItem()));
  }

  private void switchToSurvey(Survey newValue) {
    try {
      if (newValue != null) {
        Survey selection = newValue;
        String surveyId = selection.getSurveyId();
        Session.getInstance().setSurveyId(surveyId);
        App.setRoot("survey");
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to change scene: " + e.getMessage());
      e.printStackTrace();
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
}
