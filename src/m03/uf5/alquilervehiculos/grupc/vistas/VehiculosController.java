/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import m03.uf5.alquilervehiculos.grupc.modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class VehiculosController implements Initializable, MiControlador {

    private ObservableMap<String, Vehiculo> vehiculos;

    @FXML
    private TableView<Vehiculo> tblVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> clmMatricula;
    @FXML
    private TableColumn<Vehiculo, String> clmModelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();
    }

    public Map<String, Vehiculo> carregaVehiculos() {
        Modelo modelo = Modelo.getModelo();
        Map<String, Vehiculo> vehiculos = modelo.getVehiculos();
        
        return vehiculos;
        
    }

    @Override
    public void actualizar() {

       vehiculos = FXCollections.observableMap(carregaVehiculos());
       

    }

    @FXML
    private void hldbttnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraMenuPrincipal();
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
