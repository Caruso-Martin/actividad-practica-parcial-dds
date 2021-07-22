package domain.menu;

import domain.Tienda;
import domain.menu.state.Estado;
import domain.menu.state.Suficiente;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private int cantidadDisponible;
    private Estado estadoStock;

    public void renovarStock(Producto producto) throws Exception {
        estadoStock.renovarStock(this);
    }

    public boolean estaDisponible() {
        return cantidadDisponible > 0;
    }

    public void consumirStock(Producto producto, int cantidad){
        producto.restarCantidadDisponible(cantidad);
    }

    /* Constructor */

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = 25;
        this.estadoStock = new Suficiente();
    }

    /* Getters y Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void restarCantidadDisponible(int cantidad) {
        this.cantidadDisponible -= cantidad;
    }

    public Estado getEstadoStock() {
        return estadoStock;
    }

    public void setEstadoStock(Estado estadoStock) {
        this.estadoStock = estadoStock;
    }
}
