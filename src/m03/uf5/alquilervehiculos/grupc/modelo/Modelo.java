/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class Modelo {

  // Instancia singleton
  private static Modelo modelo;
  // Datos de la empresa
  private final String cif;
  private final String nombreEmpresa;
  private final String direccion;
  private final String telefono;
  // Nombre de los archivos de datos
  private static final String ARCHIVO_CLIENTES = "clientes.csv";
  private static final String ARCHIVO_VEHICULOS = "vehiculos.csv";
  private static final String ARCHIVO_ALQUILERES = "alquileres.csv";
  // Precio diario del alquiler
  public static final double PRECIO_DIARIO_ALQUILER = 75;
  // Colecciones donde se almanenan los datos de la aplicación
  private Set<Cliente> clientes = new HashSet<>();
  private Set<Vehiculo> vehiculos = new HashSet<>();
  private Set<Alquiler> alquileres = new HashSet<>();
  // Último alquiler realizado
  private Alquiler ultimoAlquiler;

  /**
   * Constructor privado de la clase. Es llamado desde el método inicializar.
   *
   * @param cif
   * @param nombreEmpresa
   * @param direccion
   * @param telefono
   */
  private Modelo(String cif, String nombreEmpresa, String direccion, String telefono) {
    this.cif = cif;
    this.nombreEmpresa = nombreEmpresa;
    this.direccion = direccion;
    this.telefono = telefono;
  }

  /**
   * Método que lee el archivo CSV con los datos de los clientes y lo carga en
   * la colección correspondiente
   *
   * @param ficheroClientes
   */
  private void cargaClientes(String ficheroClientes) {
      try {
          File fichero = new File(ficheroClientes);
          Scanner sc = new Scanner(fichero);
          while(sc.hasNext()){
              String[] datos = sc.nextLine().split(";");
              Cliente cliente = new Cliente(datos[0], datos[1], datos[2], datos[3]);
              clientes.add(cliente);
          }
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  /**
   * Método que lee el archivo CSV con los datos de los vehículos y lo carga en
   * la colección correspondiente
   *
   * @param ficheroVehiculos
   */
  private void cargaVehiculos(String ficheroVehiculos) {
      try {
          File fichero = new File(ficheroVehiculos);
          Scanner sc = new Scanner(fichero);
          while(sc.hasNext()){
              String[] datos = sc.nextLine().split(";");
              Vehiculo v = new Vehiculo(datos[0], datos[1]);
              vehiculos.add(v);
          }
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  /**
   * Método que lee el archivo CSV con los datos de los alquileres realizados y
   * lo carga en la colección correspondiente
   *
   * @param ficheroAlquileres
   */
  private void cargaAlquileres(String ficheroAlquileres) {
      try {
          File fichero = new File(ficheroAlquileres);
          Scanner sc = new Scanner(fichero);
          while(sc.hasNext()){
              String[] datos = sc.nextLine().split(";");
              Alquiler a = new Alquiler(datos[0],datos[1],datos[2],datos[3]);
              alquileres.add(a);
          }
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  /**
   * Método estático que inicializa el singleton, llamando al constructor
   * privado
   *
   * @param cif
   * @param telefono
   * @param nombreEmpresa
   * @param direccion
   */
  public static void inicializar(String cif, String telefono, String nombreEmpresa, String direccion) {
    if (modelo == null) {
        modelo = new Modelo(cif, nombreEmpresa, direccion, telefono);
        modelo.cargaClientes(ARCHIVO_CLIENTES);
        modelo.cargaAlquileres(ARCHIVO_ALQUILERES);
        modelo.cargaVehiculos(ARCHIVO_VEHICULOS);
    }
  }

  /**
   * Método que devuelve el singleton de la clase
   *
   * @return la instancia del singleton
   * @throws RuntimeException en caso de que la clase no se haya inicializado
   * previamente
   */
  public static Modelo getModelo() throws RuntimeException {
    if(modelo == null){
      throw(new RuntimeException("modelo no esta inicializado"));
    }else{
      return modelo;
    }
  }

  /**
   *
   * @return la colección de clientes dados de alta
   */
  public Set getClientes() {
    return clientes;
  }

  /**
   *
   * @return la colección de vehículos disponibles
   */
  public Set getVehiculos() {
    return vehiculos;
  }

  /**
   *
   * @return la colección de alquileres realizados
   */
  public Set getAlquileres() {
    return alquileres;
  }

  /**
   * Añade un nuevo cliente a la colección y lo guarda en el archivo CSV
   * correspondiente
   *
   * @param cliente
   */
  public void addCliente(Cliente cliente) {
      try {
          PrintStream out = new PrintStream(new FileOutputStream(ARCHIVO_CLIENTES, true));
          out.println(cliente.toString());
          clientes.add(cliente);
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  /**
   * Añade un nuevo vehiculo a la colección y lo guarda en el archivo CSV
   * correspondiente
   *
   * @param vehiculo
   */
  public void addVehiculo(Vehiculo vehiculo) {
      try {
          PrintStream out = new PrintStream(new FileOutputStream(ARCHIVO_VEHICULOS, true));
          out.println(vehiculo.toString());
          vehiculos.add(vehiculo);
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  /**
   * Añade un nuevo alquiler a la colección y lo guarda en el archivo CSV
   * correspondiente. Además guarda el alquiler en el atributo ultimoAlquiler,
   * para que sea accesible para generar la factura
   *
   * @param alquiler
   */
  public void addAlquiler(Alquiler alquiler) {
      try {
          PrintStream out = new PrintStream(new FileOutputStream(ARCHIVO_ALQUILERES, true));
          out.println();
          alquileres.add(alquiler);
          ultimoAlquiler = alquiler;
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  // Getters diversos

  public String getCif() {
    return cif;
  }

  public String getNombreEmpresa() {
    return nombreEmpresa;
  }

  public String getDireccion() {
    return direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public Alquiler getUltimoAlquiler() {
    return ultimoAlquiler;
  }

}
