package domain.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Menu instancia = null;

    private List<Producto> productos = new ArrayList<Producto>();
    private List<Promocion> promociones = new ArrayList<Promocion>();

    /* Constructor */

    public Menu(List<Producto> productos, List<Promocion> promociones) {
        this.productos = productos;
        this.promociones = promociones;
    }                       // Patron Singleton

    /* Getters y Setters */

    public static Menu getInstancia(List<Producto> productos, List<Promocion> promociones) {
        if (instancia == null) {
            instancia = new Menu(productos, promociones);
        }
        return instancia;
    }   // Patron Singleton

    public List<Producto> getProductos() {
        return productos;
    }

    public Producto getProductoById(int idProducto) {
        return productos.get(idProducto);
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void removeProductoById(int idProducto) {
        productos.removeIf(p -> p.getId() == idProducto);
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public Promocion getPromocionById(int idPromocion) {
        return promociones.get(idPromocion);
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public void addPromocion(Promocion promocion) {
        this.promociones.add(promocion);
    }

    public void removePromocionById(int idPromocion) {
        promociones.removeIf(p -> p.getId() == idPromocion);
    }
}