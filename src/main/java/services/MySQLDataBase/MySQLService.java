package services.MySQLDataBase;

import domain.Tienda;
import domain.menu.Menu;
import domain.menu.producto.Producto;
import domain.menu.producto.ProductoSimple;
import domain.menu.producto.Promocion;
import domain.menu.producto.stock.Insuficiente;
import domain.menu.producto.stock.StockState;
import domain.menu.producto.stock.Suficiente;
import domain.menu.producto.stock.Vacio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLService {
    private static Connection connection1;
    private static Connection connection2;
    private static Connection connection3;

    public static void main(String[] args) throws SQLException {
        MySQLService mySQL = new MySQLService();
        mySQL.crearConexion();

        //ProductoSimple producto = MySQLService.obtenerProducto(1);
        //System.out.println(producto.getId() + " - " + producto.getNombre() + " - Precio: $" + producto.getPrecio() + "\nDescripcion: " + producto.getDescripcion() + "\nCantidad disponible: " + producto.getCantidadDisponible());

        //Promocion promocion = MySQLService.obtenerPromocion(2);
        //System.out.println(promocion.getId() + " - " + promocion.getNombre() + " - Precio: $" + promocion.getPrecio() + "\nDescripcion: " + promocion.getDescripcion() + "\nCantidad disponible: " + promocion.getCantidadDisponible());

        //Menu menu = MySQLService.obtenerMenu(1);
        //menu.getProductos().forEach(p -> System.out.printf("%3d - %22s - Precio: %.2f - Descripcion: %s\n", p.getId(), p.getNombre(), p.getPrecio(), p.getDescripcion()));

        //Tienda tienda = MySQLService.obtenerTienda(1);
        //System.out.println(tienda.getMontoTotalRecaudado() + " - " + tienda.getUbicacion());
        //tienda.getMenu().getProductos().forEach(p -> System.out.printf("%3d - %22s - Precio: %.2f - Descripcion: %s\n", p.getId(), p.getNombre(), p.getPrecio(), p.getDescripcion()));

        //MySQLService.actualizarMonto(100, 1);

        // MySQLService.actualizarPrecio(400, 6);

        //MySQLService.actualizarStock(20, 3, 5);
        //MySQLService.actualizarStock(20, 3, 6);
        //MySQLService.actualizarStock(20, 3, 7);

        //MySQLService.agregarProducto(MySQLService.obtenerProducto(1), 1);
        //MySQLService.agregarPromocion(MySQLService.obtenerPromocion(2), 1);

    }

    public void crearConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafetindb", "root", "root");
            connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafetindb", "root", "root");
            connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafetindb", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /* INSERT */

    public static void agregarPromocion(Promocion promocion, Integer idMenu) throws SQLException {
        Statement statement = connection1.createStatement();

        statement.execute("INSERT INTO `cafetindb`.`promociones` (`idPromocion`, `nombre`, `precio`, `descripcion`, `cantidadDisponible`, `idStockState`, `idMenu`) " +
                "VALUES ('" + MySQLService.obtenerProximoIDPromocion() + "', '" + promocion.getNombre() + "', " + promocion.getPrecio() + " , '" + promocion.getDescripcion()  + "', " + promocion.getCantidadDisponible() + ", " + promocion.getEstadoStock().toInt() + ", " + idMenu + ")");

        statement.close();
    }

    private static Integer obtenerProximoIDPromocion() throws SQLException {
        Statement statement = connection1.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT COUNT(IDPROMOCION) AS ROWCOUNT FROM PROMOCIONES");
        resultSet.next();

        Integer idActual = resultSet.getInt("ROWCOUNT");

        statement.close();

        return  idActual + 1;
    }

    public static void agregarProducto(Producto producto, Integer idMenu) throws SQLException {
        Statement statement = connection1.createStatement();

        statement.execute("INSERT INTO `cafetindb`.`productos` (`idProducto`, `nombre`, `precio`, `descripcion`, `cantidadDisponible`, `idStockState`, `idMenu`) " +
                "VALUES ('" + MySQLService.obtenerProximoIDProducto() + "', '" + producto.getNombre() + "', " + producto.getPrecio() + " , '" + producto.getDescripcion()  + "', " + producto.getCantidadDisponible() + ", " + producto.getEstadoStock().toInt() + ", " + idMenu + ")");

        statement.close();
    }

    private static Integer obtenerProximoIDProducto() throws SQLException {
        Statement statement = connection1.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT COUNT(IDPRODUCTO) AS ROWCOUNT FROM PRODUCTOS");
        resultSet.next();

        Integer idActual = resultSet.getInt("ROWCOUNT");

        statement.close();

        return  idActual + 1;
    }

    /* UPDATE */

    public static void actualizarUbicacion(String nuevaUbicacion, Integer idTienda) throws SQLException {
        Statement statement = connection1.createStatement();

        statement.execute("UPDATE TIENDA SET UBICACION = " + nuevaUbicacion + " WHERE IDTIENDA = " + idTienda);

        statement.close();
    }

    public static void actualizarPrecio(Double montoActualizado, Integer idProducto) throws SQLException {
        Statement statement = connection1.createStatement();
        Statement statement2 = connection2.createStatement();

        statement.execute("UPDATE PRODUCTOS SET PRECIO = " + montoActualizado + " WHERE IDPRODUCTO = " + idProducto);

        ResultSet resultSet = statement2.executeQuery("SELECT * FROM PRODUCTOXPROMOCION WHERE IDPRODUCTO = " + idProducto);

        while (resultSet.next()) {
            Promocion promocion = MySQLService.obtenerPromocion(resultSet.getInt("idPromocion"));
            statement.execute("UPDATE PROMOCIONES SET PRECIO = " + promocion.getPrecio() + " WHERE IDPROMOCION = " + resultSet.getInt("idPromocion"));
        }
        statement.close();
    }

    public static void actualizarStock(Integer montoStockActualizado, Integer stockState, Integer idProducto) throws SQLException {
        Statement statement = connection1.createStatement();
        Statement statement2 = connection2.createStatement();
        Statement statement3 = connection3.createStatement();

        statement.execute("UPDATE `cafetindb`.`productos` SET CANTIDADDISPONIBLE = " + montoStockActualizado + ", IDSTOCKSTATE = " + stockState + " WHERE IDPRODUCTO = " + idProducto);

        ResultSet resultSet = statement2.executeQuery("SELECT * FROM PRODUCTOXPROMOCION WHERE IDPRODUCTO = " + idProducto);

        while (resultSet.next()) {
            ResultSet resultSet2 = statement3.executeQuery("SELECT * FROM PRODUCTOXPROMOCION WHERE IDPROMOCION = " + resultSet.getInt("idPromocion"));

            while (resultSet2.next()){
                ProductoSimple productoSimple = MySQLService.obtenerProducto(resultSet2.getInt("idProducto"));
                Promocion promocion = MySQLService.obtenerPromocion(resultSet.getInt("idPromocion"));

                statement.execute("UPDATE `cafetindb`.`promociones` SET CANTIDADDISPONIBLE = " + promocion.getCantidadDisponible() + ", IDSTOCKSTATE = " + promocion.getEstadoStock().toInt() + " WHERE IDPROMOCION = " + resultSet.getInt("idPromocion"));
            }
        }
        statement.close();
    }

    public static void actualizarMonto(Double montoActualizado, Integer idTienda) throws SQLException {
        Statement statement = connection1.createStatement();

        statement.execute("UPDATE TIENDA SET MONTOTOTALRECAUDADO = " + montoActualizado + " WHERE IDTIENDA = " + idTienda);

        statement.close();
    }

    /* SELECT */

    public static Tienda obtenerTienda(Integer idTienda) throws SQLException {
        Double montoTotalRecaudado;
        String ubicacion;
        Menu menu;

        Statement statement = connection1.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM TIENDA WHERE IDTIENDA = " + idTienda);
        resultSet.next();

        montoTotalRecaudado = resultSet.getDouble("montoTotalRecaudado");
        ubicacion = resultSet.getString("ubicacion");
        menu = MySQLService.obtenerMenu(1);

        statement.close();

        return Tienda.getInstancia(menu, montoTotalRecaudado, ubicacion);
    }

    public static Menu obtenerMenu(Integer idMenu) throws SQLException {
        List<Producto> productos = new ArrayList<Producto>();

        Statement statement = connection1.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM MENU WHERE IDMENU = " + idMenu);
        resultSet.next();

        resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS WHERE IDMENU = " + idMenu);

        while (resultSet.next()) {
            ProductoSimple productoSimple = MySQLService.obtenerProducto(resultSet.getInt("idProducto"));
            productos.add(productoSimple);
        }

        resultSet = statement.executeQuery("SELECT * FROM PROMOCIONES WHERE IDMENU = " + idMenu);

        while (resultSet.next()) {
            Promocion promocion = MySQLService.obtenerPromocion(resultSet.getInt("idPromocion"));
            productos.add(promocion);
        }

        statement.close();

        return Menu.getInstancia(productos);
    }

    public static List<Producto> obtenerPromociones(Integer idMenu) throws SQLException {
        List<Producto> promociones = new ArrayList<Producto>();

        Statement statement = connection1.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM MENU WHERE IDMENU = " + idMenu);
        resultSet.next();

        resultSet = statement.executeQuery("SELECT * FROM PROMOCIONES WHERE IDMENU = " + idMenu);

        while (resultSet.next()) {
            Promocion promocion = MySQLService.obtenerPromocion(resultSet.getInt("idPromocion"));
            promociones.add(promocion);
        }

        statement.close();

        return promociones;
    }

    public static List<Producto> obtenerProductos(Integer idMenu) throws SQLException {
        List<Producto> productos = new ArrayList<Producto>();

        Statement statement = connection1.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS WHERE IDMENU = " + idMenu);

        while (resultSet.next()) {
            ProductoSimple productoSimple = MySQLService.obtenerProducto(resultSet.getInt("idProducto"));
            productos.add(productoSimple);
        }

        statement.close();

        return productos;
    }

    public static Promocion obtenerPromocion(Integer idPromocion) throws SQLException {
        Promocion promocion = new Promocion();

        Statement statement = connection1.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM PROMOCIONES WHERE IDPROMOCION = " + idPromocion);
        resultSet.next();

        promocion.setId(resultSet.getInt("idPromocion"));
        promocion.setNombre(resultSet.getString("nombre"));

        resultSet = statement.executeQuery("SELECT * FROM PRODUCTOXPROMOCION WHERE IDPROMOCION = " + idPromocion);

        while (resultSet.next()) {
            ProductoSimple productoSimple = MySQLService.obtenerProducto(resultSet.getInt("idProducto"));
            promocion.addProducto(productoSimple);
        }

        statement.close();

        return promocion;
    }

    public static ProductoSimple obtenerProducto(Integer idProducto) throws SQLException {
        ProductoSimple producto = new ProductoSimple();

        Statement statement = connection1.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS WHERE IDPRODUCTO = " + idProducto);
        resultSet.next();

        producto.setId(resultSet.getInt("idProducto"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getDouble("precio"));
        producto.setDescripcion(resultSet.getString("descripcion"));
        producto.setCantidadDisponible(resultSet.getInt("cantidadDisponible"));

        resultSet = statement.executeQuery("SELECT * FROM STOCKSTATE WHERE IDSTOCKSTATE = " + resultSet.getInt("idStockState"));
        resultSet.next();

        producto.setEstadoStock(MySQLService.getStockState(resultSet.getString("nombre")));

        statement.close();

        return producto;
    }

    private static StockState getStockState(String stockStateString) {
        StockState stockState = null;

        if(stockStateString.equals("Vacio"))
            stockState = new Vacio();


        if(stockStateString.equals("Insuficiente"))
            stockState = new Insuficiente();


        if(stockStateString.equals("Suficiente"))
            stockState = new Suficiente();

        return stockState;
    }
}
