/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import java.util.Set;
import m03.uf5.alquilervehiculos.grupc.modelo.Alquiler;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Vehiculo;

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
        Set clientes = Modelo.getModelo().getClientes();
        Set vehiculos = Modelo.getModelo().getVehiculos();
        Set alquileres = Modelo.getModelo().getAlquileres();
        String textoAmostrar = "";
        Cliente cliente = null;
        Vehiculo vehiculo = null;
        Alquiler alquiler = null;

        for (Iterator it = clientes.iterator(); it.hasNext();) {
            cliente = (Cliente) it.next();

            for (Iterator it3 = alquileres.iterator(); it3.hasNext();) {

                alquiler = (Alquiler) it3.next();
                if (alquiler.getCliente().getNif().equals(cliente.getNif())) {
                    for (Iterator it2 = vehiculos.iterator(); it2.hasNext();) {
                        vehiculo = (Vehiculo) it2.next();
                        if (vehiculo.getMatricula().equals(alquiler.getVehiculo().getMatricula())) {
                            textoAmostrar += cliente.toString().replace(";", " ")+" " + vehiculo.toString().replace(";", " ") + " ( "
                                    + alquiler.getFechaInicio().toString().replace(";", " ") + "-"
                                    + alquiler.getFechaFin().toString().replace(";", " ") + " )"+ "\n";
                        }

                    }
                }
            }
        }

        textAlquileres.setText(textoAmostrar);
        System.out.println(textoAmostrar);
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
