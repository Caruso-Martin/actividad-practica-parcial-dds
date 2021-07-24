package services.mongoDB;

import domain.caja.Caja;
import domain.caja.Moneda;
import domain.caja.pedido.Pedido;
import domain.caja.strategy.CobrarStrategy;

import java.time.LocalDateTime;
import java.util.List;

public class Recibo {
    private LocalDateTime date;
    private Moneda moneda;
    private CobrarStrategy metodoPago;
    private double subtotal;
    private double total;
    private List<Articulo> articulos;

    public Recibo crearRecibo(Caja caja){
        Pedido pedido = caja.getPedido();
        Recibo recibo = new Recibo();

        recibo.setDate(LocalDateTime.now());
        recibo.setMoneda(caja.getMoneda());
        recibo.setMetodoPago(caja.getMetodoPago());
        recibo.setSubtotal(pedido.precioTotal());
        recibo.setTotal(pedido.precioTotal());

        pedido.getProductos().stream().forEach(p -> recibo.addArticulo(Articulo.crearAtriculo(p)) );
        pedido.getPromociones().stream().forEach(p -> recibo.addArticulo(Articulo.crearAtriculo(p)) );

        return recibo;
    }

    /* Getters y Setters */

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void addArticulo(Articulo articulo) {
        this.articulos.add(articulo);
    }
}
