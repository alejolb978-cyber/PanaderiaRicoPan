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
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FavoritosController implements Initializable {

    @FXML private GridPane gridFavoritos;
    @FXML private Button btnCerrarFav;

    private TiendaService tienda = TiendaService.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarFavoritos();

        btnCerrarFav.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/catalogo.fxml"));
                AnchorPane root = loader.load();

                Stage stage = (Stage) btnCerrarFav.getScene().getWindow();
                stage.getScene().setRoot(root);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void cargarFavoritos() {
        gridFavoritos.getChildren().clear();

        int col = 0, row = 0;
        for (Producto p : tienda.getFavoritos()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/card.fxml"));
                AnchorPane card = loader.load();

                CardController controller = loader.getController();
                controller.setData(p);

                gridFavoritos.add(card, col, row);

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
}
