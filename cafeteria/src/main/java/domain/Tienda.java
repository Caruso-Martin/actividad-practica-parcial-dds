package domain;

import domain.caja.Caja;
import domain.menu.Menu;

import java.util.List;

public class Tienda {
    private Menu menu;
    private List<Caja> cajas;
    //private double montoTotalRecaudado;

    public double montoTotalRecaudado(){
        return cajas.stream().mapToDouble(c -> c.getMontoRecaudado()).sum();
    }

    /* Getters y Setters */

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Caja> getCajas() {
        return cajas;
    }

    public void setCajas(List<Caja> cajas) {
        this.cajas = cajas;
    }

    public void addCaja(Caja caja) {
        this.cajas.add(caja);
    }

    /*public Caja getCaja() {
        return caja;
    }*/

    /*public void setCaja(Caja caja) {
        this.caja = caja;
    }*/

    /*public double getMontoTotalRecaudado() {
        return montoTotalRecaudado;
    }*/

    /*public void setMontoTotalRecaudado(double montoTotalRecaudado) {
        this.montoTotalRecaudado = montoTotalRecaudado;
    }*/
}
