package domain.menu.producto.stock;

import domain.menu.producto.Producto;

public interface StockState {
    void renovarStock(Producto producto);
}
