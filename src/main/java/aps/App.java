package aps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.RandomAccessFile;

import db.FilePicker;
import db.FilePicker.dbFiles;

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

  public static void main(String[] args) throws IOException {
    // launch();
    RandomAccessFile testing = FilePicker.getdbFile(dbFiles.SURVEYS);
    RandomAccessFile testing2 = FilePicker.getdbFile(dbFiles.ADMINS);
    System.out.println(testing);
    System.out.println(testing2);
  }

}
