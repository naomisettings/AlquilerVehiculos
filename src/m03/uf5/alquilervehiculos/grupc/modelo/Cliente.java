/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

/**
 *
 * @author Juan
 */
public class Cliente {
  private String nombre;
  private String apellido1;
  private String apellido2;
  private String nif;

  /**
   * Constructor de la clase
   * @param nombre
   * @param apellido1
   * @param apellido2
   * @param nif
   * @throws IllegalArgumentException en caso de que el formato del NIF no sea correcto
   */
  
  
  
  
  
  public Cliente(String nombre, String apellido1, String apellido2, String nif) 
    throws IllegalArgumentException {
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.nif = nif;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido1() {
    return apellido1;
  }

  public String getApellido2() {
    return apellido2;
  }

  public String getNif() {
    return nif;
  }

  @Override
  public String toString() {
    return nombre+";"+apellido1+";"+apellido2+";"+nif;
  }

 

  

}
