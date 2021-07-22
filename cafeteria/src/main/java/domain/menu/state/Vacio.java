package domain.menu.state;

import domain.menu.Producto;

public class Vacio implements Estado {
    @Override
    public void renovarStock(Producto producto) {
        producto.setCantidadDisponible(10);
        producto.setEstadoStock(new Insuficiente());
    }
}