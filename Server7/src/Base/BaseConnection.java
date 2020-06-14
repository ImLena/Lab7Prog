package Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConnection {
    private static Connection connection;

    static final String JDBC_DRIVER = "org.postgresql.Driver";



    public static Connection getConnect(String url, String login, String password) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            if (connection == null) {
                return connection = DriverManager.getConnection(url, login, password);
            }else return connection;
        } catch (SQLException e) {
            System.out.println("Impossible to connect to data base:\n" + e.getMessage());
            System.exit(0);
            return null;
        }
    }

}
