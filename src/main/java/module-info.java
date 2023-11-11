module aps {
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  requires jbcrypt;

  opens aps to javafx.fxml;

  exports aps;
}
