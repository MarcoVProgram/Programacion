module manager.apppersonasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens manager.apppersonasfx to javafx.fxml;
    exports manager.apppersonasfx;
}