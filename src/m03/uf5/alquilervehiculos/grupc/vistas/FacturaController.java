/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Alquiler;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class FacturaController implements Initializable, MiControlador {

    @FXML
    private Button bttnVolver;
    @FXML
    private Button bttnMenuPrincipal;
    @FXML
    private TextArea txtAreaFactura;
    private Alquiler alquiler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //actualizar();
    }

    public void getAlquiler() {

    }

    public void setAlquiler(Alquiler alquiler) {
        
        this.alquiler = alquiler;
    }

    @FXML
    private void handleBttnVolver(MouseEvent event) {
        Stage ventana = (Stage) bttnVolver.getScene().getWindow();
        ventana.close();
    }

    @FXML
    private void handleBttnMenuPrinicpal(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraMenuPrincipal();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizar() {

        Modelo modelo = Modelo.getModelo();
        System.out.println(alquiler.getFechaFin());
        System.out.println(alquiler.getCliente().getApellido1());

        String factura = "";

        factura += modelo.getNombreEmpresa() + "\nCIF: " + modelo.getCif() + "\n"
                + modelo.getDireccion() + "\nTelf: " + modelo.getTelefono();

        factura += "\n--------------------------------\n";
        factura += "Datos del cliente:\n";
        factura += alquiler.getCliente().getNif() + "\n";
        factura += "--------------------------------\n";
        factura += "Datos del vehiculo:\n";
        factura += alquiler.getVehiculo().getMatricula() + "\n";
        factura += "--------------------------------\n";
        Period dias = Period.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
        double precio = dias.getDays() * modelo.PRECIO_DIARIO_ALQUILER;
        factura += "Coste Total: " + precio + " €";
        txtAreaFactura.setText(factura);
    }

}
