package services.mongoDB;

import domain.caja.pedido.OrdenProducto;
import domain.caja.pedido.OrdenPromocion;

public class Articulo {
    private String nombre;
    private double precioUnidad;
    private int cantidad;

    public static Articulo crearAtriculo(OrdenProducto producto){
        Articulo articulo = new Articulo();

        articulo.setNombre(producto.getProducto().getNombre());
        articulo.setPrecioUnidad(producto.getProducto().getPrecio());
        articulo.setCantidad(producto.getCantidad());

        return articulo;
    }

    public static Articulo crearAtriculo(OrdenPromocion promocion){
        Articulo articulo = new Articulo();

        articulo.setNombre(promocion.getPromocion().getNombre());
        articulo.setPrecioUnidad(promocion.getPromocion().getPrecio());
        articulo.setCantidad(promocion.getCantidad());

        return articulo;
    }

    /* Getters y Setters */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }
}
