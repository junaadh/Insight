package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import db.BinHandler;
import forms.Demographic;
import forms.Likert;
import forms.Mcq;
import forms.Openended;
import forms.Polar;
import forms.Opinion;
import forms.Question;
import forms.Rank;
import javafx.scene.control.TextField;

import helper.Javax;
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
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
        // selector.setPromptText("Choose next question type");
      }
    });
    Button createSurvey = creator.createButton("Create Survey");
    vbox.getChildren().add(createSurvey);
    createSurvey.setMaxWidth(Double.MAX_VALUE);
    createSurvey.setOnAction(event -> {
      if (!surveyQuestions.isEmpty()) {
        for (Question q : surveyQuestions) {
          handler.addQuestions(q);
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
      String qId = handler.genQuestionId();
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
      Openended q = new Openended(surveyId.getText(), true, qId, qtext.getText(), null);
      addButton.setOnAction(event -> {
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        openended.getChildren().clear();
        questionBox.getChildren().remove(openended);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Polar")) {
      String qId = handler.genQuestionId();
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
      Polar q = new Polar(surveyId.getText(), false, qId, qtext.getText());
      addButton.setOnAction(event -> {
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        polar.getChildren().clear();
        questionBox.getChildren().remove(polar);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Demographic")) {
      String qId = handler.genQuestionId();
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
      Demographic q = new Demographic(surveyId.getText(), false, qId, qtext.getText(), null);
      addButton.setOnAction(event -> {
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        demographic.getChildren().clear();
        questionBox.getChildren().remove(demographic);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Opinion")) {
      String qId = handler.genQuestionId();
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
      Opinion q = new Opinion(surveyId.getText(), false, qId, qtext.getText(), null);
      addButton.setOnAction(event -> {
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        opinion
            .getChildren().clear();
        questionBox.getChildren().remove(opinion);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Rank")) {
      ArrayList<String> optionList = new ArrayList<>();
      String qId = handler.genQuestionId();
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
      optionList = component.getOptions();
      Rank q = new Rank(surveyId.getText(), false, qId, qtext.getText(), optionList, null);

      addButton.setOnAction(event -> {
        surveyQuestions.add(q);
      });
      deleteQuestion.setOnAction(event -> {
        rank
            .getChildren().clear();
        questionBox.getChildren().remove(rank);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("Likert")) {
      ArrayList<String> optionList = new ArrayList<>();
      String qId = handler.genQuestionId();
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
      optionList = component.getOptions();
      Likert q = new Likert(surveyId.getText(), false, qId, qtext.getText(), optionList);

      addButton.setOnAction(event -> {
        surveyQuestions.add(q);
      });
      deleteQuestion.setOnAction(event -> {
        likert
            .getChildren().clear();
        questionBox.getChildren().remove(likert);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });

    } else if (type.equals("Rating")) {
      String qId = handler.genQuestionId();
      TextField qtext = creator.createTextField("Enter your question");
      Button addButton = creator.createButton("Add Question");
      Button deleteQuestion = creator.createButton("Delete Question");
      HBox buttons = new HBox(addButton, deleteQuestion);
      buttons.setSpacing(16);
      buttons.setPadding(new Insets(0, 0, 16, 0));
      Node[] nodes = { qtext, buttons };
      VBox likert = creator.createVBox(nodes);
      likert.setSpacing(16);
      questionBox.getChildren().add(likert);
      Likert q = new Likert(surveyId.getText(), false, qId, qtext.getText(), null);
      addButton.setOnAction(event -> {
        if (q != null) {
          surveyQuestions.add(q);
        }
      });
      deleteQuestion.setOnAction(event -> {
        likert
            .getChildren().clear();
        questionBox.getChildren().remove(likert);
        if (surveyQuestions.contains(q)) {
          surveyQuestions.remove(q);
        }
      });
    } else if (type.equals("MCQ")) {
      ArrayList<String> optionList = new ArrayList<>();
      String qId = handler.genQuestionId();
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
      optionList = component.getOptions();
      Mcq q = new Mcq(surveyId.getText(), false, qId, qtext.getText(), optionList, null);

      addButton.setOnAction(event -> {
        surveyQuestions.add(q);
      });
      deleteQuestion.setOnAction(event -> {
        mcq

            .getChildren().clear();
        questionBox.getChildren().remove(mcq);
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

}
