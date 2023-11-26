package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Demographic;
import forms.Likert;
import forms.Mcq;
import forms.Openended;
import forms.Opinion;
import forms.Polar;
import forms.Question;
import forms.Rank;
import forms.Rating;
import forms.Response;
import forms.Survey;
import helper.Javax;
import helper.Session;
import helper.Misc.prefix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SurveyController implements Initializable {

  @FXML
  private VBox mainView;

  private String surveyId;

  public void setSurveyId(String surveyId) {
    this.surveyId = surveyId;
  }

  BinHandler handler = new BinHandler();
  Javax creator = new Javax();
  String color = "#cbc8c9";
  String btnColor = "#63c5da";
  Map<String, String> responses = new HashMap<>();
  Map<String, Question> qMap = BinHandler.loadQuestion();
  ArrayList<Question> qList = handler.valuesToList(qMap);
  ArrayList<Question> filtered = new ArrayList<>();
  Map<String, Response> loadedResp = BinHandler.loadResponse();
  Map<String, String> userResponses = new HashMap<>();

  @Override
  public void initialize(URL location, ResourceBundle resource) {
    mainView.setSpacing(16);
    mainView.setPadding(new Insets(0, 20, 0, 20));
    this.setSurveyId(Session.getInstance().getSurveyid());
    Survey s = handler.searchSurveys(prefix.SURVEYID, surveyId);
    if (s == null) {
      try {
        goBack();
      } catch (IOException e) {
        System.out.println("ERROR: Failed to navigate");
      }
    }
    for (Question q : qList) {
      if (q.getSurveyId().equals(s.getSurveyId())) {
        filtered.add(q);
      }
    }
    if (Session.getInstance().getViewMode()) {
      Map<String, String> viewMode = new HashMap<>();
      String query = prefix.NID.getPrefix() + Session.getInstance().getViewUuid()
          + prefix.SURVEYID.getPrefix() + Session.getInstance().getSurveyid();
      System.out.println(query);
      for (Map.Entry<String, Response> entry : loadedResp.entrySet()) {
        if (entry.getKey().contains(query)) {
          Response r = entry.getValue();
          System.out.println("DEBUG: viewmode map qid: " + r.getQId());
          viewMode.put(r.getQId().substring(0, r.getQId().length() - 1), r.getReponses());
        }
      }
      for (Question q : filtered) {
        System.out.println("DEBUG: Question map qid: " + q.getQId());
        createSurveyWithResponse(q, viewMode.get(q.getQId()));
      }

    } else {
      String query = prefix.NID.getPrefix() + Session.getInstance().getPerson().getNid() + prefix.SURVEYID.getPrefix()
          + surveyId;
      System.out.println("INFO: query string: " + query);

      for (Map.Entry<String, Response> entry : loadedResp.entrySet()) {
        if (entry.getKey().contains(query)) {
          Response r = entry.getValue();
          userResponses.put(r.getQId().substring(0, r.getQId().length() - 1), r.getReponses());
        }
      }
      if (!userResponses.isEmpty()) {
        for (Question q : filtered) {
          createSurveyWithResponse(q, userResponses.get(q.getQId()));
        }
      } else {
        for (Question q : filtered) {
          System.out.println(q.buildInfo());
          createSurvey(q);
        }
        Button creatButton = new Button("Submit Response");
        creatButton.setPadding(new Insets(15, 30, 15, 30));
        creatButton.setStyle("-fx-background-color: " + btnColor + "; -fx-background-radius: 50");
        mainView.getChildren().add(creatButton);
        creatButton.setOnAction(event -> {
          for (Map.Entry<String, String> entry : responses.entrySet()) {
            String qid = entry.getKey();
            String response = entry.getValue();

            Response resp = new Response(handler.genResponseId(), qid, Session.getInstance().getPerson().getNid(),
                surveyId,
                response);
            handler.addResponse(resp);
            System.out.println("INFO: Added Response: " + resp.getResponseId() + " successfully");
          }
          try {
            goBack();
          } catch (IOException e) {
            System.out.println("ERROR: Failed to change scene: " + e.getMessage());
          }
        });
      }
    }
  }

  @FXML
  private void goBack() throws IOException {
    App.setRoot("response");
  }

  private String questiontype(Question q) {
    String qTypes = "Openended,Polar,Demographic,Opinion,Rank,Likert,Rating,MCQ";
    String[] type = qTypes.split(",");

    for (String s : type) {
      if (q.getQtype().equals(s)) {
        return s;
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  private <T extends Question> T constructQuestion(Question q) {
    String qTypes = "Openended,Polar,Demographic,Opinion,Rank,Likert,Rating,MCQ";
    String[] type = qTypes.split(",");

    if (type[0].toLowerCase().equals(q.getQtype().toLowerCase())) {
      Openended question = handler.searchQuestions(prefix.QID, q.getQId(), Openended.class);
      return (T) question;
    } else if (type[1].toLowerCase().equals(q.getQtype().toLowerCase())) {
      Polar question = handler.searchQuestions(prefix.QID, q.getQId(), Polar.class);
      return (T) question;
    } else if (type[2].toLowerCase().equals(q.getQtype().toLowerCase())) {
      Demographic question = handler.searchQuestions(prefix.QID, q.getQId(), Demographic.class);
      return (T) question;
    } else if (type[3].toLowerCase().equals(q.getQtype().toLowerCase())) {
      Opinion question = handler.searchQuestions(prefix.QID, q.getQId(), Opinion.class);
      return (T) question;
    } else if (type[4].toLowerCase().equals(q.getQtype().toLowerCase())) {
      Rank question = handler.searchQuestions(prefix.QID, q.getQId(), Rank.class);
      return (T) question;
    } else if (type[5].toLowerCase().equals(q.getQtype().toLowerCase())) {
      Likert question = handler.searchQuestions(prefix.QID, q.getQId(), Likert.class);
      return (T) question;
    } else if (type[6].toLowerCase().equals(q.getQtype().toLowerCase())) {
      Rating question = handler.searchQuestions(prefix.QID, q.getQId(), Rating.class);
      return (T) question;
    } else if (type[7].toLowerCase().equals(q.getQtype().toLowerCase())) {
      Mcq question = handler.searchQuestions(prefix.QID, q.getQId(), Mcq.class);
      return (T) question;
    }

    return null;
  }

  private void createSurvey(Question q) {
    String type = questiontype(q);

    if (type.equals("Openended") || type.equals("Demographic") || type.equals("Opinion")) {
      VBox container = new VBox(16);
      container.setPadding(new Insets(16));
      Text question = new Text();
      TextField answer = new TextField();
      container.getChildren().addAll(question, answer);
      container.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 15");
      // - container.setStyle("-fx-background-color: " + color);
      // container.setStyle("-fx-background-radius: 15");
      mainView.getChildren().add(container);
      answer.setPromptText("Enter Answer Here");
      answer.setStyle("-fx-background-color: transparent; -fx-border-color: #000; -fx-border-radius: 15");
      if (q.getQtype().toLowerCase().equals("Openended".toLowerCase())) {
        Openended open = constructQuestion(q);
        question.setText(open.getQText());
      } else if (q.getQtype().toLowerCase().equals("Demographic".toLowerCase())) {
        Demographic demo = constructQuestion(q);
        question.setText(demo.getQText());
      } else if (q.getQtype().toLowerCase().equals("Opinion".toLowerCase())) {
        Opinion op = constructQuestion(q);
        question.setText(op.getQText());
      }

      answer.textProperty().addListener((observable, oldValue, newValue) -> {
        responses.put(q.getQId(), newValue);
      });
    } else if (type.equals("MCQ") || type.equals("Rank")) {
      VBox container = new VBox(16);
      container.setPadding(new Insets(16));
      container.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 15");
      Text question = new Text();
      RadioButton opt1 = new RadioButton();
      RadioButton opt2 = new RadioButton();
      RadioButton opt3 = new RadioButton();
      RadioButton opt4 = new RadioButton();
      container.getChildren().addAll(question, opt1, opt2, opt3, opt4);
      mainView.getChildren().add(container);

      if (q.getQtype().toLowerCase().equals("MCQ".toLowerCase())) {
        Mcq mcq = constructQuestion(q);
        question.setText(q.getQText());
        opt1.setText(mcq.getMcqOptions().get(0));
        opt2.setText(mcq.getMcqOptions().get(1));
        opt3.setText(mcq.getMcqOptions().get(2));
        opt4.setText(mcq.getMcqOptions().get(3));
      } else if (q.getQtype().toLowerCase().equals("Rank".toLowerCase())) {
        Rank rank = constructQuestion(q);
        question.setText(rank.getQText());
        opt1.setText(rank.getOptions().get(0));
        opt2.setText(rank.getOptions().get(1));
        opt3.setText(rank.getOptions().get(2));
        opt4.setText(rank.getOptions().get(3));
      }

      ToggleGroup group = new ToggleGroup();
      opt1.setToggleGroup(group);
      opt2.setToggleGroup(group);
      opt3.setToggleGroup(group);
      opt4.setToggleGroup(group);

      group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
          String selected = ((RadioButton) newValue).getText();
          responses.put(q.getQId(), selected);
        }
      });

    } else if (type.equals("Rating")) {
      // TODO:
    } else if (type.equals("Likert")) {
      // TODO:
    } else if (type.equals("Polar")) {
      VBox container = new VBox(16);
      container.setPadding(new Insets(16));
      container.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 15");
      Text question = new Text();
      RadioButton trueButton = new RadioButton("true");
      RadioButton falseButton = new RadioButton("false");
      container.getChildren().addAll(question, trueButton, falseButton);
      mainView.getChildren().add(container);

      Polar polar = constructQuestion(q);
      question.setText(polar.getQText());
      ToggleGroup group = new ToggleGroup();
      trueButton.setToggleGroup(group);
      falseButton.setToggleGroup(group);

      group.selectedToggleProperty().addListener((observable, oldvalue, newValue) -> {
        if (newValue != null) {
          String selected = ((RadioButton) newValue).getText();
          responses.put(q.getQId(), selected);
        }
      });

    }
  }

  private void createSurveyWithResponse(Question q, String r) {
    String type = questiontype(q);

    if (type.equals("Openended") || type.equals("Demographic") || type.equals("Opinion")) {
      VBox container = new VBox(16);
      container.setPadding(new Insets(16));
      Text question = new Text();
      TextField answer = new TextField();
      container.getChildren().addAll(question, answer);
      container.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 15");
      mainView.getChildren().add(container);
      answer.setStyle("-fx-background-color: transparent; -fx-border-color: #000; -fx-border-radius: 15");
      answer.setText(r);

      if (q.getQtype().toLowerCase().equals("Openended".toLowerCase())) {
        Openended open = constructQuestion(q);
        question.setText(open.getQText());
      } else if (q.getQtype().toLowerCase().equals("Demographic".toLowerCase())) {
        Demographic demo = constructQuestion(q);
        question.setText(demo.getQText());
      } else if (q.getQtype().toLowerCase().equals("Opinion".toLowerCase())) {
        Opinion op = constructQuestion(q);
        question.setText(op.getQText());
      }
    } else if (type.equals("MCQ") || type.equals("Rank")) {
      VBox container = new VBox(16);
      container.setPadding(new Insets(16));
      container.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 15");
      Text question = new Text();
      RadioButton opt1 = new RadioButton();
      RadioButton opt2 = new RadioButton();
      RadioButton opt3 = new RadioButton();
      RadioButton opt4 = new RadioButton();
      container.getChildren().addAll(question, opt1, opt2, opt3, opt4);
      mainView.getChildren().add(container);

      if (q.getQtype().toLowerCase().equals("MCQ".toLowerCase())) {
        Mcq mcq = constructQuestion(q);
        question.setText(q.getQText());
        opt1.setText(mcq.getMcqOptions().get(0));
        opt2.setText(mcq.getMcqOptions().get(1));
        opt3.setText(mcq.getMcqOptions().get(2));
        opt4.setText(mcq.getMcqOptions().get(3));
      } else if (q.getQtype().toLowerCase().equals("Rank".toLowerCase())) {
        Rank rank = constructQuestion(q);
        question.setText(rank.getQText());
        opt1.setText(rank.getOptions().get(0));
        opt2.setText(rank.getOptions().get(1));
        opt3.setText(rank.getOptions().get(2));
        opt4.setText(rank.getOptions().get(3));
      }

      for (RadioButton opt : new RadioButton[] { opt1, opt2, opt3, opt4 }) {
        if (opt.getText().equals(r)) {
          // System.out.println(r + q.getQId());
          opt.setSelected(true);
        }
      }

    } else if (type.equals("Rating")) {
      // TODO:
    } else if (type.equals("Likert")) {
      // TODO:
    } else if (type.equals("Polar")) {
      VBox container = new VBox(16);
      container.setPadding(new Insets(16));
      container.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 15");
      Text question = new Text();
      RadioButton trueButton = new RadioButton("true");
      RadioButton falseButton = new RadioButton("false");
      container.getChildren().addAll(question, trueButton, falseButton);
      mainView.getChildren().add(container);

      Polar polar = constructQuestion(q);
      question.setText(polar.getQText());

      for (RadioButton btn : new RadioButton[] { trueButton, falseButton }) {
        if (btn.getText().equals(r)) {
          btn.setSelected(true);
        }
      }
    }
  }
}
