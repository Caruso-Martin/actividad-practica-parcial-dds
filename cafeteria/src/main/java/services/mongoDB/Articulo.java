package services.mongoDB;

public class Articulo {
    private String nombre;
    private double precioUnidad;
    private int cantidad;

    public double getPrecioOrden(){
        return precioUnidad * cantidad;
    }

    /* Constructor */

    public Articulo(String nombre, double precioUnidad, int cantidad) {
        this.nombre = nombre;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
    }

    /* Getters y Setters */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }
}
