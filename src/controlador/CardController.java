package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Producto;
import modelo.TiendaService;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {

    @FXML private ImageView imgProducto;
    @FXML private Label lblNombre;
    @FXML private Label lblPrecio;
    @FXML private Button btnFav;
    @FXML private Button btnCarrito;

    private Producto producto;

    private TiendaService tienda = TiendaService.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    public void setData(Producto p) {
        this.producto = p;

        lblNombre.setText(p.getNombre());
        lblPrecio.setText("$" + p.getPrecio());

        if (p.getImagen() != null) {
            try {
                Image img = new Image(getClass().getResourceAsStream(p.getImagen()));
                imgProducto.setImage(img);
            } catch (Exception e) {
                System.err.println("Error cargando imagen: " + p.getImagen());
            }
        }
    }

    @FXML
    private void agregarFavorito() {
        tienda.agregarFavorito(producto);
        System.out.println("✔ Favorito agregado: " + producto.getNombre());
    }

    @FXML
    private void agregarCarrito() {
        tienda.agregarAlCarrito(producto);
        System.out.println("✔ Carrito agregó: " + producto.getNombre());
    }
}
