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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        insertarTabla();
    }

    /**
     * Carrega els clients
     * @return
     */
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

    /**
     * Inserta la taula de clients
     */
    public void insertarTabla() {

        //crido al metode cargaCliente que torna una List que es converteix en una observableArrayList
        clientes = FXCollections.observableArrayList(cargaClientes()); 
        tvCliente.setItems(clientes);

        // les dades que ha de presentar cada columna
        clmNombre.setCellValueFactory((datosFila) -> datosFila.getValue().getNombreProperty());
        clmApellido1.setCellValueFactory((datosFila) -> datosFila.getValue().getApellido1Property());
        clmApellido2.setCellValueFactory((datosFila) -> datosFila.getValue().getApellido2Property());
        clmNif.setCellValueFactory((datosFila) -> datosFila.getValue().getNifProperty());

        tvCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraCliente(newValue));
        tvCliente.getSelectionModel().select(0); //sel.lecciono la primera fila

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
        alert.setTitle("Alquiler de Veh??culos");
        alert.setHeaderText("Eliminar Cliente");
        alert.setContentText("Desea eliminar el cliente: "
                + cliente.getNif());

        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.get() == ButtonType.OK) {

            eliminarCliente(cliente);

            Alert confimacion = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alquiler de Veh??culos");
            alert.setHeaderText("Eliminar Cliente");
            alert.setContentText("Se ha elminado el cliente correctamente ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

            }

        }
    }
/**
     *
     * @param cliente
     * @return metode que elimina el client
     */
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

        //creacio de la pantalla d'edicio
        Stage ventanaPrincipal = (Stage) tvCliente.getScene().getWindow();
        Stage ventanaEdicion = new Stage();
        ventanaEdicion.initModality(Modality.WINDOW_MODAL);
        ventanaEdicion.initOwner(ventanaPrincipal);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("NuevoCliente.fxml"));

        try {
            //carrega la pantalla d'edicio
            Scene escenaEdicion = new Scene(loader.load());
            NuevoClienteController controladorEdicion = loader.getController();
            ventanaEdicion.setScene(escenaEdicion);

            if (event.getSource() == btnNuevo) { //miro l'element que ha cridat al metode
                controladorEdicion.setCliente(null); //si ha marcat boto nuevo el client es null 
            } else {
                controladorEdicion.setCliente(tvCliente.getSelectionModel().getSelectedItem());

            }
            ventanaEdicion.showAndWait(); //mostra la pantalla

            Cliente cliente = controladorEdicion.getCliente();
            
            
            if (cliente != null) {

                if (event.getSource() == btnNuevo) {
                    
                    if (cliente.getNif() != null) {
                        Modelo.getModelo().addCliente(cliente);
                        clientes.add(cliente);
                    }
                    
                } else {

                    Modelo.getModelo().modificarCliente(cliente);
                    tvCliente.getSelectionModel().getSelectedItem().getNif();

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
