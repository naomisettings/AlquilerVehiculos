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
        cbxNif.getOnMouseClicked().toString();
        System.out.println(cbxNif);

    }

    @FXML
    private void handleBotonVolver(ActionEvent event) throws IOException {
        GestorEscenas.getGestor().muestraMenuPrincipal();
    }

    @FXML
    private void handleDatePickerInicio(ActionEvent event) { //RECOLLIM LA DATA INICIAL SELECIONADA
        LocalDate fechaInicio = dpInicio.getValue();
        System.out.println("Selected date: " + fechaInicio);
    }

    @FXML
    private void handleDatePickerFin(ActionEvent event) { //RECOLLIM LA DATA FINAL SELECIONADA
        LocalDate fechaFin = dpFin.getValue();
        System.out.println("Fecha fin: " + fechaFin);

    }
public void obtenerDias(){
    LocalDate fechaInicio = dpInicio.getValue();
    LocalDate fechaFin = dpFin.getValue();
    Period diasTotales = Period.between(fechaFin, fechaInicio);
    System.out.println("Dias totales de alquiler " + diasTotales);
}
    @FXML
    private void handleComboBoxNif(ActionEvent event) { // MOSTRA LES DADES CARREGADES AL COMBOBOX AL FER UNA ACCIO
        cargarDatos();

    }

    @FXML
    private void handleComboBoxMatricula(ActionEvent event) { // MOSTRA LES DADES CARREGADES AL COMBOBOX AL FER UNA ACCIO
        cargarDatos();
    }

    public void crearFactura() {
        System.out.println("Alquileres Copèrnic");
        System.out.println("CIF: 12345678A " );
        System.out.println("Torrent del Batlle, 10 08225 Terrassa");
        System.out.println("Telf: 931231245");
        System.out.println("");
        System.out.println("-----------");
        System.out.println("");
        System.out.println("Datos del cliente:");
        System.out.println("Nif y nombre");
        System.out.println("");
        System.out.println("-----------");
        System.out.println("");
        System.out.println("Datos del vehiculo:");
        System.out.println("Matricula y marca");
         System.out.println("");
        System.out.println("-----------");
        System.out.println("");
        obtenerDias();
        
    }

    @FXML
    private void handleBotonReservar(ActionEvent event) throws IOException {
        GestorEscenas.getGestor().muestraFactura();
        crearFactura();

    }

}
