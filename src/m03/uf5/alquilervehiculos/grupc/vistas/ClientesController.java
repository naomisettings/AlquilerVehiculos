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
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class ClientesController implements Initializable, MiControlador {
    
    private ObservableList<Cliente> clientes;

    @FXML
    private Label tbCliente;
    @FXML
    private Label lblNif;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApellido1;
    @FXML
    private Label lblApellido2;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<Cliente> tblCliente;
    @FXML
    private TableColumn<Cliente, String> clmNif;
    @FXML
    private TableColumn<Cliente, String> clmNombre;
    @FXML
    private TableColumn<Cliente, String> clmApellido1;
    @FXML
    private TableColumn<Cliente, String> clmApellido2;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnNuevo;

/*  @FXML
  private TextArea textClientes;

  /**
   * Initializes the controller class.
   */
 @Override
  public void initialize(URL url, ResourceBundle rb) {
   actualizar();
  }

  public List<Cliente> cargaClientes (){
      List<Cliente> cList = new ArrayList();
      Modelo modelo = Modelo.getModelo();
      Map<String, Cliente> c = modelo.getClientes();
      for (Map.Entry<String, Cliente> entry : c.entrySet()) {
          Cliente value = entry.getValue(); 
          cList.add(value);
                  
      }
      return cList;
    
}
 /*  @FXML
  private void handleBotonVolverAction(ActionEvent event) {
    try {
      GestorEscenas.getGestor().muestraMenuPrincipal();
    } catch (IOException ex) {
      Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void addText() {
    textClientes.setText(Modelo.getModelo().getClientes().toString());
  }*/

  @Override
  public void actualizar() {
      
      clientes = FXCollections.observableArrayList(cargaClientes());
      tblCliente.setItems(clientes);
      clmNombre.setCellValueFactory((datosFila) -> datosFila.getValue().getNombreProperty());
      clmApellido1.setCellValueFactory((datosFila) -> datosFila.getValue().getApellido1Property());
      clmApellido2.setCellValueFactory((datosFila) -> datosFila.getValue().getApellido2Property());
      clmNif.setCellValueFactory((datosFila) -> datosFila.getValue().getNifProperty());
      
     tblCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraCliente(newValue));
        tblCliente.getSelectionModel().select(0);
      
  }
  private void muestraCliente(Cliente cliente){
      lblNif.setText(cliente.getNif());
      lblNombre.setText(cliente.getNombre());
      lblApellido1.setText(cliente.getApellido1());
      lblApellido2.setText(cliente.getApellido2());
      
      
      
  }

    @FXML
    private void handlebtnEditar(MouseEvent event) {

    }

    @FXML
    private void handlebtnGuardar(MouseEvent event) {
 
    }

    @FXML
    private void handlebtnEliminar(MouseEvent event) {
    }

    @FXML
    private void handlebtnCancelar(MouseEvent event) {
    }

    @FXML
    private void handlebtnVolver(MouseEvent event) {
   try{
            GestorEscenas.getGestor().muestraMenuPrincipal();
        }catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    @FXML
    private void handlebtnNuevo(MouseEvent event) {
     
    }

   
}
