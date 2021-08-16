package domain.menu.producto;

import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;
import domain.menu.Menu;
import domain.menu.producto.stock.Insuficiente;
import domain.menu.producto.stock.StockState;
import domain.menu.producto.stock.Suficiente;
import domain.menu.producto.stock.Vacio;
import services.MySQLDataBase.MySQLService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Promocion implements Producto {
    private Integer id;
    List<Producto> productos = new ArrayList<Producto>();

    @Override
    public void renovarStock(Producto producto) {
        productos.forEach(p -> {
            try {
                p.renovarStock(p);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
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

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return String.join(" + ",  productos.stream().map(Producto::getNombre).collect(Collectors.toList()));
    }

    @Override
    public void setNombre(String nombre) {

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
        return String.join(" + ",  productos.stream().map(Producto::getNombre).collect(Collectors.toList()));
    }

    @Override
    public void setDescripcion(String descripcion) {

    }

    @Override
    public Integer getCantidadDisponible() {
        return productos.stream().mapToInt(Producto::getCantidadDisponible).min().orElse(0);
    }

    @Override
    public void setCantidadDisponible(Integer cantidadDisponible) {

    }

    @Override
    public StockState getEstadoStock() {
        StockState stockState = null;

        if(productos.stream().allMatch(p -> p.getEstadoStock().toString().equals("Suficiente")))
            stockState = new Suficiente();

        if(productos.stream().anyMatch(p -> p.getEstadoStock().toString().equals("Insuficiente")))
            stockState = new Insuficiente();

        if(productos.stream().anyMatch(p -> p.getEstadoStock().toString().equals("Vacio")))
            stockState = new Vacio();

        return stockState;
    }

    @Override
    public void setEstadoStock(StockState estadoStock) {

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void addProductoByID(Integer idProducto) throws SQLException {
        this.productos.add(MySQLService.obtenerProducto(idProducto));
    }

}
