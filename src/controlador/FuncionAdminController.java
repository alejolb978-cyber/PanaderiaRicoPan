/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Alejandro
 */

public class FuncionAdminController implements Initializable {

    @FXML
    private ImageView imgMex;

    @FXML
    private ImageView imgFin;

    @FXML
    private Button btnAgregar;

    private String productoSeleccionado; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgMex.setOnMouseClicked(e -> {
            productoSeleccionado = "Pan Mexicano";
            System.out.println("Seleccionado: " + productoSeleccionado);
        });

        imgFin.setOnMouseClicked(e -> {
            productoSeleccionado = "Pan Finlandés";
            System.out.println("Seleccionado: " + productoSeleccionado);
        });

        btnAgregar.setOnAction(e -> agregarProducto());
    }

    private void agregarProducto() {
        System.out.println("Agregado: " + productoSeleccionado);
    }
}