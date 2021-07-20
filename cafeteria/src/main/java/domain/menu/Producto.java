package domain.menu;

import domain.Tienda;
import domain.menu.state.Estado;

public class Producto {
    private String nombre;
    private double precio;
    private String descripcion;
    private int cantidadDisponible;
    private Estado estadoStock;

    public void renovarStock(Producto producto) throws Exception {
        estadoStock.renovarStock(this);
    }

    /* Getters y Setters */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Estado getEstadoStock() {
        return estadoStock;
    }

    public void setEstadoStock(Estado estadoStock) {
        this.estadoStock = estadoStock;
    }
}
