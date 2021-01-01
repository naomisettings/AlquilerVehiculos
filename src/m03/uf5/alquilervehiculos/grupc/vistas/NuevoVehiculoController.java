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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        if (!event.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText("Modificar Vehículo");
            alert.setContentText("Se ha modificado el vehiculo");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        vehiculo.setMatricula(txtFieldMatricula.getText());
        vehiculo.setModelo(txtFieldModelo.getText());
        Stage ventana = (Stage) bttnGuardar.getScene().getWindow();
        ventana.close();
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


}
