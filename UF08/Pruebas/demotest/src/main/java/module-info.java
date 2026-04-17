module manager.demotest {
    requires javafx.controls;
    requires javafx.fxml;


    opens manager.demotest to javafx.fxml;
    exports manager.demotest;
}