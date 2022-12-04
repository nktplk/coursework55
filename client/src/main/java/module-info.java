module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
    exports com.example.client.controllers.windows;
    exports com.example.client.controllers.tables;
    opens com.example.client.controllers.tables to javafx.fxml;
    opens com.example.client.controllers.windows to javafx.fxml;
    opens com.example.client.controllers.charts to javafx.fxml;

    requires importify;
    requires AnimateFX;
}