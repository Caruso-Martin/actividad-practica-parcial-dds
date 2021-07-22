package domain.menu.state;

import domain.menu.Producto;

public class Insuficiente implements Estado {
    @Override
    public void renovarStock(Producto producto){
        int stockActual = producto.getCantidadDisponible();
        producto.setCantidadDisponible(stockActual * 2);

        if(producto.getCantidadDisponible() <= 20)
            producto.setEstadoStock(new Suficiente());
    }
}
