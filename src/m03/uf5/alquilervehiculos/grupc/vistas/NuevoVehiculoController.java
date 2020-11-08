/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleTxtFieldMatricula(KeyEvent event) {
        txtFieldMatricula.getText();
    }

    @FXML
    private void handleTxtFieldModelo(KeyEvent event) {
        txtFieldModelo.getText();
    }

    @FXML
    private void handlebttnGuardar(MouseEvent event) {
    }

    @FXML
    private void handleBttnVolver(MouseEvent event) {
    }
    
}
