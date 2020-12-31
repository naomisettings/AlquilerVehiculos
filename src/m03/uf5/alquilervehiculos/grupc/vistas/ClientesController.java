/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import static m03.uf5.alquilervehiculos.grupc.modelo.Modelo.printSQLException;

/**
 * FXML Controller class
 *
 * @author angels
 */
public class ClientesController implements Initializable, MiControlador {

    private ObservableList<Cliente> clientes;
    protected static Cliente enviarCliente;
    private static String urlBBDD = "jdbc:mysql://localhost:3306/alquilervehiculos?useUnicode=true&"
            + "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC&noAccesToProcedureBodies=True";

    @FXML
    private Label tbCliente;
    @FXML
    private Label lblNif;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApellido1;
    @FXML
    private Label lblApellido2;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnNuevo;
    @FXML
    private TableView<Cliente> tvCliente;
    @FXML
    private TableColumn<Cliente, String> clmNif;
    @FXML
    private TableColumn<Cliente, String> clmNombre;
    @FXML
    private TableColumn<Cliente, String> clmApellido1;
    @FXML
    private TableColumn<Cliente, String> clmApellido2;


    /*  @FXML
  private TextArea textClientes;

  /**
   * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        actualizar();
        insertarTabla();
    }

    public List<Cliente> cargaClientes() {
        List<Cliente> cList = new ArrayList();
        Modelo modelo = Modelo.getModelo();
        Map<String, Cliente> cliente = modelo.getClientes();
        for (Map.Entry<String, Cliente> entry : cliente.entrySet()) {
            Cliente value = entry.getValue();
            cList.add(value);

        }
        return cList;

    }

    @Override
    public void actualizar() {
        /*  if (cliente != null) {
            if (enviarCliente != null) {
                cliente.remove(enviarCliente);
                cliente.add(NuevoClienteController.nuevoEnviaCliente);
            }
        }*/
        insertarTabla();

    }

    public void insertarTabla() {

        clientes = FXCollections.observableArrayList(cargaClientes()); //llamo al metodo cargaCliente que devuelve una List que la convierte en observableArrayList
        tvCliente.setItems(clientes);
        //los datos que tienen que presentar en cada columna
        clmNombre.setCellValueFactory((datosFila) -> datosFila.getValue().getNombreProperty());
        clmApellido1.setCellValueFactory((datosFila) -> datosFila.getValue().getApellido1Property());
        clmApellido2.setCellValueFactory((datosFila) -> datosFila.getValue().getApellido2Property());
        clmNif.setCellValueFactory((datosFila) -> datosFila.getValue().getNifProperty());

        tvCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraCliente(newValue));
        tvCliente.getSelectionModel().select(0); //selecciono la primera fila

    }

    private void muestraCliente(Cliente cliente) {
        if (cliente != null) {
            lblNif.setText(cliente.getNif());
            lblNombre.setText(cliente.getNombre());
            lblApellido1.setText(cliente.getApellido1());
            lblApellido2.setText(cliente.getApellido2());

        }

    }

    @FXML
    private void handlebtnEliminar(MouseEvent event) throws SQLException {
        Cliente cliente = tvCliente.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alquiler de Vehículos");
        alert.setHeaderText("Eliminar Cliente");
        alert.setContentText("Desea eliminar el cliente: "
                + cliente.getNif());

        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.get() == ButtonType.OK) {

            eliminarCliente(cliente);

        } else {
            // usuario cancela accion
        }
    }

    private boolean eliminarCliente(Cliente cliente) throws SQLException {
        boolean correcto = false;
        String nif = cliente.getNif();
        try (Connection con = DriverManager.getConnection(urlBBDD,
                "admin_alquiler", "admin")) {
            String sentencia = "{CALL elimina_cliente(?)}";
            try (CallableStatement cs = con.prepareCall(sentencia)) {
                cs.setString(1, nif);
                cs.execute();

                Modelo.getModelo().setClientes(nif);
                insertarTabla();
                if (cs.getUpdateCount() == 1) {
                    correcto = true;
                }
            } catch (SQLException e) {
                printSQLException(e);

            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return correcto;
    }

    @FXML
    private void handlebtnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraMenuPrincipal();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /*  @FXML
    private void handleEditar(MouseEvent event) {
        enviarCliente = tvCliente.getSelectionModel().getSelectedItem();
              

        try {
          GestorEscenas.getGestor().muestraNuevoCliente();
          

        } catch (IOException ex) {
            Logger.getLogger(NuevoClienteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }*/
 /* @FXML
    private void handleNuevo(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraNuevoCliente();

        } catch (IOException ex) {
            Logger.getLogger(NuevoClienteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

     */
    @FXML
    private void handleEditar(ActionEvent event) {

        enviarCliente = tvCliente.getSelectionModel().getSelectedItem();

        //creacion de la ventana de edicion
        Stage ventanaPrincipal = (Stage) tvCliente.getScene().getWindow();
        Stage ventanaEdicion = new Stage();
        ventanaEdicion.initModality(Modality.WINDOW_MODAL);
        ventanaEdicion.initOwner(ventanaPrincipal);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("NuevoCliente.fxml"));

        try {
            //carga la ventana de edicion
            Scene escenaEdicion = new Scene(loader.load());
            NuevoClienteController controladorEdicion = loader.getController();
            ventanaEdicion.setScene(escenaEdicion);
            //     controladorEdicion.setCliente((Cliente) cliente);

            if (event.getSource() == btnNuevo) { //miro el elemento que ha llamado al metodo
                controladorEdicion.setCliente(null); //si ha pulsado bonton nuevo el cliente es null
            } else {
                controladorEdicion.setCliente(tvCliente.getSelectionModel().getSelectedItem());
                //   NuevoClienteController.nuevoEnviaCliente.getNombre(); //le paso el cliente seleccionado
                //   NuevoClienteController.nuevoEnviaCliente.getNif();
            }
            ventanaEdicion.showAndWait(); //muestro la ventana

            Cliente cliente = controladorEdicion.getCliente();

            if (cliente != null) {
                System.out.println("cliente despres del null " + cliente.toString());
                try (Connection con = DriverManager.getConnection(urlBBDD, "admin_alquiler", "admin")) {
                    PreparedStatement sentencia = null;
                    System.out.println("cliente abans getSource " + cliente.toString());
                    if (event.getSource() == btnNuevo) {
                        sentencia = con.prepareStatement("INSERT INTO cliente" //si es el boton nuevo
                                + "( nombre, apellido1, apellido2, nif) VALUES (?,?,?,?)",
                                Statement.RETURN_GENERATED_KEYS); //necesario para recuperar el id
                    } else {
                        sentencia = con.prepareStatement("UPDATE cliente SET " //si es el boton editar
                                + " nombre = ?, apellido1 =  ? , apellido2 = ? WHERE nif = ?");
                        //sentencia.setString(1, cliente.getNif());

                    }
                    System.out.println("Nif PrepareStatement " + cliente.getNif());
                    System.out.println("Apellido1 PrepareStatement " + cliente.getApellido1());
                    sentencia.setString(4, cliente.getNif());

                    sentencia.setString(1, cliente.getNombre());
                    sentencia.setString(2, cliente.getApellido1());
                    sentencia.setString(3, cliente.getApellido2());

                    if (sentencia.executeUpdate() == 0) {
                        throw new SQLException("No se ha podido realitzar la inserción/edición");
                    }
                    if (event.getSource() == btnNuevo) { //para recuperar el id de la nueva insercion
                        //ResultSet rs = sentencia.getGeneratedKeys();
                       // if (rs.next()) {
                            //    cliente.setNif(rs.getString(1));
                        //    Modelo.getModelo().addCliente(cliente);
                            clientes.add(cliente);
                            System.out.println("clientes " + clientes);
                       // }
                    }

                } catch (SQLException ex) {
                    printSQLException(ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
