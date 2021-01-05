/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Angels
 */
public class Factura {
   // private Cliente cliente;
   // private Vehiculo vehiculo;
   // private ObjectProperty<LocalDate> fechaInicio;
   // private ObjectProperty<LocalDate> fechaFin;
    private int id;
    private StringProperty nombre;
    private StringProperty apellido1;
    private StringProperty apellido2;
    private StringProperty nif;
   private ObjectProperty<LocalDate> fechaInicio;
    private ObjectProperty<LocalDate> fechaFin;
    private StringProperty nDias;
    private StringProperty precio;
    private StringProperty total;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Alquiler alquileres;
    
    


    public Factura(int id, Cliente cliente, Vehiculo vehiculo, Alquiler alquileres,
            StringProperty nDias, StringProperty precio,
            StringProperty total) {
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.alquileres = alquileres;
        this.nDias = nDias;
        this.precio = precio;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringProperty getNombre() {
        return nombre;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public StringProperty getApellido1() {
        return apellido1;
    }

    public void setApellido1(StringProperty apellido1) {
        this.apellido1 = apellido1;
    }

    public StringProperty getApellido2() {
        return apellido2;
    }

    public void setApellido2(StringProperty apellido2) {
        this.apellido2 = apellido2;
    }

    public StringProperty getNif() {
        return nif;
    }

    public void setNif(StringProperty nif) {
        this.nif = nif;
    }

    public ObjectProperty<LocalDate> getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(ObjectProperty<LocalDate> fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ObjectProperty<LocalDate> getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(ObjectProperty<LocalDate> fechaFin) {
        this.fechaFin = fechaFin;
    }


    

    public StringProperty getnDias() {
        return nDias;
    }

    public void setnDias(StringProperty nDias) {
        this.nDias = nDias;
    }

    public StringProperty getPrecio() {
        return precio;
    }

    public void setPrecio(StringProperty precio) {
        this.precio = precio;
    }

    public StringProperty getTotal() {
        return total;
    }

    public void setTotal(StringProperty total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Alquiler getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(Alquiler alquileres) {
        this.alquileres = alquileres;
    }
    public Factura(){
        
    }

   

    
    
}


