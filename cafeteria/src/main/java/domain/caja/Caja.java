package domain.caja;

import domain.caja.strategy.CobrarStrategy;
import domain.menu.Producto;
import domain.menu.Promocion;

import java.util.ArrayList;
import java.util.List;

public class Caja {
    private List<Producto> productos = new ArrayList<Producto>();
    private List<Promocion> promociones = new ArrayList<Promocion>();
    private Moneda moneda;
    private CobrarStrategy metodoPago;
    private double montoRecaudado;

    public void cobrar(Caja caja){
        metodoPago.cobrar(this);
    }

    public double calcularPrecioTotal(){
        double precioTotalProductos = productos.stream().mapToDouble(p -> p.getPrecio()).sum();
        double precioTotalPromociones = promociones.stream().mapToDouble(p -> p.getPrecio()).sum();

        return precioTotalProductos + precioTotalPromociones;
    }

    //TODO: RESTAR "cantidadDisponible" DE UN PRODUCTO

    //TODO: CAMBIO DE MONEDA

    /* Getters y Setters */

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public void addPromocion(Promocion promocion) {
        this.promociones.add(promocion);
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public CobrarStrategy getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(CobrarStrategy metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMontoRecaudado() {
        return montoRecaudado;
    }

    public void setMontoRecaudado(double montoRecaudado) {
        this.montoRecaudado = montoRecaudado;
    }

    public void sumarMontoRecaudado(double montoRecaudado) {
        this.montoRecaudado += montoRecaudado;
    }
}
