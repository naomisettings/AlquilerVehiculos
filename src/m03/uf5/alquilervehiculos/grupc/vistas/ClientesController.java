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

    private ObservableList<Cliente> cliente;
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
        actualizar();
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
        if (cliente != null) {
            if (enviarCliente != null) {
                cliente.remove(enviarCliente);
                cliente.add(NuevoClienteController.nuevoEnviaCliente);
            }
        }
        insertarTabla();

    }

    public void insertarTabla() {

        cliente = FXCollections.observableArrayList(cargaClientes());
        tvCliente.setItems(cliente);
        clmNombre.setCellValueFactory((datosFila) -> datosFila.getValue().getNombreProperty());
        clmApellido1.setCellValueFactory((datosFila) -> datosFila.getValue().getApellido1Property());
        clmApellido2.setCellValueFactory((datosFila) -> datosFila.getValue().getApellido2Property());
        clmNif.setCellValueFactory((datosFila) -> datosFila.getValue().getNifProperty());

        tvCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraCliente(newValue));
        tvCliente.getSelectionModel().select(0);

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

    @FXML
    private void handleEditar(MouseEvent event) {
        enviarCliente = tvCliente.getSelectionModel().getSelectedItem();
              

        try {
            GestorEscenas.getGestor().muestraNuevoCliente();

        } catch (IOException ex) {
            Logger.getLogger(NuevoClienteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    @FXML
    private void handleNuevo(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraNuevoCliente();

        } catch (IOException ex) {
            Logger.getLogger(NuevoClienteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

 

}
