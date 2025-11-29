/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import javafx.event.ActionEvent; // 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class IniciarController implements Initializable {
@FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtDir;

    @FXML
    private TextField txtCorreo;

    @FXML
    private Button btnRegistrar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void abrirAdministrador(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../vista/administrador.fxml")
            );

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Administrador");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @FXML
    private void Registrar(ActionEvent event) {
        if (!validarCampos()) return;

        
        mostrarInfo("Inicio de sesión", "Inicio de sesión como Usuario exitoso.");
              cambiarVentana("/vista/catalogo.fxml", "Panaderia Rico Pan - Catálogo");
    }
    // Validación de campos
  
    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() ||
            txtTel.getText().isEmpty() ||
            txtDir.getText().isEmpty() ||
            txtCorreo.getText().isEmpty()) {

            mostrarError("Campos vacíos", "Debe llenar todos los campos para continuar.");
            return false;
        }
        return true;
    }
       // Cambiar pantalla
   
    private void cambiarVentana(String rutaFXML, String tituloVentana) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaFXML));
            Stage stage = new Stage();
            stage.setTitle(tituloVentana);
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual
            Stage actual = (Stage) btnRegistrar.getScene().getWindow();
            actual.close();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error", "No se pudo abrir la ventana: " + rutaFXML);
        }
    }
// Alertas
    
    private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarInfo(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
}