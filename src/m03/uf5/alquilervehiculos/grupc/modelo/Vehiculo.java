/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
}
