package domain.caja.pedido;

import domain.menu.Promocion;

public class OrdenPromocion {
    private Promocion promocion;
    private int cantidad;

    public double precioTotal(){
        return promocion.getPrecio() * cantidad;
    }

    /* Getters y Setters */

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
