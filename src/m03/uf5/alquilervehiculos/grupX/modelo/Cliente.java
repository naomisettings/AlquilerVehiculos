/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupX.modelo;

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
  }

  public String getNombre() {
  }

  public String getApellido1() {
  }

  public String getApellido2() {
  }

  public String getNif() {
  }

  @Override
  public String toString() {
  }
}
