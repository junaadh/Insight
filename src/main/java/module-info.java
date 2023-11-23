@SuppressWarnings("UnstableApiUsage")
module aps {
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  requires jbcrypt;

  opens aps to javafx.fxml;
  opens forms;

  exports aps;
}
