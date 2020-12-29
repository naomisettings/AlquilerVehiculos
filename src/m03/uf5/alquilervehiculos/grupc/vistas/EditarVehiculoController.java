/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import m03.uf5.alquilervehiculos.grupc.modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class EditarVehiculoController implements Initializable, MiControlador {

    protected static Vehiculo nuevoVehiEnviar;
    @FXML
    private TextField txtFdMatricula;
    @FXML
    private TextField txtFldModelo;
    @FXML
    private Label lblMatriculaAntigua;
    @FXML
    private Label lblModeloAntiguo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();
    }

    @FXML
    private void hdlBttnGuardar(MouseEvent event) {
        Vehiculo v = new Vehiculo();
        boolean camposOk = v.camposEmplenados(txtFdMatricula.getText().isEmpty(),
                txtFldModelo.getText().isEmpty());
        if (!camposOk) {
            Boolean matriculaOk = v.validaMatricula(txtFdMatricula.getText());
            if (matriculaOk) {

                Vehiculo vehiEnviar;
                vehiEnviar = VehiculosController.vEnviar;

                v.setMatricula(txtFdMatricula.getText());
                v.setModelo(txtFldModelo.getText());
                String matricula_original = vehiEnviar.getMatricula();
                System.out.println(matricula_original);

                Modelo.getModelo().modificarVehiculo(v, matricula_original);
                nuevoVehiEnviar = v;

            }
        }
    }

    @FXML
    private void hdleBttnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraVehiculos();
        } catch (IOException ex) {
            Logger.getLogger(VehiculosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar() {
        lblMatriculaAntigua.setText(VehiculosController.vEnviar.getMatricula());
        lblModeloAntiguo.setText(VehiculosController.vEnviar.getModelo());
    }

}
