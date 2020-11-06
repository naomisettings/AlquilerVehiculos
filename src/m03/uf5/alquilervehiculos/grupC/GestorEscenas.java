/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupC;

import java.io.IOException;
import javafx.stage.Stage;

/**
 *
 * @author Juan
 */
public class GestorEscenas {

  private static GestorEscenas gestor;
  private final Stage ventana;
  
  private final static String VISTA_MENU_PRINCIPAL = "vistas/MenuPrincipal.fxml"; //Raimon
  private final static String VISTA_CLIENTES = "vistas/Clientes.fxml";            //Pau
  private final static String VISTA_NUEVO_CLIENTE = "vistas/NuevoCliente.fxml";   //Pau
  private final static String VISTA_VEHICULOS = "vistas/Vehiculos.fxml";          //Pau
  private final static String VISTA_NUEVO_VEHICULO = "vistas/NuevoVehiculo.fxml"; //Raimon 
  private final static String VISTA_ALQUILERES = "vistas/Alquileres.fxml";        //Àngels
  private final static String VISTA_NUEVO_ALQUILER = "vistas/NuevoAlquiler.fxml"; //Àngels
  private final static String VISTA_FACTURA = "vistas/Factura.fxml";              //Raimon

  /**
   * Constructor privado de la clase. Crea todas las escenas y muestra la del menu principal
   * @param stage la ventana de la aplicación
   * @throws IOException 
   */
  private GestorEscenas(Stage stage) throws IOException{

  }
  
  /**
   * Método que devuelve la instancia singleton de GestorEscenas
   * @return 
   */
  public static GestorEscenas getGestor(){

  }

  /**
   * Inicializa el singleton
   * @param stage la ventana de la aplicación
   * @throws IOException 
   */
  public static void inicializar(Stage stage) throws IOException{

  }

  public void muestraMenuPrincipal() {
      
  }
  
  public void muestraClientes(){
  }
  
  public void muestraNuevoCliente(){
  }
  
  public void muestraVehiculos(){
  }
  
  public void muestraNuevoVehiculo(){
  }
  
  public void muestraAlquileres(){
  }
  
  public void muestraNuevoAlquiler(){
  }
  
  public void muestraFactura(){
  }
}
