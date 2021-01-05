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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Alquiler;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Factura;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class FacturaController implements Initializable, MiControlador {
    
    protected static ObservableList<Factura> facturas;
    protected static ObservableList<Alquiler> alquileres;


  @FXML
  private Button bttnVolver;
  @FXML
  private Button bttnMenuPrincipal;
  private TextArea txtAreaFactura;
    @FXML
    private TableView<Factura> tvFactura;
    @FXML
    private TableColumn<Alquiler,String> clmMatricula;
    @FXML
    private TableColumn<Alquiler,LocalDate> clmInicio;
    @FXML
    private TableColumn<Alquiler,LocalDate> clmFin;
    @FXML
    private TableColumn<Factura,String> clmPrecio;
    @FXML
    private TableColumn<Factura,String> clmTotal;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApellido1;
    @FXML
    private Label lblApellido2;
    @FXML
    private Label lblNif;
    @FXML
    private TableColumn<Alquiler,String> clmModelo;
    @FXML
    private TableColumn<Factura,String> clmdias;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
   // actualizar();
  }
  
  public void insertarTabla(){
      facturas = FXCollections.observableArrayList(Modelo.getModelo().getAlquileres());
      tvFactura.setItems(facturas);
      
        clmMatricula.setCellValueFactory((datosFila) -> datosFila.getValue().getVehiculo().getMatriculaProperty());
        clmModelo.setCellValueFactory((datosFila) -> datosFila.getValue().getVehiculo().getMatriculaProperty());
        clmInicio.setCellValueFactory((datosFila) -> datosFila.getValue().getFechaFinProperty());
        clmFin.setCellValueFactory((datosFila) -> datosFila.getValue().getFechaFinProperty());
        clmdias.setCellValueFactory((datosFila) -> datosFila.getValue().getnDias());
        clmPrecio.setCellValueFactory((datosFila)-> datosFila.getValue().getPrecio());
        clmTotal.setCellValueFactory((datosFila) -> datosFila.getValue().getTotal());
        

        tvFactura.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraCliente());
        tvFactura.getSelectionModel().select(0);
      
  }
  
  /*public void muestraFactura(Factura factura){
      if(factura!= null){
         
          lblNif.setText(factura.getCliente().getNif());
          lblNombre.setText(factura.getCliente().getNombre());
          lblApellido1.setText(factura.getCliente().getApellido1());
          lblApellido2.setText(factura.getCliente().getApellido2());
      }
      
      
  }*/
  private void muestraCliente(Cliente cliente) {
        if (cliente != null) {
            lblNif.setText(cliente.getNif());
            lblNombre.setText(cliente.getNombre());
            lblApellido1.setText(cliente.getApellido1());
            lblApellido2.setText(cliente.getApellido2());

        }

    }

  @FXML
  private void handleBttnVolver(MouseEvent event) {
    try {
      GestorEscenas.getGestor().muestraMenuPrincipal();
    } catch (IOException ex) {
      Logger.getLogger(NuevoAlquilerController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  private void handleBttnMenuPrinicpal(MouseEvent event) {
    try {
      GestorEscenas.getGestor().muestraMenuPrincipal();
    } catch (IOException ex) {
      Logger.getLogger(AlquileresController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

    @Override
  public void actualizar() {
    //  insertarTabla();
   /* Modelo modelo = Modelo.getModelo();
    Alquiler alquiler = modelo.getUltimoAlquiler();
    String factura = "";
    factura += modelo.getNombreEmpresa() + "\nCIF: " + modelo.getCif() + "\n"
        + modelo.getDireccion() + "\nTelf: " + modelo.getTelefono();
    factura += "\n--------------------------------\n";
    factura += "Datos del cliente:\n";
    factura += alquiler.getCliente().toString() + "\n";
    factura += "--------------------------------\n";
    factura += "Datos del vehiculo:\n";
    factura += alquiler.getVehiculo().toString() + "\n";
    factura += "--------------------------------\n";

    Period dias = Period.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
    double precio = dias.getDays() * modelo.PRECIO_DIARIO_ALQUILER;
    factura += "Coste Total: " + precio + " €";
    txtAreaFactura.setText(factura);*/

  }

    private void muestraCliente() {
        
    }
}
