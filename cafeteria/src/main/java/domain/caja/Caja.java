package domain.caja;

import domain.Tienda;
import domain.caja.pedido.OrdenProducto;
import domain.caja.pedido.OrdenPromocion;
import domain.caja.pedido.Pedido;
import domain.caja.strategy.CobrarStrategy;
import domain.caja.strategy.Efectivo;
import domain.caja.strategy.Tarjeta;
import domain.menu.Menu;
import domain.menu.Producto;
import domain.menu.Promocion;
import org.bson.Document;
import services.mongoDB.MongoDBService;
import services.mongoDB.Recibo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Caja {
    private static Caja instancia = null;

    private Pedido pedido;
    private Moneda moneda;
    private CobrarStrategy metodoPago;

    public void nuevoPedido() {
        // Formularios de carga de un recibo
        this.pedido = this.obtenerPedido();
        this.moneda = this.obtenerMoneda();
        this.metodoPago = this.obtenerMetodoPago();

        //metodoPago.cobrarPedido(this);

        // Guardar informacion de venta (recibo) en mongoDB
        Recibo recibo = new Recibo(this.pedido, this.moneda, this.metodoPago);
        Document reciboJSON = MongoDBService.mapearPOJOaJSON(recibo);
        MongoDBService.cargarRecibo(reciboJSON);

        // Auto-gestion de stock
        this.getPedido().consumirStock();
        this.getPedido().renovarStock();
    }

    private Moneda obtenerMoneda() {
        Scanner consola = new Scanner(System.in);
        Moneda moneda;

        System.out.println("Ingrese moneda de cambio:\n- 0: Pesos Argentinos\n- 1: Dolares Estadounidenses\n- 2: Euros");

        moneda = Moneda.fromInteger(consola.nextInt());

        while (!(moneda.equals(0) || moneda.equals(1) || moneda.equals(2))) {
            System.out.println("\nValor incorrecto, ingrese uno nuevo");
            moneda = Moneda.fromInteger(consola.nextInt());
        }

        return moneda;
    }

    private CobrarStrategy obtenerMetodoPago() {
        Scanner consola = new Scanner(System.in);
        CobrarStrategy metodoPago;

        System.out.println("Ingrese metodo de pago:\n- 0: Efectivo\n- 1: Tarjeta");

        metodoPago = (consola.nextInt() == 0) ? new Efectivo() : new Tarjeta();

        while (!(metodoPago.equals(0) || metodoPago.equals(1))) {
            System.out.println("\nValor incorrecto, ingrese uno nuevo: ");
            metodoPago = (consola.nextInt() == 0) ? new Efectivo() : new Tarjeta();
        }

        return metodoPago;
    }

    private Pedido obtenerPedido() {
        Scanner consola = new Scanner(System.in);
        //Menu menu = Menu.getInstancia();
        Menu menu = this.crearMenu();

        Pedido pedido = new Pedido();

        int idArticulo;
        int cantidad;

        // Obtener pedidos

        this.mostrarMenuProductos();

        System.out.println("\nIngresar productos deseados: ");

        do {
            idArticulo = consola.nextInt();

            System.out.println("\nIngrese cantidad deseada: ");
            cantidad = consola.nextInt();

            pedido.addOrdenProducto(new OrdenProducto(menu.getProductoById(idArticulo), cantidad));
        } while (idArticulo != -1); //TODO: Validar si ID existe

        // Obtener promociones

        this.mostrarMenuPromociones();

        System.out.println("\nIngresar promociones deseadas: ");

        do {
            idArticulo = consola.nextInt();

            System.out.println("\nIngrese cantidad deseada: ");
            cantidad = consola.nextInt();

            pedido.addOrdenPromocion(new OrdenPromocion(menu.getPromocionById(idArticulo), cantidad));
        } while (idArticulo != -1); //TODO: Validar si ID existe

        return pedido;
    }

    public void mostrarMenuProductos(){
        //Menu menu = Menu.getInstancia();
        Menu menu = this.crearMenu();

        System.out.println("\nMENU - Productos");

        menu.getProductos().forEach(p -> System.out.println("Codigo #" + p.getId() + ": " + p.getNombre() + "($" + p.getPrecio() + ")"));
        System.out.println("\nCodigo #-1: Para finalizar");
    }

    public void mostrarMenuPromociones(){
        //Menu menu = Menu.getInstancia();
        Menu menu = this.crearMenu();

        System.out.println("\nMENU - Promociones");

        menu.getPromociones().forEach(p -> System.out.println("Codigo #" + p.getId() + ": " + p.getNombre() + "($" + p.getPrecio() + ")"));
        System.out.println("\nCodigo #-1: Para finalizar");
    }

    public Menu crearMenu() {
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

        return  Menu.getInstancia(productos, promociones);
    }

    public void cobrarPedido(Tienda tienda){
        metodoPago.cobrarPedido(tienda);
    }

    // TODO: Mirar como implemetar API
    public void mostrarMontoEnMonedaExtranjera(){
        System.out.println("A");
    }

    /* Constructor */

    public Caja(Pedido pedido, Moneda moneda, CobrarStrategy metodoPago) {
        this.pedido = pedido;
        this.moneda = moneda;
        this.metodoPago = metodoPago;
    }   // Patron Singleton

    /* Getters y Setters */

    public static Caja getInstancia(Pedido pedido, Moneda moneda, CobrarStrategy metodoPago) {
        if (instancia == null) {
            instancia = new Caja(pedido, moneda, metodoPago);
        }
        return instancia;
    }   // Patron Singleton

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public CobrarStrategy getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(CobrarStrategy metodoPago) {
        this.metodoPago = metodoPago;
    }
}
