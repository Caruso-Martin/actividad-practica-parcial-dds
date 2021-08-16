package domain.caja;

import domain.Tienda;
import domain.caja.cotizacion.CotizarStrategy;
import domain.caja.cotizacion.Dolar;
import domain.caja.cotizacion.Euro;
import domain.caja.cotizacion.Peso;
import domain.menu.producto.Orden;
import services.MySQLDataBase.MySQLService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Caja {
    private List<Orden> pedido = new ArrayList<Orden>();
    private CotizarStrategy monedaPago;

    public void cobrarPedido() throws IOException, SQLException {
        // Gestion interna de stock
        this.getionarStock();

        // Cobrar pedido
        this.monedaPago = new Peso(); // Se pasa la moneda a Peso para sumar el monto

        Tienda tienda = MySQLService.obtenerTienda(1);
        tienda.sumarMontoTotalRecaudado(this.calcularPrecioPedido());

        // Reset caja
        this.clearPedido();

        System.out.println("\n******************************** COBRO EXITOSO *******************************");
    }

    private void getionarStock() {
        // Consumir stock
        pedido.forEach(o -> {
            try {
                o.getProducto().consumirStock(o.getCantidad());
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });

        // Renovar stock
            pedido.forEach(o -> {
            try {
                o.getProducto().renovarStock(o.getProducto());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        // Notificar estados
        System.out.println("********************* ESTADO STOCK - PRODUCTOS CONSUMIDOS ********************");
        pedido.forEach(o -> System.out.printf("ID: %2d - %22s - Estado: %12s - Cantidad disponible: %2d\n", o.getProducto().getId(),  o.getProducto().getNombre(), o.getProducto().getEstadoStock().toString(), o.getProducto().getCantidadDisponible()));
    }

    public Double calcularPrecioPedido() throws IOException {
        Double precioOriginal = pedido.stream().mapToDouble(Orden::getPrecio).sum();
        return monedaPago.cotizar(precioOriginal);
    }

    /* Constructor */

    public Caja(CotizarStrategy monedaPago) {
        this.monedaPago = monedaPago;
    }

    /* Getters y Setters */

    public List<Orden> getPedido() {
        return pedido;
    }

    public void setPedido(List<Orden> pedido) {
        this.pedido = pedido;
    }

    public void addOrdenAPedido(Orden orden) {
        this.pedido.add(orden);
    }

    public void deleteOrdenAPedidoById(int ordenId) {
        this.pedido.remove(ordenId);
    }

    public void clearPedido() {
        this.pedido.clear();
    }

    public CotizarStrategy getMonedaPago() {
        return monedaPago;
    }

    public void setMonedaPago(CotizarStrategy monedaPago) {
        this.monedaPago = monedaPago;
    }

    public void setMonedaPagoByID(Integer idMonedaPago) {
        switch (idMonedaPago) {
            case 1:
                this.monedaPago = new Peso();
                break;
            case 2:
                this.monedaPago = new Dolar();
                break;
            case 3:
                    this.monedaPago = new Euro();
                break;
        }

    }
}
