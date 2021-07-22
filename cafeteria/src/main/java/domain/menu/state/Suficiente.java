package domain.menu.state;

import domain.menu.Producto;

public class Suficiente implements Estado {
    @Override
    public void renovarStock(Producto producto) throws Exception {
        throw new Exception("Solo se puede renovar stock si la canidad es 'insuficiente' o 'vacio'.");
    }
}
