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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private Button verAlquiler;
    @FXML
    private Button verVehiculos;
    @FXML
    private ImageView imgCotxe;
    @FXML
    private Button verClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

 
    @FXML
    private void handleBttnVerAlquiler(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraAlquileres();
            
        } catch (IOException ex) {
            Logger.getLogger(AlquileresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @FXML
    private void handleBttnVerClientes(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraClientes();
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBttnVerVehiculos(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraVehiculos();
        } catch (IOException ex) {
            Logger.getLogger(VehiculosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleBttnNuevoAlquiler(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraNuevoAlquiler();
        } catch (IOException ex) {
            Logger.getLogger(NuevoAlquilerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
