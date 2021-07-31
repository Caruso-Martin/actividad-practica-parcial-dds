package domain.caja.strategy;

import domain.Tienda;

public interface CobrarStrategy {
    void cobrarPedido(Tienda tienda);
    String toString();
}
