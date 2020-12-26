/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
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

    private ObservableList<Vehiculo> vehiculos;

    @FXML
    private TableView<Vehiculo> tblVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> clmMatricula;
    @FXML
    private TableColumn<Vehiculo, String> clmModelo;
    @FXML
    private Label lblMatricula;
    @FXML
    private Label lblModelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();
    }

    public List<Vehiculo> carregaVehiculos() {
        List<Vehiculo> vList= new ArrayList();
        Modelo modelo = Modelo.getModelo();
        Map<String, Vehiculo> v = modelo.getVehiculos();
        for (Map.Entry<String, Vehiculo> entry : v.entrySet()) {

            Vehiculo value = entry.getValue();
            vList.add(value);
        }
        return vList;

    }

    @Override
    public void actualizar() {

        vehiculos = FXCollections.observableArrayList(carregaVehiculos());
        tblVehiculo.setItems(vehiculos);
        clmMatricula.setCellValueFactory((datosFila) -> datosFila.getValue().getMatriculaProperty());
        clmModelo.setCellValueFactory((datosFila) -> datosFila.getValue().getModeloProperty());
        
        tblVehiculo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraVehiculo(newValue));
        tblVehiculo.getSelectionModel().select(0);
    }

    private void muestraVehiculo(Vehiculo vehiculo){
        lblMatricula.setText(vehiculo.getMatricula());
        lblModelo.setText(vehiculo.getModelo());
        
    }
    @FXML
    private void hldbttnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraMenuPrincipal();
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hldbttnEditar(MouseEvent event) {
        
        
    }

}
