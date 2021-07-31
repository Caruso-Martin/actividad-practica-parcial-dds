package domain.caja.pedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<OrdenProducto> ordenesProductos = new ArrayList<OrdenProducto>();
    private List<OrdenPromocion> ordenesPromociones = new ArrayList<OrdenPromocion>();

    public double precioTotal(){
        double precioTotalProductos = ordenesProductos.stream().mapToDouble(p -> p.precioTotal()).sum();
        double precioTotalPromociones = ordenesPromociones.stream().mapToDouble(p -> p.precioTotal()).sum();

        return precioTotalProductos + precioTotalPromociones;
    }

    public void renovarStock() {
        // TODO: renovarStock()
    }

    public void consumirStock(){
        ordenesProductos.forEach(p -> p.getProducto().consumirStock(p.getProducto(), p.getCantidad()));
        ordenesPromociones.forEach(p -> p.getPromocion().consumirStock(p.getPromocion(), p.getCantidad()));
    }

    /* Getters y Setters */

    public List<OrdenProducto> getOrdenesProductos() {
        return ordenesProductos;
    }

    public void setOrdenesProductos(List<OrdenProducto> ordenesProductos) {
        this.ordenesProductos = ordenesProductos;
    }

    public void addOrdenProducto(OrdenProducto ordenProducto) {
        this.ordenesProductos.add(ordenProducto);
    }

    public List<OrdenPromocion> getOrdenesPromociones() {
        return ordenesPromociones;
    }

    public void setOrdenesPromociones(List<OrdenPromocion> ordenesPromociones) {
        this.ordenesPromociones = ordenesPromociones;
    }

    public void addOrdenPromocion(OrdenPromocion ordenPromocion) {
        this.ordenesPromociones.add(ordenPromocion);
    }
}
