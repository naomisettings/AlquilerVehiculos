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
    private TextArea txtAreaFactura;
    private Alquiler alquiler;

   

    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        
        String factura = "";

        factura += modelo.getNombreEmpresa() + "\nCIF: " + modelo.getCif() + "\n"
                + modelo.getDireccion()+"\nTelf: " + modelo.getTelefono();

        factura += "\n--------------------------------\n";
        factura += "Datos del cliente\n";
        factura += "NIF: " + alquiler.getCliente().getNif() + "\n";
        factura += "Nombre: " + alquiler.getCliente().getNombre() + "\n";
        factura += "Apellidos: " + alquiler.getCliente().getApellido1()+" "+ alquiler.getCliente().getApellido2() + "\n";
        factura += "--------------------------------\n";
        factura += "Datos del vehiculo\n";
        factura += "Matrícula: " + alquiler.getVehiculo().getMatricula() + "\n";
        factura += "Modelo: " + alquiler.getVehiculo().getModelo() + "\n";
        factura += "--------------------------------\n";
        Period dias = Period.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
        double precio = dias.getDays() * modelo.PRECIO_DIARIO_ALQUILER;
        factura += "Total " + dias.getDays() + " días" + "\n";
        factura += "Coste Total:  " + precio + " €";
        txtAreaFactura.setText(factura);
    }

}
