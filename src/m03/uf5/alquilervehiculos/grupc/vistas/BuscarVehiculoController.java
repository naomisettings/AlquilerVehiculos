/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import m03.uf5.alquilervehiculos.grupc.modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author tabasin
 */
public class BuscarVehiculoController implements Initializable {
    
    private Vehiculo v;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<Vehiculo> tbvResultat;
    @FXML
    private TableColumn<Vehiculo, String> clmResultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public List<Vehiculo> carregaVehiculos() {
        List<Vehiculo> vList = new ArrayList();
        Modelo modelo = Modelo.getModelo();
        Map<String, Vehiculo> v = modelo.getVehiculos();
        String search = txtBuscar.getText().toUpperCase();

        for (Map.Entry<String, Vehiculo> entry : v.entrySet()) {

            if (entry.getKey().contains(search)) {
                Vehiculo value = entry.getValue();
                vList.add(value);
            }
        }
        return vList;
    }

    @FXML
    private void handleBuscar(ActionEvent event) {
        tbvResultat.setItems(FXCollections.observableArrayList(carregaVehiculos()));
        clmResultat.setCellValueFactory((datosFila) -> datosFila.getValue().getMatriculaProperty());
    }

    @FXML
    private void handleGuardar(ActionEvent event) {
        v = tbvResultat.getSelectionModel().getSelectedItem();
        Stage ventana = (Stage) btnBuscar.getScene().getWindow();
        ventana.close();
    }

    public Vehiculo getV() {
        return v;
    }

}
