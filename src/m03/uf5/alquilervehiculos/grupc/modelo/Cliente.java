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
public class Cliente {
  private StringProperty nombre;
  private StringProperty apellido1;
  private StringProperty apellido2;
  private StringProperty nif;

  /**
   * Constructor de la clase
   * @param nombre
   * @param apellido1
   * @param apellido2
   * @param nif
   * @throws IllegalArgumentException en caso de que el formato del NIF no sea correcto
   */
  
  
public Cliente(){
    this.nombre = new SimpleStringProperty();
    this.apellido1 = new SimpleStringProperty();
    this.apellido2 = new SimpleStringProperty();
    this.nif = new SimpleStringProperty();
        
}
  
  
  public Cliente(String nombre, String apellido1, String apellido2, String nif) 
    throws IllegalArgumentException {
    this.nombre.set(nombre);
    this.apellido1.set(apellido1);
    this.apellido2.set(apellido2);
    this.nif.set(nif);
  }

  public String getNombre() {
    return nombre.get();
  }
  
  public StringProperty getNombreProperty(){
      return nombre;
  }

  public String getApellido1() {
    return apellido1.get();
  }
  
  public StringProperty getApellido1Property(){
      return apellido1;
  }

  public String getApellido2() {
    return apellido2.get();
  }

  public StringProperty getApellido2Property(){
    return apellido2;  
  }
  public String getNif() {
    return nif.get();
  }
  
  public StringProperty getNifProperty(){
      return nif;
  }
  
  public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
  
  public void setApellido1(String apellido1){
      this.apellido1.set(apellido1);
  }
  
  public void setApellido2(String apellido2){
      this.apellido2.set(apellido2);
  }
  
  public void setNif(String nif){
      this.nif.set(nif);
  }

 

  @Override
  public String toString() {
    return nombre+";"+apellido1+";"+apellido2+";"+nif;
  }

     

 

  

}
