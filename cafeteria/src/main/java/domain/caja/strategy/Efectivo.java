package domain.caja.strategy;

import domain.caja.Caja;

public class Efectivo implements CobrarStrategy {
    @Override
    public void cobrarPedido(Caja caja) {
        double montoRecaudado = caja.getPedido().precioTotal();
        caja.agregarMontoRecaudado(montoRecaudado * 0.95); // Se aplica descuento del 5%

        caja.getPedido().consumirStock();
        caja.setPedido(null);

        //TODO: renovarStock()

    }
}
