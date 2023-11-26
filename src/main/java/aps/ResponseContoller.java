package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Survey;
import forms.Person;
import forms.Response;
import helper.Javax;
import helper.Session;
import helper.Misc.prefix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class ResponseContoller implements Initializable {
  @FXML
  private TableView<Survey> survey;

  @Override
  public void initialize(URL location, ResourceBundle resource) {
    BinHandler handler = new BinHandler();

    Map<String, Survey> map = BinHandler.loadSurvey();
    ArrayList<Survey> list = handler.valuesToList(map);
    Map<String, Response> userResp = BinHandler.loadResponse();
    Survey s = new Survey();
    Person p = Session.getInstance().getPerson();

    ArrayList<String> responded = new ArrayList<>();
    for (Map.Entry<String, Response> resp : userResp.entrySet()) {
      if (resp.getKey()
          .contains(prefix.NID.getPrefix() + p.getNid() + prefix.SURVEYID.getPrefix())) {
        responded.add(resp.getValue().getSurveyId());
      }
    }

    ArrayList<Survey> filtered = new ArrayList<>();
    for (Survey sy : list) {
      if (responded.contains(sy.getSurveyId())) {
        filtered.add(sy);
      }
    }

    Javax.initializeTableColumn(s.getHeaders(), survey);
    Javax.initializeTableRow(filtered, survey);

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
    App.setRoot("dash");
  }
}
