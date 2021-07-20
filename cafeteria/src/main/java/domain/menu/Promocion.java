package domain.menu;

import java.util.ArrayList;
import java.util.List;

public class Promocion extends Producto {
    List<Producto> productos = new ArrayList<Producto>();

    public double getPrecio(){ // Una promocion vale la suma de sus productos, menos un 10% de descuento
        return productos.stream().mapToDouble(p -> p.getPrecio()).sum() * 0.90;
    }

    /* Getters y Setters */

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }
}
