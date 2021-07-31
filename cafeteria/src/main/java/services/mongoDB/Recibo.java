package services.mongoDB;

import domain.caja.Moneda;
import domain.caja.pedido.Pedido;
import domain.caja.strategy.CobrarStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Recibo {
    private LocalDateTime date;
    private List<Articulo> articulos;
    private String moneda;
    private String metodoPago;
    private double subtotal;
    private double total;

    public double calcularPrecioArticulos(){
        return articulos.stream().mapToDouble(Articulo :: getPrecioOrden).sum();
    }

    public List<Articulo> aListaArticulos(Pedido pedido) {
        List <Articulo> articulos = new ArrayList<Articulo>();

        pedido.getOrdenesProductos()
              .forEach(p -> articulos.add(new Articulo(
                                                        p.getProducto().getNombre(),
                                                        p.getProducto().getPrecio(),
                                                        p.getCantidad()))
                                                      );
        pedido.getOrdenesPromociones()
              .forEach(p -> articulos.add(new Articulo(
                                                        p.getPromocion().getNombre(),
                                                        p.getPromocion().getPrecio(),
                                                        p.getCantidad()))
                                                      );

        return articulos;
    }

    /* Constructor */

    public Recibo(Pedido pedido, Moneda moneda, CobrarStrategy metodoPago) {
        this.date = LocalDateTime.now();
        this.articulos = this.aListaArticulos(pedido);
        this.moneda = moneda.toString();
        this.metodoPago = metodoPago.toString();
        this.subtotal = this.calcularPrecioArticulos();
        //this.total = this.calcularPrecioArticulos() - metodoPago.aplicarDescuento(); // modificar
    }

    /* Getters y Setters */

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void addArticulo(Articulo articulo) {
        this.articulos.add(articulo);
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
