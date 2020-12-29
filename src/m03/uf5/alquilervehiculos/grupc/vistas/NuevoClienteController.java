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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import static m03.uf5.alquilervehiculos.grupc.vistas.ClientesController.enviarCliente;

/**
 * FXML Controller class
 *
 * @author angels
 */
public class NuevoClienteController implements Initializable, MiControlador {

    protected static Cliente nuevoEnviaCliente;
    private Cliente cliente;

    @FXML
    private TextField textoNombre;
    @FXML
    private TextField textoApellido1;
    @FXML
    private TextField textoApellido2;
    @FXML
    private TextField textoNif;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();

    }

  /*  public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente != null) {
            textoNombre.setText(cliente.getNombre());
            textoApellido1.setText(cliente.getApellido1());
            textoApellido2.setText(cliente.getApellido2());
            textoNif.setText(cliente.getNif());
        } else {
            this.cliente = new Cliente();
        }

    }*/

    private void handleBotonVolverAction(ActionEvent event) {
        try {
            GestorEscenas.getGestor().muestraClientes();

        } catch (IOException ex) {
            Logger.getLogger(NuevoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar() {
        if (enviarCliente == null) {
            textoNombre.setText("");
        } else {
            textoNombre.setText(ClientesController.enviarCliente.getNombre());
            textoNombre.setText(ClientesController.enviarCliente.getNombre());
            textoApellido1.setText(ClientesController.enviarCliente.getApellido1());
            textoApellido2.setText(ClientesController.enviarCliente.getApellido2());
            textoNif.setText(ClientesController.enviarCliente.getNif());
        }
    }

    @FXML
    private void handleBtnGuardar(MouseEvent event) {

        Cliente cliente = new Cliente();
        System.out.println("Cliente a guardar " + textoNombre.getText() + textoApellido1.getText() + textoApellido2.getText() + textoNif.getText());
        System.out.println("clienteadd " + cliente);
        boolean camposValidos = cliente.comprobarCampos(textoNombre.getText().isEmpty(),
                textoApellido1.getText().isEmpty(), textoApellido2.getText().isEmpty(),
                textoNif.getText().isEmpty());
        if (!camposValidos) {
            boolean nifCorrecto = cliente.validaNif(textoNif.getText());
            if (nifCorrecto) {
                Cliente enviaCliente;
                enviaCliente = ClientesController.enviarCliente;
                cliente.setNombre(textoNombre.getText());
                cliente.setApellido1(textoApellido1.getText());
                cliente.setApellido2(textoApellido2.getText());
                cliente.setNif(textoNif.getText());

                Modelo.getModelo().addCliente(cliente);

                nuevoEnviaCliente = cliente;
                System.out.println("Cliente a guardar " + textoNombre.getText() + textoApellido1.getText() + textoApellido2.getText() + textoNif.getText());

            }
        }

    }

    @FXML
    private void handlebtnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraClientes();

        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
