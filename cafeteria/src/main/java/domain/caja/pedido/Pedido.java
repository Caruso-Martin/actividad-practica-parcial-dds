package domain.caja.pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private LocalDate date;
    private List<OrdenProducto> productos = new ArrayList<OrdenProducto>();
    private List<OrdenPromocion> promociones = new ArrayList<OrdenPromocion>();

    public double precioTotal(){
        double precioTotalProductos = productos.stream().mapToDouble(p -> p.precioTotal()).sum();
        double precioTotalPromociones = promociones.stream().mapToDouble(p -> p.precioTotal()).sum();

        return precioTotalProductos + precioTotalPromociones;
    }

    public void consumirStock(){
        productos.forEach(p -> p.getProducto().consumirStock(p.getProducto(), p.getCantidad()));
        promociones.forEach(p -> p.getPromocion().consumirStock(p.getPromocion(), p.getCantidad()));
    }

    /* Getters y Setters */

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<OrdenProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<OrdenProducto> productos) {
        this.productos = productos;
    }

    public void addProducto(OrdenProducto producto) {
        this.productos.add(producto);
    }

    public List<OrdenPromocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<OrdenPromocion> promociones) {
        this.promociones = promociones;
    }

    public void addPromocion(OrdenPromocion promocion) {
        this.promociones.add(promocion);
    }
}
