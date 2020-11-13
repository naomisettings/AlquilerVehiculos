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
import java.util.Iterator;
import java.util.List;
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
public class NuevoAlquilerController implements Initializable {

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
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
        
        if(cbxNif.getValue()==null){
           cbxNif.setItems((ObservableList<String>) nif); 
           cbxMatricula.setItems(matricula);
        }/*else{
        
        cbxMatricula.setItems(matricula);
        }*/
      //cbxNif.getOnMouseClicked().toString();
        

    }

    public void guardarNif() {
         
        String nif = cbxNif.getValue();
        if(nif!=null){
           System.out.println("El nif es " + nif);  
         }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No has introducido el nif");
            alert.setContentText("Introduce un nif");
            alert.showAndWait();
        }
        
    }

    public void guardarMatricula() {
        
        String matricula = cbxMatricula.getValue();
        if(matricula!=null){
           System.out.println("El matricula es  " + matricula);  
         }
       
    }

    @FXML
    private void handleBotonVolver(ActionEvent event) throws IOException {
        GestorEscenas.getGestor().muestraMenuPrincipal();
    }

    @FXML
    private void handleDatePickerInicio(ActionEvent event) { //RECOLLIM LA DATA INICIAL SELECIONADA
        
        LocalDate fechaInicio = dpInicio.getValue();
        String inicio = fechaInicio.toString();
        guardarFechaInicio();
    }

    @FXML
    private void handleDatePickerFin(ActionEvent event) { //RECOLLIM LA DATA FINAL SELECIONADA
       
        LocalDate fechaFin = dpFin.getValue();
        String fin = fechaFin.toString();
        guardarFechaFin();

    }
     public void guardarFechaInicio() {
        LocalDate fechaInicio = dpInicio.getValue();
        String inicio = fechaInicio.toString();
        System.out.println("La fecha de inicial es " + inicio);
    }
         public void guardarFechaFin() {
       
        LocalDate fechaFin = dpFin.getValue();
        String fin = fechaFin.toString();
       System.out.println("La fecha final es " + fin);
    }
//poner guardar los datos en csv
    public void obtenerDias() {
        LocalDate fechaInicio = dpInicio.getValue();
        LocalDate fechaFin = dpFin.getValue();
        Period periodo = Period.between(fechaInicio, fechaFin);
        int diasTotales = periodo.getDays();
        System.out.println("Dias totales de alquiler " + diasTotales);
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
        GestorEscenas.getGestor().muestraFactura();
       //obtenerDias();
       Alquiler a = new Alquiler(cbxNif.getValue(),cbxMatricula.getValue(),
               dpFin.getValue().toString(),dpInicio.getValue().toString());
        Modelo.getModelo().addAlquiler(a);

    }


 

}