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
import m03.uf5.alquilervehiculos.grupc.vistas.MiControlador;

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
    private final static String VISTA_EDITAR_VEHICULO = "vistas/EditarVehiculo.fxml";//Raimon
    private final static String VISTA_NUEVO_VEHICULO = "vistas/NuevoVehiculo.fxml"; //Raimon 
    private final static String VISTA_ALQUILERES = "vistas/Alquileres.fxml";        //Àngels
    private final static String VISTA_NUEVO_ALQUILER = "vistas/NuevoAlquiler.fxml"; //Àngels
    private final static String VISTA_FACTURA = "vistas/Factura.fxml";              //Raimon

    private final Scene pantallaMenuPrincipal;
    private Scene pantallaVerCliente;
    private Scene pantallaNuevoCliente;
    private Scene pantallaVerVehiculo;
    private Scene pantallaEditarVehiculo;
    private Scene pantallaNuevoVehiculo;
    private Scene pantallaVerAlquiler;
    private Scene pantallaNuevoAlquiler;
    private Scene pantallaVerFactura;

    private MiControlador contraladorCliente;
    private MiControlador controladorNuevoCliente;
    private MiControlador controladorVehiculos;
    private MiControlador controladorEditarVehiculo;
    private MiControlador controladorNuevoVehiculo;
    private MiControlador controladorAlquiler;
    private MiControlador controladorNuevoAlquiler;

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

    public void muestraClientes() throws IOException {
        if (pantallaVerCliente == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VISTA_CLIENTES));
            Parent root = loader.load();
            pantallaVerCliente = new Scene(root);
            contraladorCliente = (MiControlador) loader.getController();

        }
        ventana.setScene(pantallaVerCliente);
        contraladorCliente.actualizar();
    }

    public void muestraNuevoCliente() throws IOException {
        if (pantallaNuevoCliente == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VISTA_NUEVO_CLIENTE));
            Parent root = loader.load();
            pantallaNuevoCliente = new Scene(root);
            controladorNuevoCliente = (MiControlador) loader.getController();

        }
        ventana.setScene(pantallaNuevoCliente);
        controladorNuevoCliente.actualizar();

    }

    public void muestraVehiculos() throws IOException {
        if (pantallaVerVehiculo == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VISTA_VEHICULOS));
            Parent root = loader.load();
            pantallaVerVehiculo = new Scene(root);
            controladorVehiculos = (MiControlador) loader.getController();
        }
        ventana.setScene(pantallaVerVehiculo);
        controladorVehiculos.actualizar();
    }

    public void muestraNuevoVehiculo() throws IOException {
        if (pantallaNuevoVehiculo == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VISTA_NUEVO_VEHICULO));
            Parent root = loader.load();
            pantallaNuevoVehiculo = new Scene(root);
            controladorNuevoVehiculo = (MiControlador) loader.getController();
        }
        ventana.setScene(pantallaNuevoVehiculo);
        controladorNuevoVehiculo.actualizar();
    }

    public void muestraAlquileres() throws IOException {
        if (pantallaVerAlquiler == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VISTA_ALQUILERES));
            Parent root = loader.load();
            pantallaVerAlquiler = new Scene(root);
            controladorAlquiler = (MiControlador) loader.getController();
        }
        ventana.setScene(pantallaVerAlquiler);
        controladorAlquiler.actualizar();

    }

    public void muestraNuevoAlquiler() throws IOException {
        if (pantallaNuevoAlquiler == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VISTA_NUEVO_ALQUILER));
            Parent root = loader.load();
            pantallaNuevoAlquiler = new Scene(root);
            controladorNuevoAlquiler = (MiControlador) loader.getController();
        }
        ventana.setScene(pantallaNuevoAlquiler);
        controladorNuevoAlquiler.actualizar();
    }

    public void muestraFactura() throws IOException {
        if (pantallaVerFactura == null) {
            Parent root = FXMLLoader.load(getClass().getResource(VISTA_FACTURA));
            pantallaVerFactura = new Scene(root);
        }
        ventana.setScene(pantallaVerFactura);
    }

}
