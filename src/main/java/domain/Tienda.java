package domain;

import domain.caja.Caja;
import domain.menu.Menu;

public class Tienda {
    private static Tienda instancia = null;

    private Menu menu;
    private Caja caja;
    private Double montoTotalRecaudado;
    private String ubicacion;

    /* Constructor */

    // Singleton
    private Tienda() {
    }

    /* Getters y Setters */

    // Singleton
    public static Tienda getInstancia() {
        if(instancia == null)
            instancia = new Tienda();

        return instancia;
    }

    public void setInstancia(Tienda instancia) {
        this.instancia = instancia;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Double getMontoTotalRecaudado() {
        return montoTotalRecaudado;
    }

    public void setMontoTotalRecaudado(Double montoTotalRecaudado) {
        this.montoTotalRecaudado = montoTotalRecaudado;
    }

    public void sumarMontoTotalRecaudado(Double montoRecaudado) {
        this.montoTotalRecaudado += montoRecaudado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
