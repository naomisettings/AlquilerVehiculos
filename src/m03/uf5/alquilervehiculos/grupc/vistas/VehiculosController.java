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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
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
    @FXML
    private Button bttnNuevo;
    @FXML
    private Button bttnEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();

    }

    public List<Vehiculo> carregaVehiculos() {
        List<Vehiculo> vList = new ArrayList();
        Modelo modelo = Modelo.getModelo();
        Map<String, Vehiculo> v = modelo.getVehiculos();
        for (Map.Entry<String, Vehiculo> entry : v.entrySet()) {

            Vehiculo value = entry.getValue();
            vList.add(value);
        }
        return vList;
    }

    public void insertarTabla() {

        vehiculos = FXCollections.observableArrayList(carregaVehiculos());
        tblVehiculo.setItems(vehiculos);
        clmMatricula.setCellValueFactory((datosFila) -> datosFila.getValue().getMatriculaProperty());
        clmModelo.setCellValueFactory((datosFila) -> datosFila.getValue().getModeloProperty());

        tblVehiculo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraVehiculo(newValue));
        tblVehiculo.getSelectionModel().select(0);

    }

    @Override
    public void actualizar() {
        insertarTabla();
    }

    private void muestraVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            lblMatricula.setText(vehiculo.getMatricula());
            lblModelo.setText(vehiculo.getModelo());
        }

    }

    @FXML
    private void hldbttnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraMenuPrincipal();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hldbttnBorrar(MouseEvent event) {
        Vehiculo v = tblVehiculo.getSelectionModel().getSelectedItem();
        if (!event.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText("Borrar Vehículo");
            alert.setContentText("Desea eliminar el vehiculo: "
                    + v.getMatricula());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Modelo.getModelo().borrarVehiculo(v);
                insertarTabla();


            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }



    @FXML
    private void hldbttnNuevo(ActionEvent event) {
        Stage ventanaPrincipal = (Stage) tblVehiculo.getScene().getWindow();
        Stage ventanaEdicion = new Stage();
        ventanaEdicion.initModality(Modality.WINDOW_MODAL);

        ventanaEdicion.initOwner(ventanaPrincipal);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NuevoVehiculo.fxml"));
        try {
            Scene escenaEdicion = new Scene(loader.load());
            NuevoVehiculoController controladorEdicion = loader.getController();
            ventanaEdicion.setScene(escenaEdicion);

            if (event.getSource() == bttnNuevo) {
                controladorEdicion.setVehiculo(null);
            } else {
                controladorEdicion.setVehiculo(tblVehiculo.getSelectionModel().getSelectedItem());
            }
            ventanaEdicion.showAndWait();

            Vehiculo vehiculo = controladorEdicion.getVehiculo();
            if (vehiculo != null) {
                if (event.getSource() == bttnNuevo) {
                    Modelo.getModelo().addVehiculo(vehiculo);
                    if (vehiculo.getMatricula() != null) {
                        vehiculos.add(vehiculo);
                    }
                } else {

                    Modelo.getModelo().modificarVehiculo(vehiculo,
                            tblVehiculo.getSelectionModel().getSelectedItem().getMatricula());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NuevoVehiculoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
