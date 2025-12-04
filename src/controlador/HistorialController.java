package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import modelo.Producto;
import modelo.TiendaService;
import modelo.HistorialItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;

public class HistorialController implements Initializable {

    @FXML private GridPane gridHistorial;
    @FXML private Button btnCerrarHist;

    // Singleton
    private TiendaService tienda = TiendaService.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarHistorial();
        configurarBotonVolver();
    }

    private void cargarHistorial() {
        gridHistorial.getChildren().clear();

        int col = 0, row = 0;

        for (HistorialItem item : tienda.getHistorial()) {
            try {
                Producto p = item.getProducto();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/card.fxml"));
                AnchorPane card = loader.load();

                CardController controller = loader.getController();
                controller.setData(p);

                gridHistorial.add(card, col, row);

                col++;
                if (col == 3) {
                    col = 0;
                    row++;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void configurarBotonVolver() {
        btnCerrarHist.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/catalogo.fxml"));
                AnchorPane root = loader.load();

                Stage stage = (Stage) btnCerrarHist.getScene().getWindow();
                stage.setScene(new Scene(root));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
