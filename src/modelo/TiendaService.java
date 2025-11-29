package modelo;

import java.util.ArrayList;

public class TiendaService {

    private static TiendaService instance;

    private ArrayList<Producto> catalogo;
    private ArrayList<ItemCarrito> carrito;
    private ArrayList<Producto> favoritos;
    private ArrayList<HistorialItem> historial;

    private TiendaService() {
        catalogo = new ArrayList<>();
        carrito = new ArrayList<>();
        favoritos = new ArrayList<>();
        historial = new ArrayList<>();

        cargarProductos();
    }

    public static TiendaService getInstance() {
        if (instance == null) {
            instance = new TiendaService();
        }
        return instance;
    }

    // ---------------------------
    // CARGAR PRODUCTOS
    // ---------------------------
    private void cargarProductos() {

        catalogo.add(new Producto("P1",  "Pan Chino",       800,  "/Resources/imagenes/chino.jpg"));
        catalogo.add(new Producto("P2",  "Pan Koreano",    1000, "/Resources/imagenes/coreano.jpg"));
        catalogo.add(new Producto("P3",  "Pan Croata",       1500, "/Resources/imagenes/croata.jpg"));
        catalogo.add(new Producto("P4",  "Pan Finlandés",    1200, "/Resources/imagenes/finla.jpg"));
        catalogo.add(new Producto("P5",  "Pan Holandes ",        2000, "/Resources/imagenes/holandes.jpg"));
        catalogo.add(new Producto("P6",  "Pan Italiano",       900,  "/Resources/imagenes/italia.jpg"));
        catalogo.add(new Producto("P7",  "Pan Japones",1800, "/Resources/imagenes/japon.jpg"));
        catalogo.add(new Producto("P8",  "Pan Lituano",    2500, "/Resources/imagenes/lituan.jpg"));
        catalogo.add(new Producto("P9",  "Pan Mexicano",   3000, "/Resources/imagenes/mex.jpg"));
        catalogo.add(new Producto("P10", "Pan Nepali",1100, "/Resources/imagenes/nepal.jpg"));
        catalogo.add(new Producto("P11", "Pan Noruego",     1300, "/Resources/imagenes/noruego.jpg"));
        catalogo.add(new Producto("P12", "Pan Portugues ",       1600, "/Resources/imagenes/portugues.jpg"));
    }

    // ---------------------------
    // GETTERS
    // ---------------------------
    public ArrayList<Producto> getCatalogo() { return catalogo; }
    public ArrayList<ItemCarrito> getCarrito() { return carrito; }
    public ArrayList<Producto> getFavoritos() { return favoritos; }
    public ArrayList<HistorialItem> getHistorial() { return historial; }

    // ---------------------------
    // FAVORITOS
    // ---------------------------
    public void agregarFavorito(Producto p) {
        if (!favoritos.contains(p)) favoritos.add(p);
    }

    public void quitarFavorito(Producto p) {
        favoritos.remove(p);
    }

    public boolean esFavorito(Producto p) {
        return favoritos.contains(p);
    }

    // ---------------------------
    // CARRITO
    // ---------------------------
    public void agregarAlCarrito(Producto p) {

        for (ItemCarrito item : carrito) {
            if (item.getProducto().getCodigo().equals(p.getCodigo())) {
                item.setCantidad(item.getCantidad() + 1);
                return;
            }
        }
        carrito.add(new ItemCarrito(p, 1));
    }

    public void eliminarDelCarrito(Producto p) {
        carrito.removeIf(item -> item.getProducto().equals(p));
    }

    public double getTotalCarrito() {
        double total = 0;
        for (ItemCarrito item : carrito) {
            total += item.getSubtotal();
        }
        return total;
    }

    // ---------------------------
    // HISTORIAL MODERNO (HistorialItem)
    // ---------------------------
    public void registrarHistorialClick(Producto p) {
        historial.add(0, new HistorialItem(p, 1));
    }

    public void registrarCompra() {
        for (ItemCarrito item : carrito) {
            historial.add(0, new HistorialItem(item.getProducto(), item.getCantidad()));
        }
        carrito.clear();
    }

    // ---------------------------
    // PAGINACIÓN
    // ---------------------------
    public ArrayList<Producto> getPagina(int pagina) {
        int inicio = (pagina - 1) * 6;
        int fin = Math.min(inicio + 6, catalogo.size());
        return new ArrayList<>(catalogo.subList(inicio, fin));
    }

    public int getTotalPaginas() {
        return (int) Math.ceil(catalogo.size() / 6.0);
    }
}
