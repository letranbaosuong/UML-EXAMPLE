module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires socket.io.client;
    requires engine.io.client;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}