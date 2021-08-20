package domain.menu.producto;

import domain.menu.producto.stock.Insuficiente;
import domain.menu.producto.stock.StockState;
import domain.menu.producto.stock.Suficiente;
import domain.menu.producto.stock.Vacio;
import services.MySQLDataBase.MySQLService;

import java.sql.SQLException;

public class ProductoSimple implements Producto {
    private Integer id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private Integer cantidadDisponible;
    private StockState estadoStock;
    private Integer promocionPadreID;

    @Override
    public void renovarStock(Producto producto) throws SQLException {
        estadoStock.renovarStock(this);
        MySQLService.actualizarStock(cantidadDisponible, estadoStock.toInt(), id);
    }

    @Override
    public void consumirStock(Integer cantidadConsumida) throws Exception {
        if(cantidadDisponible >= cantidadConsumida) {
            this.setCantidadDisponible(cantidadDisponible - cantidadConsumida);
            this.setEstadoStock(this.estadoSegunCantidad(cantidadDisponible));

            MySQLService.actualizarStock(cantidadDisponible, estadoStock.toInt(), id);
        } else {
            throw new Exception("No hay suficiente #" + id + " - " + nombre + "\nCantidad disponible actualmente: " + cantidadDisponible);
        }
    }

    public static StockState estadoSegunCantidad(Integer cantidadActual) throws Exception {
        StockState stockState = null;

        if(cantidadActual == 0)
            stockState = new Vacio();

        if(cantidadActual > 0 && cantidadActual < 20)
            stockState = new Insuficiente();

        if(cantidadActual >= 20)
            stockState = new Suficiente();

        return stockState;
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

    @Override
    public Integer getPromocionPadreID() {
        return promocionPadreID;
    }

    @Override
    public void setPromocionPadreID(Integer promocionPadreID) {
        this.promocionPadreID = promocionPadreID;
    }
}
