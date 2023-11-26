package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Demographic;
import forms.Likert;
import forms.Mcq;
import forms.Openended;
import forms.Polar;
import forms.Opinion;
import forms.Person;
import forms.Question;
import forms.Rank;
import forms.Survey;
import forms.SurveyCreator;
import javafx.scene.control.TextField;

import helper.Javax;
import helper.Session;
import helper.Misc.prefix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SurveyCreationController implements Initializable {

  @FXML
  private VBox vbox;

  @FXML
  private VBox questionBox;

  @FXML
  private ComboBox<String> selector;

  @FXML
  private TextField surveyId;

  Javax creator = new Javax();
  BinHandler handler = new BinHandler();
  ArrayList<Question> surveyQuestions = new ArrayList<>();
  String scId;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Person p = Session.getInstance().getPerson();
    SurveyCreator sc = handler.searchSurveyCreator(prefix.NID, p.getNid());
    scId = sc.getScId();
    dynamicHandler();
  }

  @FXML
  private void switchBack() throws IOException {
    App.setRoot("scDash");
  }

  private void dynamicHandler() {
    ObservableList<String> type = FXCollections.observableArrayList(Arrays.asList(questionTypes()));
    surveyId.setText(handler.genSurveyId());
    surveyId.setEditable(false);
    selector.setItems(type);
    selector.setOnAction(event -> {
      if (selector.getValue() != null) {
        String selected = selector.getValue().toString();
        createQuestion(selected);
      }
    });
    Button createSurvey = creator.createButton("Create Survey");
    vbox.getChildren().add(createSurvey);
    createSurvey.setMaxWidth(Double.MAX_VALUE);
    createSurvey.setOnAction(event -> {
      System.out.println("clicker");
      if (!surveyQuestions.isEmpty()) {
        Survey s = new Survey(surveyId.getText(), scId);
        handler.addSurvey(s);
        for (Question q : surveyQuestions) {
          handler.addQuestions(q);
          System.out.println(q.buildInfo());
        }
        System.out.println("Added Surveys");
        try {
          App.setRoot("scDash");
        } catch (IOException e) {
          System.out.println("ERROR: Failed to change scene " + e.getMessage());
        }
      }
    });
  }

  private String[] questionTypes() {
    String qTypes = "Openended,Polar,Demographic,Opinion,Rank,Likert,Rating,MCQ";
    return qTypes.split(",");
  }

  private void createQuestion(String type) {
    if (type.equals("Openended")) {
      String qId = genQuestionId(surveyQuestions);
      TextField qtext = creator.createTextField("Enter your question");
      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, buttons };
      VBox openended = creator.createVBox(nodes);
      openended.setSpacing(16);
      questionBox.getChildren().add(openended);
      addButton.setOnAction(event -> {
        Openended q = new Openended(surveyId.getText(), false, qId, qtext.getText(), type);
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        openended.getChildren().clear();
        questionBox.getChildren().remove(openended);
        Openended q = new Openended(surveyId.getText(), true, qId, qtext.getText(), type);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Polar")) {
      String qId = genQuestionId(surveyQuestions);
      TextField qtext = creator.createTextField("Enter your question");
      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, buttons };
      VBox polar = creator.createVBox(nodes);
      polar.setSpacing(16);
      questionBox.getChildren().add(polar);
      addButton.setOnAction(event -> {
        Polar q = new Polar(surveyId.getText(), false, qId, qtext.getText(), type);
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        polar.getChildren().clear();
        questionBox.getChildren().remove(polar);
        Polar q = new Polar(surveyId.getText(), false, qId, qtext.getText(), type);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Demographic")) {
      String qId = genQuestionId(surveyQuestions);
      TextField qtext = creator.createTextField("Enter your question");
      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, buttons };
      VBox demographic = creator.createVBox(nodes);
      demographic.setSpacing(16);
      questionBox.getChildren().add(demographic);
      addButton.setOnAction(event -> {
        Demographic q = new Demographic(surveyId.getText(), false, qId, qtext.getText(), type);
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        demographic.getChildren().clear();
        questionBox.getChildren().remove(demographic);
        Demographic q = new Demographic(surveyId.getText(), false, qId, qtext.getText(), type);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Opinion")) {
      String qId = genQuestionId(surveyQuestions);
      TextField qtext = creator.createTextField("Enter your question");
      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, buttons };
      VBox opinion = creator.createVBox(nodes);
      opinion.setSpacing(16);
      questionBox.getChildren().add(opinion);
      addButton.setOnAction(event -> {
        Opinion q = new Opinion(surveyId.getText(), false, qId, qtext.getText(), type);
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        opinion
            .getChildren().clear();
        questionBox.getChildren().remove(opinion);
        Opinion q = new Opinion(surveyId.getText(), false, qId, qtext.getText(), type);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Rank")) {
      String qId = genQuestionId(surveyQuestions);
      TextField qtext = creator.createTextField("Enter your question");

      OptionComponent component = new OptionComponent();

      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, component.getMainBox(), buttons };
      VBox rank = creator.createVBox(nodes);
      rank.setSpacing(16);
      questionBox.getChildren().add(rank);

      ArrayList<String> optionList = component.getOptions();
      addButton.setOnAction(event -> {
        Rank q = new Rank(surveyId.getText(), false, qId, qtext.getText(), type, optionList);
        surveyQuestions.add(q);
      });
      deleteQuestion.setOnAction(event -> {
        rank
            .getChildren().clear();
        questionBox.getChildren().remove(rank);
        Rank q = new Rank(surveyId.getText(), false, qId, qtext.getText(), type, optionList);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Likert")) {
      String qId = genQuestionId(surveyQuestions);
      TextField qtext = creator.createTextField("Enter your question");

      OptionComponent component = new OptionComponent();

      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, component.getMainBox(), buttons };
      VBox likert = creator.createVBox(nodes);
      likert.setSpacing(16);
      questionBox.getChildren().add(likert);
      ArrayList<String> optionList = component.getOptions();

      addButton.setOnAction(event -> {
        Likert q = new Likert(surveyId.getText(), false, qId, qtext.getText(), type, optionList);
        surveyQuestions.add(q);
      });
      deleteQuestion.setOnAction(event -> {
        likert
            .getChildren().clear();
        questionBox.getChildren().remove(likert);
        Likert q = new Likert(surveyId.getText(), false, qId, qtext.getText(), type, optionList);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });

    } else if (type.equals("Rating")) {
      String qId = genQuestionId(surveyQuestions);
      TextField qtext = creator.createTextField("Enter your question");

      OptionComponent component = new OptionComponent();

      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, component.getMainBox(), buttons };
      VBox mcq = creator.createVBox(nodes);
      mcq.setSpacing(16);
      questionBox.getChildren().add(mcq);
      ArrayList<String> optionList = component.getOptions();

      addButton.setOnAction(event -> {
        Likert q = new Likert(surveyId.getText(), false, qId, qtext.getText(), type, optionList);
        surveyQuestions.add(q);
      });
      deleteQuestion.setOnAction(event -> {
        mcq

            .getChildren().clear();
        questionBox.getChildren().remove(mcq);
        Likert q = new Likert(surveyId.getText(), false, qId, qtext.getText(), type, optionList);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("MCQ")) {
      String qId = genQuestionId(surveyQuestions);
      TextField qtext = creator.createTextField("Enter your question");

      OptionComponent component = new OptionComponent();

      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, component.getMainBox(), buttons };
      VBox mcq = creator.createVBox(nodes);
      mcq.setSpacing(16);
      questionBox.getChildren().add(mcq);
      ArrayList<String> optionList = component.getOptions();

      addButton.setOnAction(event -> {
        Mcq q = new Mcq(surveyId.getText(), false, qId, qtext.getText(), type, optionList);
        surveyQuestions.add(q);
      });
      deleteQuestion.setOnAction(event -> {
        mcq

            .getChildren().clear();
        questionBox.getChildren().remove(mcq);
        Mcq q = new Mcq(surveyId.getText(), false, qId, qtext.getText(), type, optionList);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });

    }
  }

  private class OptionComponent {
    private ArrayList<String> options = new ArrayList<>();
    private ArrayList<TextField> optionFields = new ArrayList<>();
    private ArrayList<Button> addButtons = new ArrayList<>();
    private ArrayList<Button> removeButtons = new ArrayList<>();
    private VBox mainBox;

    public OptionComponent() {
      mainBox = new VBox(16);

      for (int i = 0; i < 4; i++) {
        createOptionField(i);
      }
    }

    private void createOptionField(int index) {
      Javax creator = new Javax();
      TextField option = creator.createTextField("Enter Option");
      Button addOption = creator.createButton("+");
      Button removeOption = creator.createButton("-");

      HBox optionHBox = new HBox(16);
      optionHBox.getChildren().addAll(option, addOption, removeOption);
      mainBox.getChildren().add(optionHBox);

      addButtons.add(addOption);
      removeButtons.add(removeOption);
      optionFields.add(option);

      addOption.setOnAction(event -> {
        addOption(index);
      });

      removeOption.setOnAction(event -> {
        removeOption(index);
      });
    }

    private void addOption(int index) {
      TextField optionField = optionFields.get(index);
      String optionText = optionField.getText();
      if (!optionText.isEmpty()) {
        options.add(optionText);
        System.out.println("Added option: " + optionText);
      }
    }

    private void removeOption(int index) {
      TextField optionField = optionFields.get(index);
      String optionText = optionField.getText();
      options.remove(optionText);
      optionField.clear();
      System.out.println("Removed option: " + optionText);
    }

    public VBox getMainBox() {
      return mainBox;
    }

    public ArrayList<String> getOptions() {
      return options;
    }
  }

  private String genQuestionId(ArrayList<Question> list) {
    String qId = handler.genQuestionId();

    if (list.isEmpty()) {
      return qId;
    }

    ArrayList<String> id = new ArrayList<>();
    for (Question q : list) {
      id.add(q.getQId().substring(2));
    }
    Collections.sort(id);
    String last = id.get(id.size() - 1);
    int index = Integer.parseInt(last) + 1;
    return "QX" + String.format("%05d", index);
  }

}
