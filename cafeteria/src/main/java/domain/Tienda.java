package domain;

import domain.caja.Caja;
import domain.menu.Menu;

public class Tienda {
    private Menu menu;
    private Caja caja;

    /* Constructor */

    public Tienda(Menu menu, Caja caja) {
        this.menu = menu;
        this.caja = caja;
    }

    /* Getters y Setters */

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
}
