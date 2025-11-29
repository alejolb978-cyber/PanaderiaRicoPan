package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialItem {

    private Producto producto;
    private int cantidad;
    private LocalDateTime fecha;

    // Constructor principal
    public HistorialItem(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    // Constructor vacío (opcional)
    public HistorialItem() {
        this.fecha = LocalDateTime.now();
    }

    // -------- GETTERS --------
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    // -------- SETTERS (solo si algún controlador los necesita) --------
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    // -------- FECHA FORMATEADA PARA MOSTRAR EN FXML --------
    public String getFechaFormateada() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fecha.format(formato);
    }
}
