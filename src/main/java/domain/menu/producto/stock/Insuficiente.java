package domain.menu.producto.stock;

import domain.menu.producto.Producto;

public class Insuficiente implements StockState{
    @Override
    public void renovarStock(Producto producto) {
        int stockActual = producto.getCantidadDisponible();
        producto.setCantidadDisponible(stockActual * 2);

        if(producto.getCantidadDisponible() <= 20)
            producto.setEstadoStock(new Suficiente());
    }
}
