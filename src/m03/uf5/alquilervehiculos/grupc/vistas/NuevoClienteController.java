/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;

/**
 * FXML Controller class
 *
 * @author angels
 */
public class NuevoClienteController implements Initializable, MiControlador {

   
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
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();

    }

    public Cliente getCliente() { //recuperar el client que s'ha editat a la pantalla
        return cliente;
    }

    public void setCliente(Cliente cliente) { //li passo al controlador el client que es modificara
        this.cliente = cliente;

        if (cliente != null) {

            textoNombre.setText("" + cliente.getNombre());

            textoApellido1.setText("" + cliente.getApellido1());
            textoApellido2.setText("" + cliente.getApellido2());
            textoNif.setText("" + cliente.getNif());
        } else {
            this.cliente = new Cliente(); // si les dades passades es un null crea un client nou
        }

    }

    @Override
    public void actualizar() {

    }

    @FXML
    private void handleBtnGuardar(MouseEvent event) {

        Cliente c = new Cliente();
        String guardaNif = textoNif.getText();
        String guardaNombre = textoNombre.getText();
        String guardaApellido1 = textoApellido1.getText();
        String guardaApellido2 = textoApellido2.getText();

        if (c.comprobarCampos(guardaNif.isEmpty(), guardaNombre.isEmpty(),
                guardaApellido1.isEmpty(), guardaApellido2.isEmpty())) {

            if (guardaNif.equals(cliente.getNif()) || (c.nifRepetido(guardaNif))) {

                if (c.validaNif(textoNif.getText())) {
                    cliente.setNif(guardaNif);
                    cliente.setNombre(guardaNombre);
                    cliente.setApellido1(guardaApellido1);
                    cliente.setApellido2(guardaApellido2);

                    Stage ventana = (Stage) btnGuardar.getScene().getWindow();
                    ventana.close();

                } else {
                    textoNif.setText("");
                    textoNombre.setText("");
                    textoApellido1.setText("");
                    textoApellido2.setText("");
                }
            } else {
                textoNif.setText("");
            }
        }

    }

    @FXML
    private void handleBtnCancelar(ActionEvent event) {  //pone el cliente a null y cierra la ventana
        cliente = null;
        Stage ventana = (Stage) btnGuardar.getScene().getWindow();
        ventana.close();
    }

}
