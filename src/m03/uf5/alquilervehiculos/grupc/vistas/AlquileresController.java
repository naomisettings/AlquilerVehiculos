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
        String cliente = "";
        String vehiculo = "";
        String matricula = "";
        String alquiler = "";
        for (Iterator it = clientes.iterator(); it.hasNext();) {
            cliente = it.next().toString();
            cliente = cliente.replace(";", " ");

            for (Iterator it2 = vehiculos.iterator(); it2.hasNext();) {
                vehiculo = it2.next().toString();
                vehiculo = vehiculo.replace(";", "  ");

                for (Iterator it3 = alquileres.iterator(); it3.hasNext();) {
                    alquiler = it3.next().toString().substring(18);
                    alquiler = alquiler.replace(";", "-");
                    //  textoAmostrar = cliente + " " + " " + vehiculo + matricula + " "
                    //          + " (" + alquiler + ")" ;

                    //  textAlquileres.setText(textoAmostrar);
                    // System.out.println(textoAmostrar);
                }
                textoAmostrar = cliente + " " + " " + vehiculo + " "
                        + " (" + alquiler + ")";

                textAlquileres.setText(textoAmostrar);
                System.out.println(textoAmostrar);
            }

        }

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
