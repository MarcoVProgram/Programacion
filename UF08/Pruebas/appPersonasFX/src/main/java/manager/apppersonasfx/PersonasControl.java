package manager.apppersonasfx;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import manager.apppersonasfx.model.Persona;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonasControl implements Initializable {

    private Persona pp;
    private ObservableList<Persona> personas = FXCollections.observableArrayList();

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private AnchorPane mainView;

    @FXML
    private AnchorPane formView;

    @FXML
    private TextField dniTextF;

    @FXML
    private TextField nombreTextF;

    @FXML
    private TextField apellidoTextF;

    @FXML
    private TextField emailTextF;

    @FXML
    private TextField telefonoTextF;

    @FXML
    private TextField edadTextF;


    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        this.mainView.setVisible(true);
        this.formView.setVisible(true);
        this.clearTextFields();
    }

    @FXML
    protected void onInsertarPersonaButtonClick(ActionEvent event) {
        this.mainPanel.setVisible(false);
        this.formView.setVisible(false);
    }

    @FXML
    protected void onVerTodoButtonClick(ActionEvent event) {}

    @FXML
    protected void onBuscarPersonaButtonClick(ActionEvent event) {}

    @FXML
    public void onSalirButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void clearTextFields() {
        this.dniTextF.clear();
        this.nombreTextF.clear();
        this.apellidoTextF.clear();
        this.emailTextF.clear();
        this.telefonoTextF.clear();
        this.edadTextF.clear();
    }
}