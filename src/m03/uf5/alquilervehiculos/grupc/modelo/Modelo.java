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
public class Modelo {

    /*COMETARIO RAIMON

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
  private COLECCION clientes;
  private COLECCION vehiculos;
  private COLECCION alquileres;
  // Último alquiler realizado
  private Alquiler ultimoAlquiler;

  /** Constructor privado de la clase. Es llamado desde el método inicializar.
   * @param cif
   * @param nombreEmpresa
   * @param direccion
   * @param telefono 
     */

    private Modelo(String cif, String nombreEmpresa, String direccion, String telefono) {
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
     * Método que lee el archivo CSV con los datos de los vehículos y lo carga
     * en la colección correspondiente
     *
     * @param ficheroVehiculos
     */
    private void cargaVehiculos(String ficheroVehiculos) {
    }

    /**
     * Método que lee el archivo CSV con los datos de los alquileres realizados
     * y lo carga en la colección correspondiente
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
    }

    /**
     * Método que devuelve el singleton de la clase
     *
     * @return la instancia del singleton
     * @throws RuntimeException en caso de que la clase no se haya inicializado
     * previamente
     */
    /* COMENTARIO RAIMON
  
  
  public static Modelo getModelo() throws RuntimeException{
  }

  /**
   * 
   * @return la colección de clientes dados de alta
     */
 /* COMENTARIO RAIMON
  
  public COLECCION getClientes() {
  }

  /**
   * 
   * @return la colección de vehículos disponibles
     */
 /* COMENTARIO RAIMON
  
  public COLECCION getVehiculos() {
  }

  /**
   * 
   * @return la colección de alquileres realizados
     */
 /* COMENTARIO RAIMON
  
  public COLECCION getAlquileres() {
  }

  /**
   * Añade un nuevo cliente a la colección y lo guarda en el archivo CSV correspondiente
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
    
    /* COMENTARIO RAIMON
    
   
    public String getCif() {
    }

    public String getNombreEmpresa() {
    }

    public String getDireccion() {
    }

    public String getTelefono() {
    }

    public Alquiler getUltimoAlquiler() {
    }

COMETARIO RAIMON*/
}
