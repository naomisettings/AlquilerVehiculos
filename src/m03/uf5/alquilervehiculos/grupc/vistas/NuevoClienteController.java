/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();

    }

    public Cliente getCliente() { //recuperar el cliente que se ha editado en la pantalla
        return cliente;
    }

    public void setCliente(Cliente cliente) { //se le pasa al controlador el cliente que se va a modificar
        this.cliente = cliente;

        if (cliente != null) {

            textoNombre.setText("" + cliente.getNombre());

            textoApellido1.setText("" + cliente.getApellido1());
            textoApellido2.setText("" + cliente.getApellido2());
            textoNif.setText("" + cliente.getNif());
        } else {
            this.cliente = new Cliente(); // si los datos pasados es un null crea un cliente nuevo
        }

    }

    @Override
    public void actualizar() {

    }


    @FXML
    private void handleBtnGuardar(MouseEvent event) {
                    
        boolean camposValidos = cliente.comprobarCampos(textoNombre.getText().isEmpty(),
                textoApellido1.getText().isEmpty(), textoApellido2.getText().isEmpty(),
                textoNif.getText().isEmpty());
        if (!camposValidos) {


                boolean nifCorrecto = cliente.validaNif(textoNif.getText());
                if (nifCorrecto) {

                    cliente.setNombre(textoNombre.getText());
                    cliente.setApellido1(textoApellido1.getText());
                    cliente.setApellido2(textoApellido2.getText());
                    cliente.setNif(textoNif.getText());

                    //para cerrar la ventana
                    Stage ventana = (Stage) btnGuardar.getScene().getWindow();
                    ventana.close();
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
