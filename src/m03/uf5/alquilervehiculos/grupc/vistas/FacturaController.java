/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
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
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;

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

    private void actualizar() {

        Set clientes = Modelo.getModelo().getClientes();
        Set vehiculos = Modelo.getModelo().getVehiculos();
        String textoAmostrar = "";
        String ulitmoAlquilerCliente = Modelo.getModelo().getUltimoAlquiler().getCliente();
        String ulitmoAlquilerVehiculo = Modelo.getModelo().getUltimoAlquiler().getVehiculo();

        textoAmostrar = Modelo.getModelo().getNombreEmpresa();
        textoAmostrar = textoAmostrar + "\nCif: " + Modelo.getModelo().getCif();
        textoAmostrar = textoAmostrar + "\n" + Modelo.getModelo().getDireccion();
        textoAmostrar = textoAmostrar + "\nTelf: " + Modelo.getModelo().getTelefono();

        for (Iterator it = clientes.iterator(); it.hasNext();) {
            String cliente = it.next().toString();
            String dniCliente = cliente.substring(cliente.length() - 9, cliente.length());

            if (dniCliente.equals(ulitmoAlquilerCliente)) {
                cliente = cliente.replace(";", " ");

                textoAmostrar = textoAmostrar + "\n\nDatos del cliente\n===========\n" + cliente;
            }
        }
        for (Iterator it = vehiculos.iterator(); it.hasNext();) {
            String vehiculo = it.next().toString();
            String matriculaVehiculo = vehiculo.substring(0, 7);
            if (matriculaVehiculo.equals(ulitmoAlquilerVehiculo)) {
                vehiculo = vehiculo.replace(";", " ");

                textoAmostrar = textoAmostrar + "\n\nDatos del vehículo\n===========\n" + vehiculo;
            }

        }
        txtAreaFactura.setText(textoAmostrar);

    }

  /*  private void actualizar2() {
        String clientes = Modelo.getModelo().getClientes().toString();
        String alquileres = Modelo.getModelo().getAlquileres().toString();

        System.out.println(clientes);
        clientes = clientes.replace("[", "");
        clientes = clientes.replace("]", "");

        alquileres = alquileres.replace("[", "");
        alquileres = alquileres.replace("]", "");

        int indexSeparaClienteInici = 0;
        int indexSeparaCliente = clientes.indexOf(",");
        int indexSeparaAlquiler = alquileres.indexOf(",");

        String todosClientesAlquileres = "";

        int indexDniAlquilerEncontrar = 0;
        String dniCliente = "";
        String nombreCliente = "";
        do {
            if (indexSeparaCliente != -1) {
                dniCliente = clientes.substring(indexSeparaCliente - 9, indexSeparaCliente);
                indexDniAlquilerEncontrar = alquileres.indexOf(dniCliente);
                nombreCliente = clientes.substring(indexSeparaClienteInici, indexSeparaCliente - 9);
            } else {
                dniCliente = clientes.substring(clientes.length() - 9, clientes.length());
                nombreCliente = clientes.substring(0, clientes.length() - 9);
            }

            do {
                String unAlquiler = alquileres.substring(indexDniAlquilerEncontrar, indexDniAlquilerEncontrar + 39);
                unAlquiler = unAlquiler.replace(";", " ");
                nombreCliente = nombreCliente.replace(";", " ");
                todosClientesAlquileres = todosClientesAlquileres + nombreCliente + " " + unAlquiler + "\n";

                System.out.println(nombreCliente + dniCliente + " / " + unAlquiler);

                indexSeparaAlquiler = alquileres.indexOf(",", indexSeparaAlquiler + 9);

                indexDniAlquilerEncontrar = alquileres.indexOf(dniCliente, indexDniAlquilerEncontrar + 1);
            } while (indexDniAlquilerEncontrar != -1);

            indexSeparaClienteInici = clientes.indexOf(",", indexSeparaClienteInici + 1);
            if (!(indexSeparaCliente != -1) || clientes.charAt(indexSeparaCliente) != clientes.length()) {
                indexSeparaCliente = clientes.indexOf(",", indexSeparaCliente + 1);
            }
        } while (indexSeparaCliente != - 1);
        todosClientesAlquileres = todosClientesAlquileres.replace(",", "");
        System.out.println(todosClientesAlquileres);

        txtAreaFactura.setText(todosClientesAlquileres);
    }*/

}
