/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class AdministradorController {

    @FXML
    private TextField txtContrasena;

    @FXML
    private void continuar() {
        String contrasena = txtContrasena.getText();

        if (contrasena.equals("1234")) {
            try {

                URL url = getClass().getResource("../vista/funcionAdmin.fxml");

                if (url == null) {
                    System.out.println("❌ ERROR: No se encontró funcionAdmin.fxml");
                    return;
                }

                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Funciones Administrador");
                stage.setScene(new Scene(root));
                stage.show();

                // Cerramos la ventana actual
                Stage actual = (Stage) txtContrasena.getScene().getWindow();
                actual.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Contraseña incorrecta");
        }
    }
}
