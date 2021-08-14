package domain.caja;

import domain.Tienda;
import domain.caja.cotizacion.CotizarStrategy;
import domain.caja.cotizacion.Peso;
import domain.menu.producto.Orden;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Caja {
    private List<Orden> pedido = new ArrayList<Orden>();
    private CotizarStrategy monedaPago;


    public void cobrarPedido() throws IOException {
        // Cobrar pedido
        this.monedaPago = new Peso();

        Tienda tienda = Tienda.getInstancia();
        tienda.sumarMontoTotalRecaudado(this.calcularPrecioPedido());

        // Gestion interna de stock
        this.getionarStock();

        // Reset caja
        pedido = null;
        monedaPago = null;
    }

    private void getionarStock() {
        // Consumir stock
        pedido.stream().forEach(o -> {
            try {
                o.getProducto().consumirStock(o.getCantidad());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Renovar stock
        pedido.stream().forEach(o -> o.getProducto().renovarStock(o.getProducto()));
    }

    public Double calcularPrecioPedido() throws IOException {
        Double precioOriginal = pedido.stream().mapToDouble(Orden::getPrecio).sum();
        return monedaPago.cotizar(precioOriginal);
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

    public CotizarStrategy getMonedaPago() {
        return monedaPago;
    }

    public void setMonedaPago(CotizarStrategy monedaPago) {
        this.monedaPago = monedaPago;
    }
}
