package domain.menu.producto;

import services.MySQLDataBase.MySQLService;

import java.sql.SQLException;

public class Orden {
    private Producto producto;
    private Integer cantidad;

    public  Double getPrecio() {
        return producto.getPrecio() * cantidad;
    }

    /* Getters y Setters */

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setProductoByID(Integer idProducto) throws SQLException {
        this.producto = MySQLService.obtenerProducto(idProducto);
    }

    public void setPromocionByID(Integer idPromocion) throws SQLException {
        this.producto = MySQLService.obtenerPromocion(idPromocion);
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}

