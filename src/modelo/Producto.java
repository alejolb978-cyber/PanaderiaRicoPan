package modelo;

public class Producto {

    private String codigo;
    private String nombre;
    private double precio;
    private String imagen;  // Ruta interna del recurso: /Resources/imagenes/archivo.jpg

    public Producto(String codigo, String nombre, double precio, String imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = normalizarRuta(imagen);
    }

    // --- Getters ---
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    // --- Setters ---
    public void setImagen(String imagen) {
        this.imagen = normalizarRuta(imagen);
    }

    // --- Método para asegurar que la ruta es válida para JavaFX ---
    private String normalizarRuta(String ruta) {
        if (ruta == null) return null;

        // Si ya comienza con "/" es válida como recurso
        if (ruta.startsWith("/")) return ruta;

        // Si el usuario escribió solo el nombre del archivo
        if (!ruta.contains("/")) {
            return "/Resources/imagenes/" + ruta;
        }

        // Caso general → convertir a recurso
        return "/" + ruta.replace("\\", "/");
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}
