/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private final Scene pantallaMenuPrincipal;
    private Scene pantallaVerCliente;
    private Scene pantallaNuevoCliente;
    private Scene pantallaVerVehiculo;
    private Scene pantallaNuevoVehiculo;
    private Scene pantallaVerAlquiler;
    private Scene pantallaNuevoAlquiler;

    /**
     * Constructor privado de la clase. Crea todas las escenas y muestra la del
     * menu principal
     *
     * @param stage la ventana de la aplicación
     * @throws IOException
     */
    private GestorEscenas(Stage stage) throws IOException {
        this.ventana = stage;
        Parent root = FXMLLoader.load(getClass().getResource(VISTA_MENU_PRINCIPAL));
        Scene scene = new Scene(root);
        pantallaMenuPrincipal = scene;
        stage.setTitle("Alquiler de Vehículos");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método que devuelve la instancia singleton de GestorEscenas
     *
     * @return
     */
    public static GestorEscenas getGestor() {
        if (gestor == null) {
            throw new RuntimeException("ERROR: Gestor no inicializado");
        }
        return gestor;
    }

    /**
     * Inicializa el singleton
     *
     * @param stage la ventana de la aplicación
     * @throws IOException
     */
    public static void inicializar(Stage stage) throws IOException {
        if (gestor == null) {
            gestor = new GestorEscenas(stage);
        }
    }

    public void muestraMenuPrincipal() throws IOException {
        ventana.setScene(pantallaMenuPrincipal);
    }

    
    public void muestraClientes()  throws IOException{
        if (pantallaVerCliente == null) {
            Parent root = FXMLLoader.load(getClass().getResource(VISTA_CLIENTES));
            pantallaVerCliente = new Scene(root);
        }
        ventana.setScene(pantallaVerCliente);
    }

    public void muestraNuevoCliente()  throws IOException{
        if (pantallaNuevoCliente == null) {
            Parent root = FXMLLoader.load(getClass().getResource(VISTA_NUEVO_CLIENTE));
            pantallaNuevoCliente = new Scene(root);
        }
        ventana.setScene(pantallaNuevoCliente);
    }

    public void muestraVehiculos()  throws IOException{
        if (pantallaVerVehiculo == null) {
            Parent root = FXMLLoader.load(getClass().getResource(VISTA_VEHICULOS));
            pantallaVerVehiculo = new Scene(root);
        }
        ventana.setScene(pantallaVerVehiculo);
    }
   
    public void muestraNuevoVehiculo() throws IOException {
        if (pantallaNuevoVehiculo == null) {
            Parent root = FXMLLoader.load(getClass().getResource(VISTA_NUEVO_VEHICULO));
            pantallaNuevoVehiculo = new Scene(root);
        }
        ventana.setScene(pantallaNuevoVehiculo);
    }

    public void muestraAlquileres()  throws IOException{
        if (pantallaVerAlquiler == null) {
            Parent root = FXMLLoader.load(getClass().getResource(VISTA_ALQUILERES));
            pantallaVerAlquiler = new Scene(root);
        }
        ventana.setScene(pantallaVerAlquiler);
    }

    public void muestraNuevoAlquiler()  throws IOException{
        if (pantallaNuevoAlquiler == null) {
            Parent root = FXMLLoader.load(getClass().getResource(VISTA_NUEVO_ALQUILER));
            pantallaNuevoAlquiler = new Scene(root);
        }
        ventana.setScene(pantallaNuevoAlquiler);
    }

    public void muestraFactura()  throws IOException{
        if (pantallaVerCliente == null) {
            Parent root = FXMLLoader.load(getClass().getResource(VISTA_FACTURA));
            pantallaVerCliente = new Scene(root);
        }
        ventana.setScene(pantallaVerCliente);
    }
  
}
