package domain;

import domain.caja.Caja;
import domain.menu.Menu;

public class Tienda {
    private static Tienda instancia = null;

    private Menu menu;
    private Caja caja;
    private double montoRecaudado;

    /* Constructor */

    public Tienda(Menu menu, Caja caja) {
        this.menu = menu;
        this.caja = caja;
    }   // Patron Singleton

    /* Getters y Setters */

    public static Tienda getInstancia(Menu menu, Caja caja) {
        if (instancia == null) {
            instancia = new Tienda(menu, caja);
        }
        return instancia;
    }   // Patron Singleton

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

    public double getMontoRecaudado() {
        return montoRecaudado;
    }

    public void setMontoRecaudado(double montoRecaudado) {
        this.montoRecaudado = montoRecaudado;
    }

    public void agregarMontoRecaudado(double montoRecaudado) {
        this.montoRecaudado += montoRecaudado;
    }
}
