package domain.caja;

import domain.caja.pedido.Pedido;
import domain.caja.strategy.CobrarStrategy;
import domain.menu.Promocion;

public class Caja {
    private Pedido pedido;
    private Moneda moneda;
    private CobrarStrategy metodoPago;
    private double montoRecaudado;

    public void cobrarPedido(Caja caja){
        metodoPago.cobrarPedido(this);
    }

    // TODO: Mirar como implemetar API
    public void mostrarMontoEnMonedaExtranjera(){
        System.out.println("A");
    }

    /* Getters y Setters */

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public void agregarMontoRecaudado(double montoRecaudado) {
        this.montoRecaudado += montoRecaudado;
    }
}
