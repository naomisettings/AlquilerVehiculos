/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

import java.time.LocalDate;

/**
 *
 * @author Juan
 */
public class Alquiler {
  private Cliente cliente;
  private Vehiculo vehiculo;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  
  /**
   * Constructor de la clase
   * @param cliente
   * @param vehiculo
   * @param fechaInicio
   * @param fechaFin 
   */
  
  //CONSTRUCTOR PARA COMPILAR
  public Alquiler(){}
  //ATENCIÓN
  
  
 /* public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio,LocalDate fechaFin){
     
      
  }
          

  public Cliente getCliente() {
  }

  public Vehiculo getVehiculo() {
  }*/

  public LocalDate getFechaInicio() {
      return fechaInicio;
  }

  public LocalDate getFechaFin() {
      return fechaFin;
  }

 /* @Override
  public String toString() {
  }
*/
}
