package services.InterfaceServices;

import domain.caja.Caja;
import domain.menu.producto.Producto;
import services.MySQLDataBase.MySQLService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InterfaceService {
    private static Scanner scanner = new Scanner(System.in);

    public static void limpiarConsola() {
        System.out.println("\r\n");
    }

    public static void mostrarMenu(Caja caja) throws SQLException {
        System.out.println("======================== #1. MENU DEL DIA - 'CAFETIN' ========================");

        System.out.println("-------------------------------- A. PRODUCTOS --------------------------------");
        InterfaceService.mostrarProductosMenu(caja);

        System.out.println("\n------------------------------- B. PROMOCIONES ------------------------------");
        InterfaceService.mostrarPromocionesMenu(caja);
    }

    public static void mostrarPromocionesMenu(Caja caja) throws SQLException {
        List<Producto> promociones = MySQLService.obtenerPromociones(1);

        promociones.forEach(p -> {
            try {
                System.out.printf("ID: %2d - %22s - Precio: $%7.2f - Cantidad disponible: %2d\n", p.getId(), p.getNombre(), caja.getMonedaPago().cotizar(p.getPrecio()), p.getCantidadDisponible());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void mostrarProductosMenu(Caja caja) throws SQLException {
        List<Producto> productos = MySQLService.obtenerProductos(1);

        productos.forEach(p -> {
            try {
                System.out.printf("ID: %2d - %22s - Precio: $%7.2f - Cantidad disponible: %2d\n", p.getId(), p.getNombre(), caja.getMonedaPago().cotizar(p.getPrecio()), p.getCantidadDisponible());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void cambiarMoneda(Caja caja) {
        System.out.println("============================ #3. CAMBIO DE MONEDA ============================");
        System.out.println("MONEDAS DISPONIBLES (ACTUALMENTE EN: " + caja.getMonedaPago().toString() + ")");
        System.out.println("\t#1. PESOS\n\t#2. DOLARES\n\t#3. EUROS");

        caja.setMonedaPagoByID(scanner.nextInt());
        scanner.nextLine(); // Consumir left-over
    }

    public static void validadorOperaciones(Integer operacionSeleccionada) {
        System.out.println("**** OPERACION #" + operacionSeleccionada + " NO EXISTE ****\n");
    }
}
