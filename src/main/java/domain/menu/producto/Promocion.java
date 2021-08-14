package domain.menu.producto;

import domain.menu.producto.stock.Insuficiente;
import domain.menu.producto.stock.StockState;
import domain.menu.producto.stock.Suficiente;
import domain.menu.producto.stock.Vacio;

import java.util.ArrayList;
import java.util.List;

public class Promocion implements Producto {
    private Integer id;
    private String nombre;
    List<Producto> productos = new ArrayList<Producto>();

    @Override
    public void renovarStock(Producto producto) {
        productos.forEach(p -> p.renovarStock(p));
    }

    @Override
    public void consumirStock(Integer cantidadConsumida) {
        productos.forEach(p -> {
            try {
                p.consumirStock(cantidadConsumida);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean estaDisponible() {
        return productos.stream().allMatch(Producto::estaDisponible);
    }

    /* Getters y Setters */

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Double getPrecio() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum() * 0.90;
    }

    @Override
    public void setPrecio(Double precio) {

    }

    @Override
    public String getDescripcion() {
        return String.join(" + ", (CharSequence) productos.stream().map(Producto::getDescripcion));
    }

    @Override
    public void setDescripcion(String descripcion) {

    }

    @Override
    public Integer getCantidadDisponible() {
        return productos.stream().mapToInt(Producto::getCantidadDisponible).min().getAsInt();
    }

    @Override
    public void setCantidadDisponible(Integer cantidadDisponible) {

    }

    @Override
    public StockState getEstadoStock() {
        if(productos.stream().anyMatch(p -> p.getEstadoStock().equals(new Vacio())))
            return new Vacio();

        if(productos.stream().anyMatch(p -> p.getEstadoStock().equals(new Insuficiente())))
            return new Insuficiente();

        if(productos.stream().allMatch(p -> p.getEstadoStock().equals(new Suficiente())))
            return new Suficiente();

        return null;
    }

    @Override
    public void setEstadoStock(StockState estadoStock) {

    }
}
