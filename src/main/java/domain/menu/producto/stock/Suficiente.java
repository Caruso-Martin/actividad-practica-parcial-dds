package domain.menu.producto.stock;

import domain.menu.producto.Producto;

public class Suficiente implements StockState{
    @Override
    public void renovarStock(Producto producto) {
        System.out.println("No se renovara el stock, hay una cantidad suficiente.");
    }

    @Override
    public String toString(){
        return "Suficiente";
    }

    @Override
    public Integer toInt(){
        return 3;
    }
}
