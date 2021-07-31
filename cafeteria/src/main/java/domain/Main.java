package domain;

import domain.caja.Caja;
import domain.menu.Menu;
import domain.menu.Producto;
import domain.menu.Promocion;

import java.util.ArrayList;
import java.util.List;

public class Main {
   public static void main(String[] args) {
        Producto americano  = new Producto(0, "Americano", 100);
        Producto espresso   = new Producto(1, "Espresso", 120);
        Producto capuccino  = new Producto(2, "Capuccino", 150);
        Producto latte      = new Producto(3, "Latte", 180);
        Producto moka       = new Producto(4, "Moka", 200);
        Producto macchiato  = new Producto(5, "Macchiato", 220);

        Promocion promoEconomica = new Promocion(0, "Promocion Economica", 0);
        promoEconomica.addProducto(americano);
        promoEconomica.addProducto(espresso);

        Promocion promoCara = new Promocion(1, "Promocion Cara", 0);
        promoCara.addProducto(moka);
        promoCara.addProducto(macchiato);

        List<Producto> productos = new ArrayList<Producto>();
        productos.add(americano);
        productos.add(espresso);
        productos.add(capuccino);
        productos.add(latte);
        productos.add(moka);
        productos.add(macchiato);

        List<Promocion> promociones = new ArrayList<Promocion>();
        promociones.add(promoCara);
        promociones.add(promoEconomica);

        Menu menu = Menu.getInstancia(productos, promociones);
       // Caja caja = new Caja();

        //Tienda tienda = new Tienda(menu, caja);
    }
}
