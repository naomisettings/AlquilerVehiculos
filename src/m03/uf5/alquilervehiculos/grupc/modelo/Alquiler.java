/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Juan
 */
public class Alquiler {

    private Cliente cliente;
    private Vehiculo vehiculo;
    private ObjectProperty<LocalDate> fechaInicio;
    private ObjectProperty<LocalDate> fechaFin;
    private int id;

    /**
     * Constructor de la clase
     *
     * @param cliente
     * @param vehiculo
     * @param fechaInicio
     * @param fechaFin
     */
    //CONSTRUCTOR PARA COMPILAR
    public Alquiler(int id, Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin)
            throws IllegalArgumentException {
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = new SimpleObjectProperty<>(fechaInicio);
        this.fechaFin = new SimpleObjectProperty<>(fechaFin);
    }
    
    public Alquiler(){
        
    }
    
    /**
     *
     * @return
     */
    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio.get();
    }

    public ObjectProperty<LocalDate> getFechaInicioProperty() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin.get();
    }

    public ObjectProperty<LocalDate> getFechaFinProperty() {
        return fechaFin;
    }

    /*
    @Override
    public String toString() {
        //return cliente+ ": "+vehiculo+ ": "+"("+fechaInicio+"-"+fechaFin+")" ;
        return cliente.getNif() + ";" + vehiculo.getMatricula() + ";" + fechaInicio + ";" + fechaFin;
    }
     */
    public int getId() {
        return id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setFechaInicio(ObjectProperty<LocalDate> fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(ObjectProperty<LocalDate> fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

}
