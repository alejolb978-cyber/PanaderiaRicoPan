package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import modelo.Producto;
import modelo.TiendaService;

public class CarritoController {

    @FXML private ListView<String> listaCarrito;
    @FXML private Label lblTotal;
    @FXML private Button btnComprar;
    @FXML private Button btnVolver;

    private TiendaService tienda = TiendaService.getInstance();

    @FXML
    public void initialize() {
        cargarCarrito();

        btnComprar.setOnAction(event -> comprarSeleccionado());
        btnVolver.setOnAction(event -> cambiarEscena("Catalogo.fxml"));
    }

    private void cargarCarrito() {
        listaCarrito.getItems().clear();
        double total = 0;

        for (var item : tienda.getCarrito()) {
            Producto p = item.getProducto();
            listaCarrito.getItems().add(p.getNombre() + " - $" + String.format("%.2f", item.getSubtotal()));
            total += item.getSubtotal();
        }

        lblTotal.setText(String.format("Total: $%.2f", total));
    }

    private void comprarSeleccionado() {
        int index = listaCarrito.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            mostrarAlerta("Error", "Selecciona un producto para comprar.");
            return;
        }

        var item = tienda.getCarrito().get(index);
        tienda.registrarHistorialClick(item.getProducto()); // historial correcto

        tienda.getCarrito().remove(index); // eliminar ítem real

        cargarCarrito();

        mostrarAlerta("Compra exitosa", "Producto comprado: " + item.getProducto().getNombre());
    }

    private void cambiarEscena(String fxml) {
        try {
            Stage stage = (Stage) listaCarrito.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/vista/" + fxml)));
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la escena: " + fxml);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
