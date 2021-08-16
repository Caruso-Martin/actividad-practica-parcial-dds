package domain.menu;

import domain.menu.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Menu instancia = null;

    private List<Producto> productos = new ArrayList<Producto>();

    /* Constructor */

    // Singleton
    private Menu(List<Producto> productos) {
        this.productos = productos;
    }

    /* Getters y Setters */

    // Singleton
    public static Menu getInstancia(List<Producto> productos) {
        if(instancia == null)
                instancia = new Menu(productos);

        return instancia;
    }

    public void setInstancia(Menu instancia) {
        this.instancia = instancia;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
