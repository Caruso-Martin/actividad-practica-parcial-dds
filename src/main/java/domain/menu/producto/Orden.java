package domain.menu.producto;

public class Orden {
    private Producto producto;
    private Integer cantidad;

    public Double getPrecio() {
        return producto.getPrecio() * cantidad;
    }

    /* Getters y Setters */

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}

