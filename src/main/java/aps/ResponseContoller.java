package aps;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Survey;
import forms.SurveyResponse;
import forms.Person;
import forms.Response;
import helper.Javax;
import helper.Session;
import helper.Misc.prefix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import helper.Misc;

public class ResponseContoller implements Initializable {
  @FXML
  private TableView<?> survey;

  @Override
  public void initialize(URL location, ResourceBundle resource) {
    BinHandler handler = new BinHandler();
    Misc.inResponse = true;
    
    

    Map<String, Survey> map = BinHandler.loadSurvey();
    ArrayList<Survey> list = handler.valuesToList(map);
    Map<String, Response> userResp = BinHandler.loadResponse();
    ArrayList<SurveyResponse> rList = new ArrayList<>();
    Survey s = new Survey();
    Person p = Session.getInstance().getPerson();

    if (Session.getInstance().getViewMode()) {
      for (Map.Entry<String, Response> entry : userResp.entrySet()) {
        String surveyId = entry.getValue().getSurveyId();
        String nid = entry.getValue().getNid();
        Person person = handler.searchPerson(prefix.NID, nid);
        SurveyResponse sr = new SurveyResponse(person.getUsername(), person.getNid(), surveyId);
        rList.add(sr);
      }

      HashSet<SurveyResponse> set = new HashSet<>(rList);
      ArrayList<SurveyResponse> sorted = new ArrayList<>(set);
      SurveyResponse sr = new SurveyResponse();

      Javax.initializeTableColumn(sr.getHeaders(), survey);
      Javax.initializeTableRow(sorted, survey);

    } else {
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
    }
    survey.setOnMouseClicked(event -> switchToSurvey(survey.getSelectionModel().getSelectedItem()));

  }

  private <T> void switchToSurvey(T newValue) {
    try {
      if (newValue != null) {
        // T selection = className.cast(newValue);
        // String surveyId = selection.getSurveyId();
        Method getSurveyId = newValue.getClass().getMethod("getSurveyId");
        String surveyId = (String) getSurveyId.invoke(newValue);
        Method getNid = newValue.getClass().getMethod("getNid");
        String nid = (String) getNid.invoke(newValue);

        Session.getInstance().setSurveyId(surveyId);
        Session.getInstance().setViewUuid(nid);
        App.setRoot("survey");
      }
    } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      System.out.println("ERROR: Failed to change scene: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @FXML
  private void goBack() throws IOException {
    Misc.inResponse = false;
    boolean sc = Session.getInstance().isSurveyCreator();
    boolean a = Session.getInstance().isAdmin();
    try {
      if (sc) {
        App.setRoot("scDash");
      } else if (a) {
        App.setRoot("adminDash");
      } else {
        App.setRoot("dash");
      }
    } catch (Exception e) {
     e.printStackTrace();
    }
  }
}
