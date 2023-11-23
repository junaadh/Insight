package helper;

import java.util.List;

import db.BinHandler;
import forms.Openended;
import forms.SurveyCreator;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Javax {

	public Button createButton(String prompt) {
		Button button = new Button(prompt);
		button.setPadding(new Insets(16));
		return button;
	}

	public TextField createTextField(String prompt) {
		TextField field = new TextField();
		field.setPromptText(prompt);
		field.setPadding(new Insets(16));
		return field;
	}

	public ComboBox<String> createComboBox(String prompt, ObservableList<String> items) {
		ComboBox<String> box = new ComboBox<String>(items);
		box.setPromptText(prompt);
		box.setPadding(new Insets(16));
		return box;
	}

	public HBox createHBox(Node[] items) {
		HBox hlayout = new HBox(20);
		for (Node item : items) {
			hlayout.getChildren().add(item);
		}

		return hlayout;
	}

	public VBox createVBox(Node[] items) {
		VBox vlayout = new VBox(20);
		for (Node item : items) {
			vlayout.getChildren().add(item);
		}

		return vlayout;
	}

	public static <T> void initializeTableColumn(String[] columnNames, TableView<T> table) {
		table.getColumns().clear();

		for (String li : columnNames) {
			TableColumn<T, String> column = new TableColumn<>(li);
			column.setCellValueFactory(new PropertyValueFactory<>(li));
			table.getColumns().add(column);
		}
	}

	public static <T extends Manipulator> void initializeTableRow(List<T> list,
			TableView<T> table) {
		table.getItems().clear();
		if (!list.isEmpty()) {

			table.getItems().addAll(list);
		}
	}

	// public void createOpenended(VBox parent, String surveyId, SurveyCreator s) {
	// TextField qText = createTextField("Enter Question");
	// TextField qAnswer = createTextField("Enter answer or leave null");
	// ComboxBox<String> compulsory = new ComboBox<>()
	// Openended q = new Openended(surveyId, false, , , )
	// BinHandler handler = new BinHandler();
	// handler.addQuestions()
	// }

}

