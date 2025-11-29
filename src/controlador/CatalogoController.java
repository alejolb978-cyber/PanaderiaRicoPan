package controlador;

import controlador.CardController;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Producto;
import modelo.TiendaService;

public class CatalogoController implements Initializable {

    @FXML private GridPane gridProductos;
    @FXML private Button btnVerMas;
    @FXML private Label lblPagina;

    // Menu items
    @FXML private MenuButton menuNav;
    @FXML private MenuItem itemCarrito;
    @FXML private MenuItem itemFavoritos;
    @FXML private MenuItem itemHistorial;
    @FXML private MenuItem itemCerrarSesion;

    private TiendaService tienda = TiendaService.getInstance();

    private int paginaActual = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarPagina();

        // Botón ver más (pasa al catalogoSig)
        btnVerMas.setOnAction(e -> pagina2());

        // Acciones del menú 
        itemCarrito.setOnAction(e -> cargarVentana("/vista/carrito.fxml"));
        itemFavoritos.setOnAction(e -> cargarVentana("/vista/favoritos.fxml"));
        itemHistorial.setOnAction(e -> cargarVentana("/vista/Historial.fxml"));
        itemCerrarSesion.setOnAction(e -> cargarVentana("/vista/login.fxml"));
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
    private void pagina2() {
        cargarVentana("/vista/catalogoSig.fxml");
    }

    private void cargarVentana(String rutaFXML) {
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
