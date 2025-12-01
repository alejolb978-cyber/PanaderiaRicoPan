package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Producto;
import modelo.TiendaService;

public class CatalogoSigController implements Initializable {

    @FXML private GridPane gridProductos;
    @FXML private Button btnVolver;
    @FXML private Label lblPagina;

    // Menu items
    @FXML private MenuItem itemCarrito;
    @FXML private MenuItem itemFavoritos;
    @FXML private MenuItem itemHistorial;
    @FXML private MenuItem itemCerrar;

   private TiendaService tienda = TiendaService.getInstance();

    private int paginaActual = 2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarPagina();

        btnVolver.setOnAction(e -> pagina1());

        itemCarrito.setOnAction(e -> abrirVentana("/vista/carrito.fxml"));
        itemFavoritos.setOnAction(e -> abrirVentana("/vista/favoritos.fxml"));
        itemHistorial.setOnAction(e -> abrirVentana("/vista/Historial.fxml"));
        itemCerrar.setOnAction(e -> abrirVentana("/vista/login.fxml")); // o pantalla de inicio
    }

    private void cargarPagina() {

        gridProductos.getChildren().clear();

        int col = 0, row = 0;

        for (Producto p : tienda.getPagina(paginaActual)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/card.fxml"));
                AnchorPane card = loader.load();

                CardController controller = loader.getController();
                controller.setData(p);

                gridProductos.add(card, col, row);

                col++;
                if (col == 3) {
                    col = 0;
                    row++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        lblPagina.setText("Página " + paginaActual);
    }

    @FXML
    private void pagina1() {
        abrirVentana("/vista/catalogo.fxml");
    }

    private void abrirVentana(String rutaFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaFXML));
            Stage stage = (Stage) gridProductos.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.err.println("ERROR cargando ventana: " + rutaFXML);
            ex.printStackTrace();
        }
    }
}
