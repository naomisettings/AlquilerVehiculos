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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class Modelo {

    private static String urlBBDD = "jdbc:mysql://localhost:3306/alquilervehiculos?useUnicode=true&"
            + "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC&noAccesToProcedureBodies=True";

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
    // private ObservableMap<String, Vehiculo> vehiculos;

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
     * Metode que carrega les dades del client a la BBDD
     *
     * @param ficheroClientes
     */
    private void cargaClientes(Connection con) {
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nif = rs.getString(1);
                String nombre = rs.getString(2);
                String apellido1 = rs.getString(3);
                String apellido2 = rs.getString(4);

                Cliente c = new Cliente();
                c.setNif(nif);
                c.setNombre(nombre);
                c.setApellido1(apellido1);
                c.setApellido2(apellido2);

                clientes.put(nif, c);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    /**
     * Metode que carrega les dades del vehicle a la BBDD
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

                Vehiculo v = new Vehiculo();
                v.setMatricula(matricula);
                v.setModelo(modelo);

                vehiculos.put(matricula, v);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    /**
     * Metode que carrega les dades dels lloguers a la BBDD
     *
     * @param ficheroAlquileres
     */
    private void cargaAlquileres(Connection con) {
        String sql = "SELECT * FROM alquiler";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                Cliente c = clientes.get(rs.getString(2));
                Vehiculo v = vehiculos.get(rs.getString(3));
                LocalDate inicio = rs.getObject("fechainicio", LocalDate.class);
                LocalDate fin = rs.getObject("fechafin", LocalDate.class);

                Alquiler a = new Alquiler(id, c, v, inicio, fin);
                alquileres.add(a);
            }
        } catch (SQLException e) {
            printSQLException(e);
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

            try (Connection con = DriverManager.getConnection(urlBBDD,
                    "admin_alquiler", "admin")) {

                modelo.cargaVehiculos(con);
                modelo.cargaClientes(con);
                modelo.cargaAlquileres(con);

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

    public void setClientes(String nif) {
        clientes.remove(nif);
    }

    /**
     *
     * @return la colección de vehículos disponibles
     */
    public Map<String, Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(String matricula) {
        vehiculos.remove(matricula);
    }

    /**
     *
     * @return la colección de alquileres realizados
     */
    public List getAlquileres() {
        return alquileres;
    }

    /**
     * Afegeix un nou client a la BBDD
     *
     * @param cliente
     */
    public void addCliente(Cliente cliente) {
        try (Connection con = DriverManager.getConnection(urlBBDD,
                "admin_alquiler", "admin")) {
            String sentencia = "{CALL insertar_cliente(?,?,?,?)}";
            try (CallableStatement cs = con.prepareCall(sentencia)) {
                cs.setString(1, cliente.getNif());
                cs.setString(2, cliente.getNombre());
                cs.setString(3, cliente.getApellido1());
                cs.setString(4, cliente.getApellido2());
                cs.execute();
                Cliente c = new Cliente();
                c.setNif(cliente.getNif());
                c.setNombre(cliente.getNombre());
                c.setApellido1(cliente.getApellido1());
                c.setApellido2(cliente.getApellido2());

                clientes.put(cliente.getNif(), c);

            } catch (SQLException e) {
                printSQLException(e);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
    }
    
/**
     * Modifica un client de la BBDD
     *
     * @param cliente
     */
    public void modificarCliente(Cliente cliente) {
        if (cliente != null) {
            try (Connection con = DriverManager.getConnection(urlBBDD,
                    "admin_alquiler", "admin")) {
                String sentencia = "{CALL modifica_cliente(?,?,?,?)}";
                try (CallableStatement cs2 = con.prepareCall(sentencia)) {
                    cs2.setString(1, cliente.getNif());
                    cs2.setString(2, cliente.getNombre());
                    cs2.setString(3, cliente.getApellido1());
                    cs2.setString(4, cliente.getApellido2());
                    cs2.execute();

                }

            } catch (SQLException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Afegeix un nou vehicle a la BBDD
     *
     * @param vehiculo
     */
    public void addVehiculo(Vehiculo vehiculo) {
        if (vehiculo.getMatricula() != null) {
            try (Connection con = DriverManager.getConnection(urlBBDD,
                    "admin_alquiler", "admin")) {

                String sql = "{CALL insertar_vehiculo(?, ?)}";
                try (CallableStatement cs = con.prepareCall(sql)) {
                    cs.setString(1, vehiculo.getMatricula());
                    cs.setString(2, vehiculo.getModelo());
                    cs.execute();
                    Vehiculo v = new Vehiculo();
                    v.setMatricula(vehiculo.getMatricula());
                    v.setModelo(vehiculo.getModelo());
                    vehiculos.put(vehiculo.getMatricula(), v);
                } catch (SQLException e) {
                    printSQLException(e);
                }

            } catch (SQLException ex) {
                printSQLException(ex);
            }
        }
    }

    /**
     * Afegeix un nou lloguer a la BBDD
     *
     * @param alquiler
     */
    public void addAlquiler(Alquiler alquiler) {
        try (Connection con = DriverManager.getConnection(urlBBDD,
                "admin_alquiler", "admin")) {

            String sql = "{CALL insertar_alquiler(?, ?, ?, ?)}";
            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setString(1, alquiler.getCliente().getNif());
                cs.setString(2, alquiler.getVehiculo().getMatricula());
                cs.setObject(3, alquiler.getFechaInicio());
                cs.setObject(4, alquiler.getFechaFin());
                cs.execute();
            } catch (SQLException e) {
                printSQLException(e);
            }

            sql = "SELECT * FROM alquiler ORDER BY id DESC LIMIT 1;";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    alquiler.setId(id);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            alquileres.add(alquiler);
            ultimoAlquiler = alquiler;

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.println(ultimoAlquiler.getId());

    }
/**
     * Esborra un lloguer de la BBDD
     *
     * @param alquiler
     */
    public void borrarAlquiler(Alquiler alquiler) {
        int id = alquiler.getId();
        try (Connection con = DriverManager.getConnection(urlBBDD,
                "admin_alquiler", "admin")) {

            String sql = "{CALL elimina_alquiler(?)}";
            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setInt(1, id);
                cs.execute();

                alquileres.remove(alquiler);

            } catch (SQLException e) {
                printSQLException(e);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
    }
/**
     * Modifica un lloguer de la BBDD
     *
     * @param alquiler
     */
    public void modificarAlquiler(Alquiler alquiler) {
        try (Connection con = DriverManager.getConnection(urlBBDD,
                "admin_alquiler", "admin")) {

            String sql = "{CALL modifica_alquiler(?, ?, ?, ?, ?)}";
            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setInt(1, alquiler.getId());
                cs.setString(2, alquiler.getCliente().getNif());
                cs.setString(3, alquiler.getVehiculo().getMatricula());
                cs.setObject(4, alquiler.getFechaInicio());
                cs.setObject(5, alquiler.getFechaFin());
                cs.execute();   
            } catch (SQLException e) {
                printSQLException(e);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
    }

    /**
     * Modifica un vehicle de la BBDD
     * @param vehiculo
     * @param matricula_original
     */
    public void modificarVehiculo(Vehiculo vehiculo, String matricula_original) {
        if (vehiculo != null) {
            try (Connection con = DriverManager.getConnection(urlBBDD,
                    "admin_alquiler", "admin")) {

                String sql = "{CALL modifica_vehiculo(?, ?, ?)}";
                try (CallableStatement cs = con.prepareCall(sql)) {
                    cs.setString(1, matricula_original);
                    cs.setString(2, vehiculo.getMatricula());
                    cs.setString(3, vehiculo.getModelo());
                    cs.execute();
                    vehiculos.replace(matricula_original, vehiculo);
                } catch (SQLException e) {
                    printSQLException(e);
                }

            } catch (SQLException ex) {
                printSQLException(ex);
            }
        }
    }

    /**
     * Esborra un vehicle de la BBDD
     * @param vehiculo
     */
    public void borrarVehiculo(Vehiculo vehiculo) {
        String matricula = vehiculo.getMatricula();
        try (Connection con = DriverManager.getConnection(urlBBDD,
                "admin_alquiler", "admin")) {

            String sql = "{CALL elimina_vehiculo(?)}";
            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setString(1, matricula);
                cs.execute();

                Modelo.getModelo().setVehiculos(matricula);

            } catch (SQLException e) {
                printSQLException(e);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
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
