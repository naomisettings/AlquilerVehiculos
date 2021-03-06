/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import m03.uf5.alquilervehiculos.grupc.modelo.Alquiler;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import m03.uf5.alquilervehiculos.grupc.modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Angels
 */
public class NuevoAlquilerController implements Initializable, MiControlador {

    private Alquiler alquiler;

    @FXML
    private Button btnReservar;
    @FXML
    private Button btnVolver;
    @FXML
    private DatePicker dpFin;
    @FXML
    private DatePicker dpInicio;

    private ComboBox<String> cbxNif;
    private ComboBox<String> cbxMatricula;
    @FXML
    private Label lbdata;
    @FXML
    private Label txtNif;
    @FXML
    private Label txtMatricula;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //cargarDatos();
    }

    @Override
    public void actualizar() {

    }

    public void cargarDatos() { //CARREGA LES DADES AL COMBOBOX
        Modelo modelo = Modelo.getModelo();
        Map<String, Cliente> clientes = modelo.getClientes();
        List listaNifs = new ArrayList(clientes.keySet());
        cbxNif.setItems(FXCollections.observableList(listaNifs));
        Map<String, Vehiculo> vehiculos = modelo.getVehiculos();
        List listaMatriculas = new ArrayList(vehiculos.keySet());
        cbxMatricula.setItems(FXCollections.observableList(listaMatriculas));

    }

    public void borrarDatos() {
        cbxNif.setValue("");
        cbxMatricula.setValue("");
        dpInicio.getEditor().clear();
        dpFin.getEditor().clear();

    }

    @FXML
    private void handleBotonVolver(ActionEvent event) {
        Stage ventana = (Stage) btnVolver.getScene().getWindow();
        ventana.close();
    }

    private boolean comprobarCampos() {
        LocalDate hoy = LocalDate.now();
        boolean comprobarTodos = true;
        //comprueba que los campos no esten vacios

        if (txtNif.getText().isEmpty() || txtMatricula.getText().isEmpty()
                || dpFin.getValue() == null || dpInicio.getValue() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alquiler de vehiculos");
            alert.setHeaderText("Campo Vacio");
            alert.setContentText("Todos los campos deben estar completos");
            alert.showAndWait();
            comprobarTodos = false;
        }
        if (comprobarTodos) {

            //comprueba que la fecha final no sea anterior a la fecha inicial
            if (dpFin.getValue().isBefore(dpInicio.getValue())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Alquiler de vehiculos");
                alert.setHeaderText("Fecha incorrecta");
                alert.setContentText("La fecha inicial tiene que ser menor a la final");
                alert.showAndWait();
                comprobarTodos = false;
            }
            //comprueba que la fecha inicial no sea anterior al dia actual
            if (dpInicio.getValue().isBefore(hoy)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Alquiler de vehiculos");
                alert.setHeaderText("Fecha incorrecta");
                alert.setContentText("La fecha inicial tiene que ser posterior a hoy");
                alert.showAndWait();
                comprobarTodos = false;
            }
        }
        return comprobarTodos;
    }

    public void obtenerDias() {
        LocalDate fechaInicio = dpInicio.getValue();
        LocalDate fechaFin = dpFin.getValue();
        Period periodo = Period.between(fechaInicio, fechaFin);
        int diasTotales = periodo.getDays();

    }

    @FXML
    private void handleBotonReservar(ActionEvent event) throws IOException {
        if (comprobarCampos()) {
            Modelo modelo = Modelo.getModelo();
            String nif = txtNif.getText();
            Cliente cliente = modelo.getClientes().get(nif);
            String matricula = txtMatricula.getText();
            Vehiculo vehiculo = modelo.getVehiculos().get(matricula);
            LocalDate fechaInicio = dpInicio.getValue();
            LocalDate fechaFin = dpFin.getValue();

            alquiler.setCliente(cliente);
            alquiler.setVehiculo(vehiculo);
            alquiler.setFechaInicio(new SimpleObjectProperty<LocalDate>(fechaInicio));
            alquiler.setFechaFin(new SimpleObjectProperty<LocalDate>(fechaFin));

            Stage ventana = (Stage) btnReservar.getScene().getWindow();
            ventana.close();

        }

    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        if (alquiler != null) {
            txtNif.setText(alquiler.getCliente().getNif());
            txtMatricula.setText(alquiler.getVehiculo().getMatricula());
            dpInicio.setValue(alquiler.getFechaInicio());
            dpFin.setValue(alquiler.getFechaFin());
        } else {
            alquiler = new Alquiler();
        }
        this.alquiler = alquiler;
    }

    @FXML
    private void handleBuscarNif(ActionEvent event) {
        Stage ventanaPrincipal = (Stage) btnReservar.getScene().getWindow();
        Stage ventanaEdicion = new Stage();
        ventanaEdicion.initModality(Modality.WINDOW_MODAL);
        ventanaEdicion.setTitle("Alquiler de veh??culos");

        ventanaEdicion.initOwner(ventanaPrincipal);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("BuscarCliente.fxml"));
        Scene escenaEdicion;
        try {
            escenaEdicion = new Scene(loader.load());
            BuscarClienteController controller = loader.getController();
            ventanaEdicion.setScene(escenaEdicion);
            ventanaEdicion.showAndWait();
            Cliente c = controller.getC();
            if (c != null) {
                txtNif.setText(c.getNif());
            }
        } catch (IOException ex) {
            Logger.getLogger(NuevoAlquilerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBuscarMatricula(ActionEvent event) {
        Stage ventanaPrincipal = (Stage) btnReservar.getScene().getWindow();
        Stage ventanaEdicion = new Stage();
        ventanaEdicion.initModality(Modality.WINDOW_MODAL);
        ventanaEdicion.setTitle("Alquiler de veh??culos");

        ventanaEdicion.initOwner(ventanaPrincipal);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("BuscarVehiculo.fxml"));
        Scene escenaEdicion;
        try {
            escenaEdicion = new Scene(loader.load());
            BuscarVehiculoController controller = loader.getController();
            ventanaEdicion.setScene(escenaEdicion);
            ventanaEdicion.showAndWait();
            Vehiculo v = controller.getV();
            if (v != null) {
                txtMatricula.setText(v.getMatricula());
            }
        } catch (IOException ex) {
            Logger.getLogger(NuevoAlquilerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
