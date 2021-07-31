package domain.caja.strategy;

import domain.Tienda;

public class Efectivo implements CobrarStrategy {
    @Override
    public void cobrarPedido(Tienda tienda) {
        double montoRecaudado = tienda.getCaja().getPedido().precioTotal();
        tienda.agregarMontoRecaudado(montoRecaudado * 0.95); // Se aplica descuento del 5%
    }
    @Override
    public String toString(){
        return "Efectivo";
    }
}
