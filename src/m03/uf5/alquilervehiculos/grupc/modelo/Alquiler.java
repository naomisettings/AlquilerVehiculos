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
  private String nif;
  private String vehiculo;
  private String fechaInicio;
  private String fechaFin;
  
  /**
   * Constructor de la clase
   * @param cliente
   * @param vehiculo
   * @param fechaInicio
   * @param fechaFin 
   */
  
  //CONSTRUCTOR PARA COMPILAR
  public Alquiler(String nif,String vehiculo, String fechaInicio, String fechaFin)
  throws IllegalArgumentException {
  this.nif=nif;
  this.vehiculo=vehiculo;
  this.fechaInicio = fechaInicio;
  this.fechaFin = fechaFin;
  }
    /**
     *
     * @return
     */
    public String getCliente() {
      return nif;
  }

  public String getVehiculo() {
      return vehiculo;
  }

  public String getFechaInicio() {
      return fechaInicio;
  }

  public String getFechaFin() {
      return fechaFin;
  }
  
  @Override
  public String toString() {
      //return cliente+ ": "+vehiculo+ ": "+"("+fechaInicio+"-"+fechaFin+")" ;
      return nif+ ";"+  vehiculo+ ";"+fechaInicio+";"+fechaFin;
  }

}
