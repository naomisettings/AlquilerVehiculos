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
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Alquiler;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import m03.uf5.alquilervehiculos.grupc.modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class FacturaController implements Initializable {

    @FXML
    private Button bttnVolver;
    @FXML
    private Button bttnMenuPrincipal;
    @FXML
    private TextArea txtAreaFactura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();
    }

    @FXML
    private void handleBttnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraNuevoAlquiler();
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
    public int obtenerDias() {
        Set<Alquiler> alquil = Modelo.getModelo().getAlquileres();
        Alquiler alquiler = null;
        LocalDate inicio = null;
        LocalDate fin = null;
        
        for (Iterator it = alquil.iterator(); it.hasNext(); ) {
            alquiler = (Alquiler)it.next();
            inicio = alquiler.getFechaInicio();
            fin = alquiler.getFechaFin();
        }
        Period periodo = Period.between(inicio, fin);
        int diasTotales = periodo.getDays();
        return diasTotales;
        
    }
    private void actualizar() {
        Set clientes = Modelo.getModelo().getClientes();
        Set vehiculos = Modelo.getModelo().getVehiculos();
        String textoAmostrar = "";
        Cliente ulitmoAlquilerCliente = Modelo.getModelo().getUltimoAlquiler().getCliente();
        Vehiculo ulitmoAlquilerVehiculo = Modelo.getModelo().getUltimoAlquiler().getVehiculo();

        textoAmostrar = Modelo.getModelo().getNombreEmpresa();
        textoAmostrar = textoAmostrar + "\nCif: " + Modelo.getModelo().getCif();
        textoAmostrar = textoAmostrar + "\n" + Modelo.getModelo().getDireccion();
        textoAmostrar = textoAmostrar + "\nTelf: " + Modelo.getModelo().getTelefono();

        for (Iterator it = clientes.iterator(); it.hasNext();) {
            String cliente = it.next().toString();
            String dniCliente = cliente.substring(cliente.length() - 9, cliente.length());

            if (dniCliente.equals(ulitmoAlquilerCliente.getNif())) {
                cliente = cliente.replace(";", " ");

                textoAmostrar = textoAmostrar + "\n\nDatos del cliente\n===========\n" + cliente;
            }
        }
        for (Iterator it = vehiculos.iterator(); it.hasNext();) {
            String vehiculo = it.next().toString();
            String matriculaVehiculo = vehiculo.substring(0, 7);
            if (matriculaVehiculo.equals(ulitmoAlquilerVehiculo.getMatricula())) {
                vehiculo = vehiculo.replace(";", " ");

                textoAmostrar = textoAmostrar + "\n\nDatos del vehículo\n===========\n" + vehiculo;
            }
        }
        int dias = obtenerDias();
        textoAmostrar += "\n\nPrecio i dias\n===========";
        textoAmostrar += "\n El precio del Alquiler és de: " + dias * 40 + " € por " + dias + " dias.";
        
        txtAreaFactura.setText(textoAmostrar);

    }
}
