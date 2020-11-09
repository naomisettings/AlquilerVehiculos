/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.modelo;

import java.util.TreeSet;

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
  private TreeSet clientes;
  private TreeSet vehiculos;
  private TreeSet alquileres;
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
  }

  /**
   * Método que lee el archivo CSV con los datos de los vehículos y lo carga en
   * la colección correspondiente
   *
   * @param ficheroVehiculos
   */
  private void cargaVehiculos(String ficheroVehiculos) {
  }

  /**
   * Método que lee el archivo CSV con los datos de los alquileres realizados y
   * lo carga en la colección correspondiente
   *
   * @param ficheroAlquileres
   */
  private void cargaAlquileres(String ficheroAlquileres) {
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
  public TreeSet getClientes() {
    return clientes;
  }

  /**
   *
   * @return la colección de vehículos disponibles
   */
  public TreeSet getVehiculos() {
    return vehiculos;
  }

  /**
   *
   * @return la colección de alquileres realizados
   */
  public TreeSet getAlquileres() {
    return alquileres;
  }

  /**
   * Añade un nuevo cliente a la colección y lo guarda en el archivo CSV
   * correspondiente
   *
   * @param cliente
   */
  public void addCliente(Cliente cliente) {
  }

  /**
   * Añade un nuevo vehiculo a la colección y lo guarda en el archivo CSV
   * correspondiente
   *
   * @param vehiculo
   */
  public void addVehiculo(Vehiculo vehiculo) {
  }

  /**
   * Añade un nuevo alquiler a la colección y lo guarda en el archivo CSV
   * correspondiente. Además guarda el alquiler en el atributo ultimoAlquiler,
   * para que sea accesible para generar la factura
   *
   * @param alquiler
   */
  public void addAlquiler(Alquiler alquiler) {
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
