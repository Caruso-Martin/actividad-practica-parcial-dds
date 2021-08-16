package interfaces;

import domain.caja.Caja;
import domain.caja.cotizacion.Peso;
import domain.menu.producto.Orden;
import services.InterfaceServices.InterfaceService;
import services.MySQLDataBase.MySQLService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MainCaja {
    private static Scanner scanner;
    private static Caja caja = new Caja(new Peso());

    public static void main(String[] args) throws SQLException, IOException {
        MySQLService mySQL = new MySQLService();
        mySQL.crearConexion();

        scanner = new Scanner(System.in);

        int operacionSeleccionada;
        do {
            System.out.println("======================== ¡BIENVENIDO AL MODULO 'CAJA'! =======================");
            System.out.println("OPERACIONES DISPONIBLES");
            System.out.println("\t#1. CONSULTAR MENU DEL DIA\n\t#2. CREAR PEDIDO\n\t#3. CAMBIAR MONEDA\n\t#4. VOLVER AL MODULO 'INICIAL'");
            System.out.println("INGRESE QUE OPERACION DECIDE REALIZAR: ");

            operacionSeleccionada = scanner.nextInt();
            scanner.nextLine(); // Consumir left-over

            InterfaceService.limpiarConsola();
            switch (operacionSeleccionada) {
                case 1:
                    InterfaceService.mostrarMenu(caja);
                    break;
                case 2:
                    MainCaja.crearPedido();
                    break;
                case 3:
                    InterfaceService.cambiarMoneda(caja);
                    break;
                case 4:
                    System.out.println("A");//TODO: NO HACER
                    break;
                default:
                    InterfaceService.validadorOperaciones(operacionSeleccionada);
                    break;
            }
            InterfaceService.limpiarConsola();
        } while(operacionSeleccionada != 4);
    }

    private static void crearPedido() throws SQLException, IOException {
        int operacionSeleccionada;
        int numeroOrden = 0;
        do {
            System.out.println("============================= #2. CREACION PEDIDO ============================");

            System.out.println("OPERACIONES DISPONIBLES");
            System.out.println("\t#1. AGREGAR PRODUCTO\n\t#2. AGREGAR PROMOCION\n\t#3. ELIMINAR ORDEN\n\t#4. CALCULAR PRECIO PEDIDO\n\t#5. CANCELAR PEDIDO\n\t#6. COBRAR PEDIDO\n\t#7. VOLVER AL MENU ANTERIOR");
            System.out.println("INGRESE QUE OPERACION DECIDE REALIZAR: ");

            operacionSeleccionada = scanner.nextInt();
            scanner.nextLine(); // Consumir left-over

            InterfaceService.limpiarConsola();
            switch (operacionSeleccionada) {
                case 1:
                    System.out.println("--------------------------------- CREAR ORDEN --------------------------------");
                    InterfaceService.mostrarProductosMenu(caja);
                    System.out.println("------------------------------------------------------------------------------\n");

                    System.out.println("ORDEN #" + numeroOrden);
                    Orden ordenProducto = new Orden();

                    System.out.println("INGRESE ID DEL PRODUCTO: ");
                    ordenProducto.setProductoByID(scanner.nextInt());
                    scanner.nextLine(); // Consumir left-over

                    System.out.println("INGRESE CANTIDAD DE LA ORDEN: ");
                    ordenProducto.setCantidad(scanner.nextInt());
                    scanner.nextLine(); // Consumir left-over

                    caja.addOrdenAPedido(ordenProducto);
                    numeroOrden++;
                    break;
                case 2:
                    System.out.println("--------------------------------- CREAR ORDEN --------------------------------");
                    InterfaceService.mostrarPromocionesMenu(caja);
                    System.out.println("------------------------------------------------------------------------------\n");

                    System.out.println("ORDEN #" + numeroOrden);
                    Orden ordenPromocion = new Orden();

                    System.out.println("INGRESE ID DEL PROMOCION: ");
                    ordenPromocion.setPromocionByID(scanner.nextInt());
                    scanner.nextLine(); // Consumir left-over

                    System.out.println("INGRESE CANTIDAD DE LA ORDEN: ");
                    ordenPromocion.setCantidad(scanner.nextInt());
                    scanner.nextLine(); // Consumir left-over

                    caja.addOrdenAPedido(ordenPromocion);
                    numeroOrden++;
                    break;
                case 3:
                    System.out.println("-------------------------------- ELIMINAR ORDEN ------------------------------");

                    System.out.println("INGRESE ID DE LA ORDEN: ");
                    caja.deleteOrdenAPedidoById(scanner.nextInt());
                    scanner.nextLine(); // Consumir left-over

                    numeroOrden--;
                    break;
                case 4:
                    Double precioPedido = caja.calcularPrecioPedido();
                    System.out.printf("PRECIO PEDIDO ('%s'): ......................................... $%4.2f", caja.getMonedaPago().toString(), caja.calcularPrecioPedido() );
                    break;
                case 5:
                    caja.clearPedido();
                    numeroOrden = 0;
                    System.out.println("****************************** PEDIDO CANCELADO ******************************");
                    break;
                case 6:
                    caja.cobrarPedido();
                    caja.clearPedido();
                    numeroOrden = 0;
                    break;
                case 7:
                    if(!caja.getPedido().isEmpty()) {
                        caja.clearPedido();
                        numeroOrden = 0;
                        System.out.println("************************** PEDIDO EN CURSO CANCELADO **************************");
                    }
                    break;
                default:
                    InterfaceService.validadorOperaciones(operacionSeleccionada);
                    break;
            }
            InterfaceService.limpiarConsola();
        } while(operacionSeleccionada != 7);
    }

}

