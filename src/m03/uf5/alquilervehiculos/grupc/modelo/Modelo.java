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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    private Map<String, Cliente> clientes = new HashMap<>();
    private Map<String, Vehiculo> vehiculos = new HashMap<>();
    private List<Alquiler> alquileres = new ArrayList<>();
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

    public static void printSQLException(SQLException e) {
        e.printStackTrace(System.err);
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
        Throwable t = e.getCause();
        while (t != null) {
            System.out.println("Cause: " + t);
            t = t.getCause();
        }
    }

    /**
     * Método que lee el archivo CSV con los datos de los clientes y lo carga en
     * la colección correspondiente
     *
     * @param ficheroClientes
     */
    private void cargaClientes(String ficheroClientes) {
        File fichero = new File(ficheroClientes);
        try {
            Scanner sc = new Scanner(fichero);
            while (sc.hasNext()) {
                String[] datos = sc.nextLine().split(";");
                Cliente c = new Cliente(datos[0], datos[1], datos[2], datos[3]);
                clientes.put(c.getNif(), c);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha podido abrir el fichero de clientes");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error al cargar el fichero de clientes: " + ex.getMessage());
        }

    }

    /**
     * Método que lee el archivo CSV con los datos de los vehículos y lo carga
     * en la colección correspondiente
     *
     * @param ficheroVehiculos
     */
    private void cargaVehiculos(Connection con) {
        String sql = "SELECT * FROM vehiculo";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String matricula = rs.getString(1);
                String modelo = rs.getString(2);
                System.out.println(matricula);
                System.out.println(modelo);
                /*
                Vehiculo v = new Vehiculo(matricula, modelo);
                vehiculos.put(matricula, v);
                */
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    /**
     * Método que lee el archivo CSV con los datos de los alquileres realizados
     * y lo carga en la colección correspondiente
     *
     * @param ficheroAlquileres
     */
    private void cargaAlquileres(String ficheroAlquileres) {
        File fichero = new File(ficheroAlquileres);
        try {
            Scanner sc = new Scanner(fichero);
            while (sc.hasNext()) {
                String[] datos = sc.nextLine().split(";");
                Cliente c = clientes.get(datos[0]);
                Vehiculo v = vehiculos.get(datos[1]);
                Alquiler al = new Alquiler(c, v, LocalDate.parse(datos[2]), LocalDate.parse(datos[3]));
                alquileres.add(al);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha podido abrir el fichero de alquileres");
        } catch (DateTimeParseException ex) {
            System.out.println("Error al cargar el fichero de vehiculos: " + ex.getMessage());
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

            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/alquilervehiculos?useUnicode=true&"
                    + "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
                    + "serverTimezone=UTC&noAccesToProcedureBodies=True",
                    "admin_alquiler", "admin")) {

                modelo.cargaVehiculos(con);

                modelo.cargaClientes(ARCHIVO_CLIENTES);
                modelo.cargaAlquileres(ARCHIVO_ALQUILERES);

            } catch (SQLException ex) {
                printSQLException(ex);
            }
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
        if (modelo == null) {
            throw (new RuntimeException("modelo no esta inicializado"));
        } else {
            return modelo;
        }
    }

    /**
     *
     * @return la colección de clientes dados de alta
     */
    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    /**
     *
     * @return la colección de vehículos disponibles
     */
    public Map<String, Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /**
     *
     * @return la colección de alquileres realizados
     */
    public List getAlquileres() {
        return alquileres;
    }

    /**
     * Añade un nuevo cliente a la colección y lo guarda en el archivo CSV
     * correspondiente
     *
     * @param cliente
     */
    public void addCliente(Cliente cliente) {
        clientes.put(cliente.getNif(), cliente);
        try {
            PrintStream out = new PrintStream(new FileOutputStream(ARCHIVO_CLIENTES, true));
            out.println(cliente.getNombre() + ";" + cliente.getApellido1() + ";"
                    + cliente.getApellido1() + ";" + cliente.getNif());
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al guargar el fichero de clientes");
        }

    }

    /**
     * Añade un nuevo vehiculo a la colección y lo guarda en el archivo CSV
     * correspondiente
     *
     * @param vehiculo
     */
    public void addVehiculo(Vehiculo vehiculo, Connection conn) {
        String sql = "{CALL insertar_vehiculo(?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {

        } catch (SQLException e) {
            printSQLException(e);
        }
        /*
        vehiculos.put(vehiculo.getMatricula(), vehiculo);
        try {
            PrintStream out = new PrintStream(new FileOutputStream(ARCHIVO_VEHICULOS, true));
            out.println(vehiculo.getMatricula() + ";" + vehiculo.getModelo());
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al guargar el fichero de vehiculos");
        }
         */

    }

    /**
     * Añade un nuevo alquiler a la colección y lo guarda en el archivo CSV
     * correspondiente. Además guarda el alquiler en el atributo ultimoAlquiler,
     * para que sea accesible para generar la factura
     *
     * @param alquiler
     */
    public void addAlquiler(Alquiler alquiler) {
        alquileres.add(alquiler);
        ultimoAlquiler = alquiler;
        try {
            PrintStream out = new PrintStream(new FileOutputStream(ARCHIVO_ALQUILERES, true));
            out.println(alquiler.getCliente().getNif() + ";"
                    + alquiler.getVehiculo().getMatriculaProperty() + ";"
                    + alquiler.getFechaInicio().toString() + ";"
                    + alquiler.getFechaFin().toString());
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al guargar el fichero de alquileres");
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
