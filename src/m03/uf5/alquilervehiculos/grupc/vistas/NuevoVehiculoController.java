/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class NuevoVehiculoController implements Initializable {

    @FXML
    private TextField txtFieldMatricula;
    @FXML
    private TextField txtFieldModelo;
    @FXML
    private Button bttnGuardar;
    @FXML
    private Button bttnVolver;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleTxtFieldMatricula(KeyEvent event) {

    }

    @FXML
    private void handleTxtFieldModelo(KeyEvent event) {
    }

    @FXML
    private void handlebttnGuardar(MouseEvent event) {
        boolean camposOk = camposEmplenados();
        if (camposOk) {
            validaMatricula();
            txtFieldModelo.getText();
        }

    }

    @FXML
    private void handleBttnVolver(MouseEvent event) {
    }

    private boolean camposEmplenados() {

        if (txtFieldModelo.getText().isEmpty() || txtFieldModelo.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText("Vehículo no introducido");
            alert.setContentText("Todos los campos deben estar completos");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                // ... user chose OK
            } else {
                // ... user chose CANCEL or closed the dialog
            }
            return false;
        }else{
        return true;
        }
    }  

    private String validaMatricula() {
        Pattern reglas = Pattern.compile("[0-9]{4}[[A-Za-z]&&[^AEIOUaeiou]]{3}");
        Matcher matriculaAnalitzar = reglas.matcher(txtFieldMatricula.getText());
        if (!matriculaAnalitzar.matches()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText("Vehículo no introducido");
            alert.setContentText("La matríucula debe estar en el formato correcto");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                // ... user chose OK
            } else {
                // ... user chose CANCEL or closed the dialog
            }
            return null;
        } else {
            return matriculaAnalitzar.group();
        }
    }
}
