package domain.menu.producto.stock;

import domain.menu.producto.Producto;

public class Vacio implements StockState{
    @Override
    public void renovarStock(Producto producto) {
        producto.setCantidadDisponible(20);
        producto.setEstadoStock(new Suficiente());
    }
}
