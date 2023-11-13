package helper;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
}
