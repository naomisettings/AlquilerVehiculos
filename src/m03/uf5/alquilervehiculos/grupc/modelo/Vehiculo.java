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
public class Vehiculo {
  private String matricula;
  private String modelo;

  /**
   * Constructor de la clase. 
   * @param matricula
   * @param modelo
   * @throws IllegalArgumentException en caso de que el formato de la matrícula 
   * no sea correcto: https://es.wikipedia.org/wiki/Matr%C3%ADculas_automovil%C3%ADsticas_de_Espa%C3%B1a#Sistema_actual
   */

  public Vehiculo(String matricula, String modelo) throws IllegalArgumentException{
      this.matricula = matricula;
      this.modelo = modelo;
  }

  public String getMatricula() {
      return matricula;
  }

  public String getModelo() {
      return modelo;
  }

  @Override
  public String toString() {
      return matricula + ";" + modelo;
  }
}
