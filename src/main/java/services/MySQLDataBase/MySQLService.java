package services.MySQLDataBase;

import java.sql.*;

public class MySQLService {
    public static void main(String[] args) {
        MySQLService mySQL = new MySQLService();
        mySQL.crearConexion();
    }

    public void crearConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafetindb", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS");
            while (resultSet.next()){
                String name = resultSet.getString("nombre");
                System.out.println(name);
            }

            System.out.println("¡Conexion con cafetinDB exitosa!");
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
