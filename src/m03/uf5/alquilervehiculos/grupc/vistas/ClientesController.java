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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
           
            if (event.getSource() == btnNuevo) { //miro el elemento que ha llamado al metodo
                controladorEdicion.setCliente(null); //si ha pulsado bonton nuevo el cliente es null
            } else {
                controladorEdicion.setCliente(tvCliente.getSelectionModel().getSelectedItem());
            
            }
            ventanaEdicion.showAndWait(); //muestro la ventana

            Cliente cliente = controladorEdicion.getCliente();

            if (cliente != null) {
                
                try (Connection con = DriverManager.getConnection(urlBBDD, "admin_alquiler", "admin")) {
                    String sentenciaInsertar = "{CALL insertar_cliente(?,?,?,?)}";
                    String sentenciaEditar = "{CALL modifica_cliente(?,?,?,?)}";
                    
                    if (event.getSource() == btnNuevo) {
                        
                        CallableStatement cs = con.prepareCall(sentenciaInsertar);

                        cs.setString(1, cliente.getNif());
                        cs.setString(2, cliente.getNombre());
                        cs.setString(3, cliente.getApellido1());
                        cs.setString(4, cliente.getApellido2());
                        cs.execute();
                       
                    } else {

                        CallableStatement cs2 = con.prepareCall(sentenciaEditar);

                        cs2.setString(1, cliente.getNif());
                        cs2.setString(2, cliente.getNombre());
                        cs2.setString(3, cliente.getApellido1());
                        cs2.setString(4, cliente.getApellido2());
                        cs2.execute();

                    }

                  
                    if (event.getSource() == btnNuevo) { //para recuperar el id de la nueva insercion
                        
                        clientes.add(cliente);
                                                
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
