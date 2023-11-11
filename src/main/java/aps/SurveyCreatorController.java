package aps;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;

public class SurveyCreatorController {
  @FXML
  private TextField scidTextField;

  @FXML
  private TextField usernameTextField;

  @FXML
  private PasswordField passwordTextField;

  @FXML
  private TextField firstNameTextField;

  @FXML
  private TextField lastNameTextField;

  @FXML
  private ChoiceBox<String> facultyChoiceBox;

  @FXML
  private TextField emailAddressTextField;

  @FXML
  private ChoiceBox<String> genderChoiceBox;

  @FXML
  private TextField phoneNoTextField;

  @FXML
  private Button createButton;

  // Event Listener on Create Button
  @FXML
  public void createSurveyCreator() {
    // get all the form data using getters
    // pass the data to teh business logic and data access layers to save it in the
    // database
  }

}
