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
import helper.Javax;
import helper.Misc;
import helper.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SignUpController implements Initializable {

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

	@FXML
	private VBox vbox;

	@FXML
	private StackPane stack;

	private String roleGlobal;
	private String userIdValue;
	private String adminIdValue;
	private String scIdValue;
	private String scDept;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");
		genderBox.setItems(genders);
		// debuggin only
		Session.getInstance().setIsAdmin();
		dynamicHandler();
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
		if (infoCheck()) {
			if (Session.getInstance().isAdmin()) {
				App.setRoot("primary");
			}
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
		String gender = genderBox.getValue() != null ? genderBox.getValue().toString() : null;
		// if (Session.getInstance().isAdmin()) {
		// role = roleBox.getValue() != null ? roleBox.getValue().toString() : null;
		// }

		boolean usernameIsValid = !username.isBlank() && !(new BinHandler().usernameExists(username, dbFiles.PERSON));
		boolean emailIsValid = !email.isBlank() && Misc.isEmail(email);
		boolean passwordIsValid = !password.isBlank() && password.length() > 8;
		boolean confirmPasswordIsValid = !confirmPassword.isBlank() && password.equals(confirmPassword);
		boolean nameIsValid = !name.isBlank() && name.contains(" ");
		boolean ageIsValid = !age.isBlank() && Misc.isIntegar(age);
		boolean phoneNoIsValid = !phoneNo.isBlank() && Misc.isIntegar(phoneNo);
		boolean nidIsValid = !nid.isBlank() && !(new BinHandler().nidExists(nid, dbFiles.PERSON));
		boolean nationalityIsValid = !nationality.isBlank();
		boolean genderIsValid = gender != null;
		boolean roleIsValid = roleGlobal != null;

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
		errStr += !roleIsValid ? "Have to select a role\n" : "";

		BinHandler handler = new BinHandler();

		String hashed = Hasher.genSalted(password);

		if (errStr.isBlank()) {
			if (roleGlobal.equals("Survey Creator")) {
				SurveyCreator sc = new SurveyCreator(nid, username, name, hashed, Integer.parseInt(age), gender, email,
						Integer.parseInt(phoneNo), nationality, scIdValue, scDept);
				return handler.addSurveyCreator(sc) != null;
			} else if (roleGlobal.equals("Admin")) {
				Admin admin = new Admin(nid, username, name, hashed, Integer.parseInt(age), gender, email,
						Integer.parseInt(phoneNo), nationality, adminIdValue);
				return handler.addAdmin(admin) != null;
			} else {
				User user = new User(nid, username, name, hashed, Integer.parseInt(age), gender, email,
						Integer.parseInt(phoneNo), nationality, userIdValue);
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

	private void dynamicHandler() {
		Javax creator = new Javax();
		ObservableList<String> depts = FXCollections.observableArrayList("IT", "HR", "ADMIN");
		Button trigger = creator.createButton("Sign Up");
		trigger.setMaxWidth(Double.MAX_VALUE);
		trigger.setOnAction(event -> {
			try {
				signInAction();
			} catch (IOException e) {
				System.out.println("ERROR: Failed to invoke signin");
			}
		});

		ObservableList<String> roles;
		if (Session.getInstance().isAdmin()) {
			trigger.setText("Set Up");
			roles = FXCollections.observableArrayList("Admin", "Survey Creator", "User");
		} else {
			roles = FXCollections.observableArrayList("User");
		}
		ComboBox<String> roleBox = creator.createComboBox("Assign Role", roles);
		BinHandler bin = new BinHandler();
		userIdValue = bin.genUserId();
		adminIdValue = bin.genAdminId();
		scIdValue = bin.genSurveyCreatorId();

		stack.getChildren().add(roleBox);
		VBox dynB = new VBox(20);
		vbox.getChildren().add(dynB);
		vbox.getChildren().add(trigger);

		roleBox.setOnAction(event -> {
			String role = roleBox.getValue().toString();
			roleGlobal = role;

			if (role.equals("User")) {
				TextField userid = creator.createTextField(userIdValue);
				userid.setEditable(false);
				dynB.getChildren().clear();
				dynB.getChildren().add(userid);
			} else if (role.equals("Admin")) {
				TextField adminid = creator.createTextField(adminIdValue);
				adminid.setEditable(false);
				dynB.getChildren().clear();
				dynB.getChildren().add(adminid);
			} else {
				ComboBox<String> dept = creator.createComboBox("Department", depts);
				TextField scId = creator.createTextField(scIdValue);
				scId.setEditable(false);
				Node[] nodes = { dept, scId };
				HBox sc = creator.createHBox(nodes);
				dynB.getChildren().clear();
				dynB.getChildren().add(sc);
			}
		});

	}

}
