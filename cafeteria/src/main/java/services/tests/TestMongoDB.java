package services.tests;

import domain.caja.Caja;
import domain.caja.pedido.OrdenProducto;
import domain.caja.pedido.OrdenPromocion;
import domain.caja.pedido.Pedido;
import domain.menu.Menu;
import domain.menu.Producto;
import domain.menu.Promocion;
import services.mongoDB.MongoDBService;
import services.mongoDB.Recibo;

import javax.swing.text.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestMongoDB {
    public static void main(String[] args) {
        Menu menu = TestMongoDB.cargarMenu();

        OrdenProducto ordenProducto = new OrdenProducto();
        ordenProducto.setProducto(menu.getProductoById(0));
        ordenProducto.setCantidad(2);

        OrdenPromocion ordenPromocion = new OrdenPromocion();
        ordenPromocion.setPromocion(menu.getPromocionById(0));
        ordenPromocion.setCantidad(3);

        Pedido pedido = new Pedido();
        pedido.setDate(LocalDateTime.now());
        pedido.addProducto(ordenProducto);
        pedido.addPromocion(ordenPromocion);


        Caja caja = new Caja();
        caja.setPedido(pedido);

        Recibo recibo = new Recibo();
        recibo = recibo.crearRecibo(caja);

        Document documento = (Document) MongoDBService.crearDocumento(recibo);
        MongoDBService.insertarDocumento((org.bson.Document) documento);

    }

    public static Menu cargarMenu() {
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

        return Menu.getInstancia(productos, promociones);
    }


}
