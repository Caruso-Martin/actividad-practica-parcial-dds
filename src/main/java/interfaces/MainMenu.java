package interfaces;

import domain.caja.Caja;
import domain.caja.cotizacion.Peso;
import domain.menu.producto.ProductoSimple;
import domain.menu.producto.Promocion;
import domain.menu.producto.stock.StockState;
import services.InterfaceServices.InterfaceService;
import services.MySQLDataBase.MySQLService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    private static Scanner scanner;
    private static Caja caja = new Caja(new Peso());

    public static void main(String[] args) throws Exception {
        MySQLService mySQL = new MySQLService();
        mySQL.crearConexion();

        scanner = new Scanner(System.in);

        int operacionSeleccionada;
        do {
            System.out.println("======================== ¡BIENVENIDO AL MODULO 'MENU'! =======================");
            System.out.println("OPERACIONES DISPONIBLES");
            System.out.println("\t#1. CONSULTAR MENU DEL DIA\n\t#2. OPERACIONES CON PRODUCTOS\n\t#3. OPERACIONES CON PROMOCIONES\n\t#4. CAMBIAR MONEDA\n\t#5. VOLVER AL MODULO 'INICIAL'");
            System.out.println("INGRESE QUE OPERACION DECIDE REALIZAR: ");

            operacionSeleccionada = scanner.nextInt();
            scanner.nextLine(); // Consumir left-over

            InterfaceService.limpiarConsola();
            switch (operacionSeleccionada) {
                case 1:
                    InterfaceService.mostrarMenu(caja);
                    break;
                case 2:
                    MainMenu.operacionesPedido();
                    break;
                case 3:
                    MainMenu.operacionesPromocion();
                    break;
                case 4:
                    InterfaceService.cambiarMoneda(caja);
                    break;
                case 5://TODO: NO HACER
                    System.out.println("A");
                    break;
                default:
                    InterfaceService.validadorOperaciones(operacionSeleccionada);
                    break;
            }
            InterfaceService.limpiarConsola();
        } while(operacionSeleccionada != 5);
    }

    private static void operacionesPedido() throws Exception {
        int operacionSeleccionada;
        do {
            System.out.println("========================== #2. OPERACIONES PRODUCTO ==========================");
            System.out.println("OPERACIONES DISPONIBLES");
            System.out.println("\t#1. CREAR Y AGREGAR PRODUCTO NUEVO\n\t#2. ACTUALIZAR PRECIO PRODUCTO\n\t#3. VOLVER AL MENU ANTERIOR");
            System.out.println("INGRESE QUE OPERACION DECIDE REALIZAR: ");

            operacionSeleccionada = scanner.nextInt();
            scanner.nextLine(); // Consumir left-over

            InterfaceService.limpiarConsola();
            switch (operacionSeleccionada) {
                case 1:
                    System.out.println("------------------------------ CREACION PRODUCTO -----------------------------");
                    MySQLService.agregarProducto(MainMenu.crearProducto(), 1);
                    System.out.println("\n********************** PRODUCTO AGREGADO EXITOSAMENTE **********************");
                    break;
                case 2:
                    System.out.println("------------------------ ACTUALIZACION PRECIO PRODUCTO -----------------------");
                    InterfaceService.mostrarProductosMenu(caja);
                    System.out.println("------------------------------------------------------------------------------");

                    MainMenu.actualizarPrecio();
                    System.out.println("\n********************** PRECIO ACTUALIZADO EXITOSAMENTE *********************");
                    break;
                case 3:
                    break;
                default:
                    InterfaceService.validadorOperaciones(operacionSeleccionada);
                    break;
            }
            InterfaceService.limpiarConsola();
        } while(operacionSeleccionada != 3);
    }

    private static ProductoSimple crearProducto() throws Exception {
        ProductoSimple productoSimple = new ProductoSimple();

        System.out.println("INGRESE NOMBRE: ");
        productoSimple.setNombre(scanner.nextLine());

        System.out.println("INGRESE PRECIO: ");
        productoSimple.setPrecio(scanner.nextDouble());
        scanner.nextLine(); // Consumir left-over

        System.out.println("INGRESE DESCRIPCION: ");
        productoSimple.setDescripcion(scanner.nextLine());

        System.out.println("INGRESE CANTIDAD DISPONIBLE: ");
        productoSimple.setCantidadDisponible(scanner.nextInt());
        scanner.nextLine(); // Consumir left-over

        StockState stockState = ProductoSimple.estadoSegunCantidad(productoSimple.getCantidadDisponible());
        productoSimple.setEstadoStock(stockState);

        return productoSimple;
    }

    private static void actualizarPrecio() throws SQLException {
        System.out.println("INGRESE ID PRODUCTO: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine(); // Consumir left-over

        System.out.println("INGRESE NUEVO PRECIO: ");
        Double nuevoPrecio = scanner.nextDouble();
        scanner.nextLine(); // Consumir left-over

        MySQLService.actualizarPrecio(nuevoPrecio, idProducto);
    }

    private static void operacionesPromocion() throws Exception {
        int operacionSeleccionada;
        do {
            System.out.println("========================== #3. OPERACIONES PROMOCION =========================");
            System.out.println("OPERACIONES DISPONIBLES");
            System.out.println("\t#1. CREAR Y AGREGAR PROMOCION NUEVA\n\t#2. VOLVER AL MENU ANTERIOR");
            System.out.println("INGRESE QUE OPERACION DECIDE REALIZAR: ");

            operacionSeleccionada = scanner.nextInt();
            scanner.nextLine(); // Consumir left-over

            InterfaceService.limpiarConsola();
            switch (operacionSeleccionada) {
                case 1:
                    System.out.println("------------------------------ CREACION PROMOCION ----------------------------");
                    InterfaceService.mostrarProductosMenu(caja);
                    System.out.println("-------------------------------------------------------------------------------");

                    System.out.println("\n********************** (MAXIMO 5 PRODUCTOS POR PROMOCION) *********************");

                    MySQLService.agregarPromocion(MainMenu.crearPromocion(), 1);
                    System.out.println("\n*********************** PROMOCION AGREGADA EXITOSAMENTE ***********************");
                    break;
                case 2:
                    break;
                default:
                    InterfaceService.validadorOperaciones(operacionSeleccionada);
                    break;
            }
            InterfaceService.limpiarConsola();
        } while(operacionSeleccionada != 2);
    }

    private static Promocion crearPromocion() throws Exception {
        Promocion promocion = new Promocion();

        int cantidadProductos = 0;
        do {
            System.out.println("INGRESE ID DEL PRODUCTO A AGREGAR A PROMOCION: ");
            promocion.addProductoByID(scanner.nextInt());
            scanner.nextLine(); // Consumir left-over

            cantidadProductos++;

            System.out.println("¿DESEA INGRESAR OTRO PRODUCTO (S/N)?");
            if(scanner.nextLine().equals("N"))
                cantidadProductos = 6;
        } while (cantidadProductos < 5);

        return promocion;
    }

}
