module com.example.pratica3324 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pratica3324 to javafx.fxml;
    exports com.example.pratica3324;
}