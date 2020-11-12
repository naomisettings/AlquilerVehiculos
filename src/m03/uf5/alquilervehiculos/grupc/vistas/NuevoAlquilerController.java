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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import m03.uf5.alquilervehiculos.grupc.GestorEscenas;

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
        ObservableList<String> nif = FXCollections.observableArrayList("45471259Y", "58962147Z", "25741958P", "84235719O", "65235719O");
        ObservableList<String> matricula = FXCollections.observableArrayList("3415KNJ", "5632KMN", "5874PLK", "8745HJN", "5845HJN");

        cbxNif.setItems(nif);
        cbxMatricula.setItems(matricula);
      //cbxNif.getOnMouseClicked().toString();
        

    }

    public void guardarNif() {
         
        String nif = cbxNif.getValue();
        if(nif!=null){
           System.out.println("El nif es " + nif);  
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
          /* DatePicker dpInicio = new DatePicker();*/
        LocalDate fechaInicio = dpInicio.getValue();
        String inicio = fechaInicio.toString();
      //  System.out.println("Selected date: " + fechaInicio);
      //  System.out.println("fecha incio cadena: " + inicio);
       guardarFechaInicio();
    }

    @FXML
    private void handleDatePickerFin(ActionEvent event) { //RECOLLIM LA DATA FINAL SELECIONADA
       /*DatePicker dpFin = new DatePicker();*/
        LocalDate fechaFin = dpFin.getValue();
        String fin = fechaFin.toString();
      //  System.out.println("Selected date: " + fechaFin);
      //  System.out.println("fecha fin cadena: " + fin);
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
        //GestorEscenas.getGestor().muestraFactura();
       obtenerDias();
        

    }


 

}