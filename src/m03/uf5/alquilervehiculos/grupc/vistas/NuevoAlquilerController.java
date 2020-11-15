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
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
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

    @FXML
    private Button btnReservar;
    @FXML
    private Button btnVolver;
    @FXML
    private DatePicker dpFin;
    @FXML
    private DatePicker dpInicio;

    @FXML
    private ComboBox<String> cbxNif;
    @FXML
    private ComboBox<String> cbxMatricula;
    @FXML
    private Label lbdata;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }

    @Override
    public void actualizar() {
       

         
    }

    public void cargarDatos() { //CARREGA LES DADES AL COMBOBOX
        ObservableList<String> nif = FXCollections.observableArrayList();
        ObservableList<String> matricula = FXCollections.observableArrayList();
        Set<Cliente> clientes;
        clientes = Modelo.getModelo().getClientes();
        Set<Vehiculo> vehiculos;
        vehiculos = Modelo.getModelo().getVehiculos();

        for (Cliente cliente : clientes) {
            nif.add(cliente.getNif());
        }

        for (Vehiculo vehiculo : vehiculos) {
            matricula.add(vehiculo.getMatricula());
        }

        if (cbxNif.getValue() == null) {
            cbxNif.setItems((ObservableList<String>) nif);
            cbxMatricula.setItems(matricula);
        }
      
    }

    public void borrarDatos() {
        cbxNif.setValue("");
        cbxMatricula.setValue("");
        dpInicio.getEditor().clear();
        dpFin.getEditor().clear();
       
    }

    public void guardarNif() {

        String nif = cbxNif.getValue();
        if (nif != null) {
            
        }

    }

    public void guardarMatricula() {

        String matricula = cbxMatricula.getValue();
        if (matricula != null) {
            
        }

    }

    @FXML
    private void handleBotonVolver(ActionEvent event) throws IOException {
        GestorEscenas.getGestor().muestraMenuPrincipal();
    }

    @FXML
    private void handleDatePickerInicio(ActionEvent event) { //RECOLLIM LA DATA INICIAL SELECIONADA

        LocalDate fechaInicio = dpInicio.getValue();

        guardarFechaInicio();
    }

    @FXML
    private void handleDatePickerFin(ActionEvent event) { //RECOLLIM LA DATA FINAL SELECIONADA

        LocalDate fechaFin = dpFin.getValue();

        guardarFechaFin();

    }

    public void guardarFechaInicio() {
        LocalDate fechaInicio = dpInicio.getValue();
        String inicio = fechaInicio.toString();

    }

    public void guardarFechaFin() {

        LocalDate fechaFin = dpFin.getValue();
        String fin = fechaFin.toString();

    }

    private boolean comprobarCampos() {
        LocalDate hoy = LocalDate.now();
        boolean comprobarTodos = true;
        //comprueba que los campos no esten vacios
      
        if (cbxNif.getValue() == null || cbxMatricula.getValue() == null
                || dpFin.getValue() == null || dpInicio.getValue() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alquiler de vehiculos");
            alert.setHeaderText("Campo Vacio");
            alert.setContentText("Todos los campos deben estar completos");
            alert.showAndWait();
            comprobarTodos = false;
        }
        if (comprobarTodos){

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
    private void handleComboBoxNif(ActionEvent even) { // MOSTRA LES DADES CARREGADES AL COMBOBOX AL FER UNA ACCIO
        guardarNif();

    }

    @FXML
    private void handleComboBoxMatricula(ActionEvent even) { // MOSTRA LES DADES CARREGADES AL COMBOBOX AL FER UNA ACCIO
        guardarMatricula();
    }

    @FXML
    private void handleBotonReservar(ActionEvent event) throws IOException {

      //  obtenerDias();
        Set<Cliente> clientes = Modelo.getModelo().getClientes();
        Set<Vehiculo> vehiculos = Modelo.getModelo().getVehiculos();

        Cliente client = null;
        for (Iterator<Cliente> it = clientes.iterator(); it.hasNext();) {
            client = it.next();
            if (client.getNif().equals(cbxNif.getValue())) {
                break;
            }
        }
        Vehiculo vehi = null;
        for (Iterator<Vehiculo> it = vehiculos.iterator(); it.hasNext();) {
            vehi = it.next();
            if (vehi.getMatricula().equals(cbxMatricula.getValue())) {
                break;
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inicio = dpInicio.getValue();
        LocalDate fin = dpFin.getValue();
        
        if (comprobarCampos()) {
            Alquiler a = new Alquiler(client, vehi, inicio, fin);
            Modelo.getModelo().addAlquiler(a);
             Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alquiler de vheiculos");
            alert.setHeaderText(null);
            alert.setContentText("Alquiler registrado con éxito");
            alert.showAndWait();
             borrarDatos();
            GestorEscenas.getGestor().muestraFactura();
           
        }
        

    }
}
