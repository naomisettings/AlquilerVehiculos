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
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import m03.uf5.alquilervehiculos.grupc.GestorEscenas;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import static m03.uf5.alquilervehiculos.grupc.modelo.Modelo.printSQLException;
import m03.uf5.alquilervehiculos.grupc.modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class VehiculosController implements Initializable, MiControlador {

    private ObservableList<Vehiculo> vehiculos;

    @FXML
    private TableView<Vehiculo> tblVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> clmMatricula;
    @FXML
    private TableColumn<Vehiculo, String> clmModelo;
    @FXML
    private Label lblMatricula;
    @FXML
    private Label lblModelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();
    }

    public List<Vehiculo> carregaVehiculos() {
        List<Vehiculo> vList = new ArrayList();
        Modelo modelo = Modelo.getModelo();
        Map<String, Vehiculo> v = modelo.getVehiculos();
        for (Map.Entry<String, Vehiculo> entry : v.entrySet()) {

            Vehiculo value = entry.getValue();
            vList.add(value);
        }
        return vList;

    }

    @Override
    public void actualizar() {

        vehiculos = FXCollections.observableArrayList(carregaVehiculos());
        tblVehiculo.setItems(vehiculos);
        clmMatricula.setCellValueFactory((datosFila) -> datosFila.getValue().getMatriculaProperty());
        clmModelo.setCellValueFactory((datosFila) -> datosFila.getValue().getModeloProperty());

        tblVehiculo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraVehiculo(newValue));
        tblVehiculo.getSelectionModel().select(0);
    }

    private void muestraVehiculo(Vehiculo vehiculo) {
        lblMatricula.setText(vehiculo.getMatricula());
        lblModelo.setText(vehiculo.getModelo());

    }

    @FXML
    private void hldbttnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraMenuPrincipal();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hldbttnNuevo(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraNuevoVehiculo();
        } catch (IOException ex) {
            Logger.getLogger(NuevoVehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hldbttnEditar(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraEditarVehiculo();
        } catch (IOException ex) {
            Logger.getLogger(EditarVehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hldbttnBorrar(MouseEvent event) {
        Vehiculo v = tblVehiculo.getSelectionModel().getSelectedItem();
        if (!event.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText("Borrar Vehículo");
            alert.setContentText("Desea eliminar el vehiculo: " +
                    v.getMatricula());
      
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                borrarVehiculo(v);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }
    

    

    private boolean borrarVehiculo(Vehiculo vehiculo) {
        boolean correcte = false;
        String matricula = vehiculo.getMatricula();
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/alquilervehiculos?useUnicode=true&"
                + "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
                + "serverTimezone=UTC&noAccesToProcedureBodies=True",
                "admin_alquiler", "admin")) {

            String sql = "{CALL elimina_vehiculo(?)}";
            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setString(1, matricula);
                cs.execute();
                System.out.println("executa");

                if (cs.getUpdateCount() == 1) {
                    correcte = true;
                }
                Modelo.getModelo().setVehiculos(matricula);
                actualizar();
            } catch (SQLException e) {
                printSQLException(e);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return correcte;
    }

}
