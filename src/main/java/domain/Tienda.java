package domain;

import domain.caja.Caja;
import domain.menu.Menu;
import services.MySQLDataBase.MySQLService;

import java.sql.SQLException;

public class Tienda {
    private static Tienda instancia = null;

    private Menu menu;
    private Caja caja;
    private Double montoTotalRecaudado;
    private String ubicacion;

    /* Constructor */

    // Singleton

    public Tienda(Menu menu, Double montoTotalRecaudado, String ubicacion) {
        this.menu = menu;
        this.montoTotalRecaudado = montoTotalRecaudado;
        this.ubicacion = ubicacion;
    }

    /* Getters y Setters */

    // Singleton
    public static Tienda getInstancia(Menu menu, Double montoTotalRecaudado, String ubicacion) {
        if(instancia == null)
            instancia = new Tienda(menu, montoTotalRecaudado, ubicacion);

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

    public void sumarMontoTotalRecaudado(Double montoRecaudado) throws SQLException {
        this.montoTotalRecaudado += montoRecaudado;
        MySQLService.actualizarMonto(montoTotalRecaudado, 1);
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
