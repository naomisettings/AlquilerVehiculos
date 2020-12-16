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
import javafx.collections.FXCollections;
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
  private void handleBotonVolver(ActionEvent event) throws IOException {
    GestorEscenas.getGestor().muestraMenuPrincipal();
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
      String nif = (String) (cbxNif.getSelectionModel().getSelectedItem());
      Cliente cliente = modelo.getClientes().get(nif);
      String matricula = (String) (cbxMatricula.getSelectionModel().getSelectedItem());
      Vehiculo vehiculo = modelo.getVehiculos().get(matricula);
      Alquiler alquiler = new Alquiler(cliente, vehiculo, dpInicio.getValue(), dpFin.getValue());
      modelo.addAlquiler(alquiler);
      cbxNif.getSelectionModel().clearSelection();
      cbxMatricula.getSelectionModel().clearSelection();
      dpInicio.setValue(null);
      dpFin.setValue(null);
      GestorEscenas.getGestor().muestraFactura();
    }

  }
}
