/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Juan
 */
public class Vehiculo {

    private StringProperty matricula;
    private StringProperty modelo;

    /**
     * Constructor de la clase.
     *
     * @param matricula
     * @param modelo
     * @throws IllegalArgumentException en caso de que el formato de la
     * matrícula no sea correcto:
     * https://es.wikipedia.org/wiki/Matr%C3%ADculas_automovil%C3%ADsticas_de_Espa%C3%B1a#Sistema_actual
     */
    public Vehiculo() {
        this.matricula = new SimpleStringProperty();
        this.modelo = new SimpleStringProperty();
    }

    public Vehiculo(String matricula, String modelo) {
        this.matricula.set(matricula);
        this.modelo.set(modelo);
    }

    public String getMatricula() {
        return matricula.get();
    }

    public StringProperty getMatriculaProperty() {
        return matricula;
    }

    public String getModelo() {
        return modelo.get();
    }

    public StringProperty getModeloProperty() {
        return modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula.set(matricula);
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    @Override
    public String toString() {
        return matricula + ";" + modelo;
    }

    public Boolean validaMatricula(String matricula) {
        Pattern reglas = Pattern.compile("[0-9]{4}[[A-Z]&&[^AEIOUaeiou]]{3}");
        Matcher matriculaAnalitzar = reglas.matcher(matricula);
        if (!matriculaAnalitzar.matches()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText("Vehículo no introducido");
            alert.setContentText("La matríucula debe estar en el formato correcto"
                    + " y en mayúsculas");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                // ... user chose OK
            } else {
                // ... user chose CANCEL or closed the dialog
            }
            return false;
        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText(null);
            alert.setContentText("Vehículo introducido correctamente");

            alert.showAndWait();
            return true;
        }
    }

    public boolean camposEmplenados(Boolean matricula, Boolean modelo) {

        if (matricula && modelo) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText("Vehículo no introducido");
            alert.setContentText("Todos los campos deben estar completos");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                // ... user chose OK
            } else {
                // ... user chose CANCEL or closed the dialog
            }
            return false;

        } else {
            return true;
        }
    }

    public boolean matriculaRepetida(ObservableList<Vehiculo> vehiculos, String matricula) {
        System.out.println(matricula);
        System.out.println(vehiculos);
        
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println("bucle Matricula " + vehiculo.getMatricula());
            if (vehiculo.getMatricula().equals(matricula)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alquiler de Vehículos");
                alert.setHeaderText("Vehículo no introducido");
                alert.setContentText("La matrícula no puede estar repetida");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
                return false;
            } 
        }
        return true;
    }
}
