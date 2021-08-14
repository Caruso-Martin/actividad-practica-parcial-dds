package domain.menu.producto;

import domain.menu.producto.stock.Insuficiente;
import domain.menu.producto.stock.StockState;
import domain.menu.producto.stock.Suficiente;
import domain.menu.producto.stock.Vacio;

public class ProductoSimple implements Producto {
    private Integer id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private Integer cantidadDisponible;
    private StockState estadoStock;

    @Override
    public void renovarStock(Producto producto) {
        estadoStock.renovarStock(this);
    }

    @Override
    public void consumirStock(Integer cantidadConsumida) throws Exception {
        this.setCantidadDisponible(cantidadDisponible - cantidadConsumida);
        this.setEstadoStock(this.estadoSegunCantidad(cantidadDisponible));
    }

    private StockState estadoSegunCantidad(Integer cantidadActual) throws Exception {
        if(cantidadActual == 0)
            return new Vacio();

        if(cantidadActual > 0 && cantidadActual < 20)
            return new Insuficiente();

        if(cantidadActual >= 20)
            return new Suficiente();

        throw new Exception("El valor del stock es negativo.");
    }

    @Override
    public boolean estaDisponible() {
        return this.cantidadDisponible > 0;
    }

    /* Getters y Setters */

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    @Override
    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public StockState getEstadoStock() {
        return estadoStock;
    }

    @Override
    public void setEstadoStock(StockState estadoStock) {
        this.estadoStock = estadoStock;
    }
}
