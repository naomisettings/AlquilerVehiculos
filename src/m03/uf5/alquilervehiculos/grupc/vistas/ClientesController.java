/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class ClientesController implements Initializable {

  @FXML
  private TextArea textClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textClientes.setText(Modelo.getModelo().getClientes().toString());
    }    

  @FXML
  private void handleBotonVolverAction(ActionEvent event) {
    try {
      GestorEscenas.getGestor().muestraMenuPrincipal();
    } catch (IOException ex) {
      Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
    
}
