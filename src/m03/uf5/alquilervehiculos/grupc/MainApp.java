/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc;

import javafx.application.Application;
import javafx.stage.Stage;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;

/**
 *
 * @author Juan
 */
public class MainApp extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    Modelo.inicializar("12345678A", "931231231", "Alquileres Copèrnic", "Torrent del Batlle 10, 08225 Terrassa");
    GestorEscenas.inicializar(stage);
   
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
