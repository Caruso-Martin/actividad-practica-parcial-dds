package domain.menu;

import domain.menu.state.Estado;
import domain.menu.state.Vacio;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private int cantidadDisponible;
    private Estado estadoStock;

    public boolean estaDisponible() {
        return cantidadDisponible > 0;
    }

    public void renovarStock() throws Exception {
        estadoStock.renovarStock(this); // TODO: Modificar, la Excepcion causa problemas.

    }

    public void consumirStock(Producto producto, int cantidad){
        producto.restarCantidadDisponible(cantidad);
        //TODO: cambiar estado
    }

    /* Constructor */

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = 0;
        this.estadoStock = new Vacio();
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
