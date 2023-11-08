package aps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import db.FilePicker;
import db.FilePicker.dbFiles;
import java.io.File;

/**
 * JavaFX App
 */
public class App extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("landing"), 1220, 720);
    stage.setTitle("Insight");
    Image icon = new Image(getClass().getResource("pics/icampus_icon-removebg-preview-2.png").toExternalForm());
    stage.getIcons().add(icon);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    // launch();
    File testing = FilePicker.getdbFile(dbFiles.SURVEY_CREATORS);
    System.out.println(testing);
  }

}
