package manager.demotest;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    @FXML
    private Label hiddenText;

    @FXML
    protected void onHelloButtonClick() {
        hiddenText.setText("Esta es nuestra primera APP de JavaFX!");
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }
}