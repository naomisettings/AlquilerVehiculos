/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
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
    }

}
