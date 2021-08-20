package domain.menu.producto;

import domain.menu.producto.stock.StockState;

import java.sql.SQLException;

public interface Producto {
    void renovarStock(Producto producto) throws SQLException;
    void consumirStock(Integer cantidadConsumida) throws Exception;
    boolean estaDisponible();

    /* Getters y Setters */

    Integer getId();
    void setId(Integer id);

    String getNombre();
    void setNombre(String nombre);

    Double getPrecio();
    void setPrecio(Double precio);

    String getDescripcion();
    void setDescripcion(String descripcion);

    Integer getCantidadDisponible();
    void setCantidadDisponible(Integer cantidadDisponible);

    StockState getEstadoStock();
    void setEstadoStock(StockState estadoStock);

    Integer getPromocionPadreID();
    void setPromocionPadreID(Integer promocionPadreID);
}
