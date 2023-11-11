package aps;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.BinHandler;
import db.FilePicker.dbFiles;
import forms.Admin;
import forms.Person;
import forms.SurveyCreator;
import forms.User;
import helper.Hasher;
import helper.Misc;
import helper.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SignUpController implements Initializable {

	@FXML
	private ComboBox<String> roleBox;

	@FXML
	private TextField usernameField;

	@FXML
	private TextField emailField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private PasswordField confirmPasswordField;

	@FXML
	private TextField nameField;

	@FXML
	private ComboBox<String> genderBox;

	@FXML
	private TextField ageField;

	@FXML
	private TextField phoneNoField;

	@FXML
	private TextField nidField;

	@FXML
	private TextField nationalityField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");
		genderBox.setItems(genders);
		ObservableList<String> roles = FXCollections.observableArrayList("Admin", "Survey Creator", "User");
		roleBox.setItems(roles);
		if (Session.getInstance().isAdmin()) {
			roleBox.setVisible(true);
			roleBox.setDisable(false);
		}
	}

	@FXML
	private void switchToLogin() throws IOException {
		if (!Session.getInstance().isAdmin()) {
			App.setRoot("login");
		} else {
			App.setRoot("dash");
		}
	}

	@FXML
	private void signInAction() throws IOException {
		if (infoCheck() && !Session.getInstance().isAdmin()) {
			App.setRoot("primary");
		} else {
			App.setRoot("dash");
		}
	}

	private boolean infoCheck() {
		String username = usernameField.getText();
		String email = emailField.getText();
		String password = passwordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		String name = nameField.getText();
		String age = ageField.getText();
		String phoneNo = phoneNoField.getText();
		String nid = nidField.getText();
		String nationality = nationalityField.getText();
		String gender = genderBox.getValue().toString();
		String role = "User";
		if (Session.getInstance().isAdmin()) {
			role = roleBox.getValue().toString();
		}

		boolean usernameIsValid = !username.isBlank() && new BinHandler().usernameExists(username, dbFiles.PERSON);
		boolean emailIsValid = !email.isBlank() && Misc.isEmail(email);
		boolean passwordIsValid = !password.isBlank() && password.length() > 8;
		boolean confirmPasswordIsValid = !confirmPassword.isBlank() && confirmPassword.equals(password);
		boolean nameIsValid = !name.isBlank() && name.contains(" ");
		boolean ageIsValid = !age.isBlank() && Misc.isIntegar(age);
		boolean phoneNoIsValid = !phoneNo.isBlank() && Misc.isIntegar(phoneNo);
		boolean nidIsValid = !nid.isBlank() && new BinHandler().nidExists(nid, dbFiles.PERSON);
		boolean nationalityIsValid = !nationality.isBlank();
		boolean genderIsValid = gender != null;
		boolean roleIsValid = role != null;

		String errStr = "";

		errStr += !usernameIsValid ? "Username already taken\n" : "";
		errStr += !emailIsValid ? "Email is invalid\n" : "";
		errStr += !passwordIsValid ? "Password should be atleast 8 digits\n" : "";
		errStr += !confirmPasswordIsValid ? "Passwords do not match\n" : "";
		errStr += !nameIsValid ? "Please enter your full name\n" : "";
		errStr += !ageIsValid ? "Age should be a number\n" : "";
		errStr += !phoneNoIsValid ? "Phone number can only be integars\n" : "";
		errStr += !nidIsValid ? "One person can register once. Contact admin to reset password\n" : "";
		errStr += !nationalityIsValid ? "Nationality cannot be blank\n" : "";
		errStr += !genderIsValid ? "Please select a gender\n" : "";
		errStr += roleIsValid ? "Have to select a role\n" : "";

		BinHandler handler = new BinHandler();

		String hashed = Hasher.genSalted(password);

		if (errStr.isBlank()) {
			if (role.equals("Survey Creator")) {
				SurveyCreator sc = new SurveyCreator(nid, username, name, hashed, Integer.parseInt(age), gender, email,
						Integer.parseInt(phoneNo), "", "");
				return handler.addSurveyCreator(sc) != null;
			} else if (role.equals("Admin")) {
				Admin admin = new Admin(nid, username, name, hashed, Integer.parseInt(age), gender, email,
						Integer.parseInt(phoneNo), "");
				return handler.addAdmin(admin) != null;
			} else {
				User user = new User(nid, username, name, hashed, Integer.parseInt(age), gender, email,
						Integer.parseInt(phoneNo), "");
				boolean bool = handler.addUser(user) != null;
				if (bool && !Session.getInstance().isAdmin()) {
					Session.getInstance().setPerson((Person) user);
				}
				return bool;
			}
		} else {
			Alert error = new Alert(AlertType.ERROR);
			error.setHeaderText("Invalid input");
			error.setContentText(errStr);
			error.showAndWait();

			return false;
		}
	}

}
