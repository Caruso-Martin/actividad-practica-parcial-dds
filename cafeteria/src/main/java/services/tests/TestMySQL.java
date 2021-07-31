package services.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMySQL {
    private static Connection connection = null;
    private static String database = "jdbc:mysql://localhost:3306/" + connection;
    private static String url = "";

    private static String username = "root";
    private static String password = "martinMYSQL200";

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement("");
    }
}
