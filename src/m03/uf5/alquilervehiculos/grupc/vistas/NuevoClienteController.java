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
import javafx.scene.control.TextField;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class NuevoClienteController implements Initializable {

    public static final String TABLA_LETRA = "TRWAGMYFPDXBNJZSQVHLCKE";

    @FXML
    private TextField textoNombre;
    @FXML
    private TextField textoApellido1;
    @FXML
    private TextField textoApellido2;
    @FXML
    private TextField textoNif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBotonGuardarAction(ActionEvent event) {
        if (comprobarCampos() && validaNIF()) {
            //Cliente cliente = new Cliente(textoNombre.getText(), textoApellido1.getText(),
            //textoApellido2.getText(), textoNif.getText());
            //Modelo.getModelo().addCliente(cliente);
        }
    }

    @FXML
    private void handleBotonVolverAction(ActionEvent event) {
        try {
            GestorEscenas.getGestor().muestraMenuPrincipal();
            textoApellido1.setText("");
            textoApellido2.setText("");
            textoNif.setText("");
            textoNombre.setText("");
        } catch (IOException ex) {
            Logger.getLogger(NuevoClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean comprobarCampos() {
        if (textoNombre.getText().isEmpty() || textoApellido1.getText().isEmpty()
                || textoApellido2.getText().isEmpty() || textoNif.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alquiler de vheiculos");
            alert.setHeaderText("Campo Vacio");
            alert.setContentText("Todos los campos deben estar completos");

            alert.showAndWait();
            return false;
        } else {
            Pattern reglas = Pattern.compile("[A-Za-z]*");
            Matcher[] textAnalizar = {reglas.matcher(textoNombre.getText()),
                reglas.matcher(textoApellido1.getText()), reglas.matcher(textoApellido2.getText())};
            for (int i = 0; i < textAnalizar.length; i++) {
                if (!textAnalizar[i].matches()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Alquiler de vheiculos");
                    alert.setHeaderText("Formato Incorrecto");
                    alert.setContentText("");

                    alert.showAndWait();
                    return false;
                }
            }
            return true;
        }
    }

    private boolean validaNIF() {
        String nif = textoNif.getText();
        Pattern reglas = Pattern.compile("[0-9]{8}[A-Z]");
        Matcher textAnalitzar = reglas.matcher(nif);

        if (textAnalitzar.matches()) {
            int dni = Integer.parseInt(nif.substring(0, 8));
            char lletra = TABLA_LETRA.charAt(dni % 23);
            if (nif.charAt(nif.length() - 1) == lletra) {
                return true;
            }
        }

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Alquiler de vheiculos");
        alert.setHeaderText("NIF incorrecto");
        alert.setContentText("");

        alert.showAndWait();
        return false;
    }

}
