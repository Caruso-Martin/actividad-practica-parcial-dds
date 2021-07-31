package domain.caja.strategy;

import domain.Tienda;

public class Tarjeta implements CobrarStrategy {
    @Override
    public void cobrarPedido(Tienda tienda) {
        double montoRecaudado = tienda.getCaja().getPedido().precioTotal();
        tienda.agregarMontoRecaudado(montoRecaudado); // No se aplica descuento del 5%
    }
    @Override
    public String toString(){
        return "Tarjeta";
    }
}
