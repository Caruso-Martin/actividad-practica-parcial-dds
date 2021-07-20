package domain.caja.strategy;

import domain.caja.Caja;

public class Efectivo implements CobrarStrategy {
    @Override
    public void cobrar(Caja caja){
        double montoRecaudado = caja.calcularPrecioTotal();
        caja.sumarMontoRecaudado(montoRecaudado * 0.95); // Se aplica descuento del 5%
    }
}
