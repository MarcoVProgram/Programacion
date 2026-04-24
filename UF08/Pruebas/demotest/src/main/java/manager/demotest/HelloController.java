package manager.demotest;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    @FXML
    private Label hiddenText;

    @FXML
    private Label hiddenText1;

    @FXML
    private Label hiddenText2;

    @FXML
    private Label hiddenText3;

    @FXML
    private Label hiddenText4;

    @FXML
    private Button helloButton;

    @FXML
    protected void onHelloButtonClick() {
        hiddenText.setText("Esta es nuestra primera APP de JavaFX!");
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void onSelectButtonClick() {
        hiddenText1.setText("Has hecho click en Select!");
    }

    @FXML
    protected void onAddButtonClick() {
        hiddenText2.setText("Has hecho click en Add!");
    }

    @FXML
    protected void onDeleteButtonClick() {
        hiddenText3.setText("Has hecho click en Delete!");
    }


    private boolean toggled = false;

    @FXML
    protected void onToggleButtonToggle() {
        if (toggled) {
            toggled = false;
            hiddenText4.setText("¡No Eres Sabio!");
        }
        else {
            toggled = true;
            hiddenText4.setText("¡Eres Sabio!");
        }
    }
}