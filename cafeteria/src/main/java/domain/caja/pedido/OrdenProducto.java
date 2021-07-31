package domain.caja.pedido;

import domain.menu.Producto;

public class OrdenProducto {
    private Producto producto;
    private int cantidad;

    public double precioTotal(){
        return producto.getPrecio() * cantidad;
    }

    /* Constructor */

    public OrdenProducto(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /* Getters y Setters */

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
