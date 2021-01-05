/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.alquilervehiculos.grupc.vistas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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
import m03.uf5.alquilervehiculos.grupc.modelo.Modelo;
import m03.uf5.alquilervehiculos.grupc.modelo.Alquiler;
import m03.uf5.alquilervehiculos.grupc.modelo.Cliente;

/**
 * FXML Controller class
 *
 * @author sella
 */
public class AlquileresController implements Initializable, MiControlador {

    protected static ObservableList<Alquiler> alquileres;

    @FXML
    private TableColumn<Alquiler, String> clmNif;
    @FXML
    private TableColumn<Alquiler, String> clmMatricula;
    @FXML
    private TableColumn<Alquiler, LocalDate> clmFechaInicio;
    @FXML
    private TableColumn<Alquiler, LocalDate> clmFechaFin;
    @FXML
    private Label lblNif;
    @FXML
    private Label lblMatricula;
    @FXML
    private Label lblFechaInicio;
    @FXML
    private Label lblFechaFin;
    @FXML
    private Button bttnNuevo;
    @FXML
    private Button bttnEditar;
    @FXML
    private TableView<Alquiler> tblAlquileres;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblModelo;
    @FXML
    private Button btnFactura;
    @FXML
    private Button btnVolver;

    @Override
    public void actualizar() {
        insertarTabla();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();
    }

    public void insertarTabla() {
        alquileres = FXCollections.observableArrayList(Modelo.getModelo().getAlquileres());
        tblAlquileres.setItems(alquileres);
        clmNif.setCellValueFactory((datosFila) -> datosFila.getValue().getCliente().getNifProperty());
        clmMatricula.setCellValueFactory((datosFila) -> datosFila.getValue().getVehiculo().getMatriculaProperty());
        clmFechaInicio.setCellValueFactory((datosFila) -> datosFila.getValue().getFechaInicioProperty());
        clmFechaFin.setCellValueFactory((datosFila) -> datosFila.getValue().getFechaFinProperty());
        tblAlquileres.getSortOrder().setAll(clmFechaInicio);

        tblAlquileres.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> muestraAlquiler(newValue));
        tblAlquileres.getSelectionModel().select(0);
    }

    public void muestraAlquiler(Alquiler alquiler) {
        if (alquiler != null) {
            lblNif.setText(alquiler.getCliente().getNif());
            lblMatricula.setText(alquiler.getVehiculo().getMatricula());
            Cliente c = alquiler.getCliente();
            lblNombre.setText(c.getNombre() + " " + c.getApellido1() + " " + c.getApellido2());
            lblModelo.setText(alquiler.getVehiculo().getModelo());
            lblFechaInicio.setText(alquiler.getFechaInicio().toString());
            lblFechaFin.setText(alquiler.getFechaFin().toString());
        }
    }

    @FXML
    private void hldbttnNuevo(ActionEvent event) {
        Stage ventanaPrincipal = (Stage) tblAlquileres.getScene().getWindow();
        Stage ventanaEdicion = new Stage();
        ventanaEdicion.initModality(Modality.WINDOW_MODAL);

        ventanaEdicion.initOwner(ventanaPrincipal);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NuevoAlquiler.fxml"));
        try {
            Scene escenaEdicion = new Scene(loader.load());
            NuevoAlquilerController controller = loader.getController();
            ventanaEdicion.setScene(escenaEdicion);

            if (event.getSource() == bttnNuevo) {
                controller.setAlquiler(null);
            } else {
                controller.setAlquiler(tblAlquileres.getSelectionModel().getSelectedItem());
            }
            ventanaEdicion.showAndWait();
            Alquiler alquiler = controller.getAlquiler();
            if (alquiler.getCliente() != null) {
                if (event.getSource() == bttnNuevo) {
                    Modelo.getModelo().addAlquiler(alquiler);
                    alquileres.add(alquiler);
                } else {
                    Modelo.getModelo().modificarAlquiler(alquiler);
                }
                tblAlquileres.refresh();

            }
        } catch (IOException ex) {
            Logger.getLogger(AlquileresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hldbttnBorrar(MouseEvent event) {
        Alquiler a = tblAlquileres.getSelectionModel().getSelectedItem();
        if (!event.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alquiler de Vehículos");
            alert.setHeaderText("Borrar Alquiler");
            alert.setContentText("Desea eliminar el alquiler: "
                    + a.getCliente().getNif() + " " + a.getVehiculo().getMatricula()
                    + " " + a.getFechaInicio().toString() + " " + a.getFechaFin().toString());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Modelo.getModelo().borrarAlquiler(a);
                insertarTabla();
            }
        }
    }

    @FXML
    private void hldbttnVolver(MouseEvent event) {
        try {
            GestorEscenas.getGestor().muestraNuevoAlquiler();
        } catch (IOException ex) {
            Logger.getLogger(NuevoAlquilerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hlbttnFactura(MouseEvent event) {

        //creacio de la pantalla d'edicio
        Stage ventanaPrincipal = (Stage) tblAlquileres.getScene().getWindow();
        Stage ventanaEdicion = new Stage();
        ventanaEdicion.initModality(Modality.WINDOW_MODAL);

        ventanaEdicion.initOwner(ventanaPrincipal);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Factura.fxml"));

        //carrega la pantalla d'edicio
        try {
            Scene escenaEdicion = new Scene(loader.load());
            FacturaController controller = loader.getController();
            ventanaEdicion.setScene(escenaEdicion);

            // if (event.getSource() == bttnNuevo) {
            //   controller.setAlquiler(null);
            //} else {
            if (event.getSource() == btnFactura) {
                controller.setAlquiler(tblAlquileres.getSelectionModel().getSelectedItem());
                
           
            }
ventanaEdicion.showAndWait();
            // }
            //  
        } catch (IOException ex) {
            Logger.getLogger(AlquileresController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
