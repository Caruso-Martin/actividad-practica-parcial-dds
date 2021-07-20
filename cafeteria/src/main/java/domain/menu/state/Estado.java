package domain.menu.state;

import domain.Tienda;
import domain.menu.Producto;

public interface Estado {
    void renovarStock(Producto producto) throws Exception;
}
