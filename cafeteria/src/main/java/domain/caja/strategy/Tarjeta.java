package domain.caja.strategy;

import domain.caja.Caja;

public class Tarjeta implements CobrarStrategy {
    @Override
    public void cobrar(Caja caja){
        double montoRecaudado = caja.calcularPrecioTotal();
        caja.sumarMontoRecaudado(montoRecaudado); // No se aplica descuento
    }
}
