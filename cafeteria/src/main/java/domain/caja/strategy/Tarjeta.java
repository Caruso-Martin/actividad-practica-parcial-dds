package domain.caja.strategy;

import domain.caja.Caja;

public class Tarjeta implements CobrarStrategy {
    @Override
    public void cobrarPedido(Caja caja) {
        double montoRecaudado = caja.getPedido().precioTotal();
        caja.agregarMontoRecaudado(montoRecaudado); // No se aplica descuento

        caja.getPedido().consumirStock();
        caja.setPedido(null);

        //TODO: renovarStock()
    }
}
