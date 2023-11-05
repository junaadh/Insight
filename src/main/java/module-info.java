module aps {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens aps to javafx.fxml;
    exports aps;
}
