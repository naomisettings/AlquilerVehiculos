/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import static m03.uf5.alquilervehiculos.grupc.vistas.NuevoClienteController.TABLA_LETRA;

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

    /*private boolean validaNIF() {
        String nif = textoNif.getText();
        Pattern reglas = Pattern.compile("[0-9]{8}[A-Z]");
        Matcher textAnalitzar = reglas.matcher(nif);

        if (textAnalitzar.matches()) {
            int dni = Integer.parseInt(nif.substring(0, 8));
            char lletra = TABLA_LETRA.charAt(dni % 23);
            if (nif.charAt(nif.length() - 1) == lletra) {
                return true;
            }
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alquiler de vheiculos");
        alert.setHeaderText(null);
        alert.setContentText("NIF incorrecto");

        alert.showAndWait();
        return false;
    }*/

  @Override
  public String toString() {
    return nombre+";"+apellido1+";"+apellido2+";"+nif;
  }

     

 

  

}
