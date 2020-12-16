/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import m03.uf5.alquilervehiculos.grupc.modelo.Alquiler;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class AlquileresController implements Initializable, MiControlador {

  @FXML
  private TextArea textAlquileres;

  @Override
  public void actualizar() {
    Modelo modelo = Modelo.getModelo();
    List<Alquiler> alquileres = modelo.getAlquileres();
    String texto = "";
    for (Alquiler a : alquileres) {
      texto += a.toString() + "\n";
    }
    textAlquileres.setText(texto);

  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    actualizar();
  }

  @FXML
  private void handleBottonVolver(ActionEvent event
  ) {
    try {
      GestorEscenas.getGestor().muestraMenuPrincipal();
    } catch (IOException ex) {
      Logger.getLogger(AlquileresController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void addText() {
    textAlquileres.setText(Modelo.getModelo().getAlquileres().toString());
  }

}
