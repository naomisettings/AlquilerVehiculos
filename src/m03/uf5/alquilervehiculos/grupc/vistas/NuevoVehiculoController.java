/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class NuevoVehiculoController implements Initializable, MiControlador {

    private Vehiculo vehiculo;

    @FXML
    private TextField txtFieldMatricula;
    @FXML
    private TextField txtFieldModelo;
    @FXML
    private Button bttnGuardar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();
    }

    @FXML

    private void handlebttnGuardar(MouseEvent event) {
        Vehiculo v = new Vehiculo();
        String guardaTxtFieldMatricula = txtFieldMatricula.getText();
        String guardaTxtFieldModelo = txtFieldModelo.getText();
        if (v.camposEmplenados(guardaTxtFieldMatricula.isEmpty(),
                guardaTxtFieldModelo.isEmpty())) {

            if (guardaTxtFieldMatricula.equals(vehiculo.getMatricula())
                    || v.matriculaRepetida(guardaTxtFieldMatricula)) {

                if (v.validaMatricula(txtFieldMatricula.getText())) {
                    vehiculo.setMatricula(guardaTxtFieldMatricula);
                    vehiculo.setModelo(guardaTxtFieldModelo);
                    Stage ventana = (Stage) bttnGuardar.getScene().getWindow();
                    ventana.close();

                } else {
                    txtFieldMatricula.setText("");
                    txtFieldModelo.setText("");
                }
            } else {
                txtFieldMatricula.setText("");

            }
        }
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        if (vehiculo != null) {
            txtFieldMatricula.setText("" + vehiculo.getMatricula());
            txtFieldModelo.setText("" + vehiculo.getModelo());
        } else {
            this.vehiculo = new Vehiculo();
        }
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void handleBttnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraVehiculos();
        } catch (IOException ex) {
            Logger.getLogger(VehiculosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar() {
    }

    @FXML
    private void handlebttnCancelar(ActionEvent event) {
        Stage ventana = (Stage) bttnGuardar.getScene().getWindow();
        ventana.close();
    }

}
