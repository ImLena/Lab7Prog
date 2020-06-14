package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

    public class Base {
        private final String url = "jdbc:postgresql://localhost:5432/postgres";
        private final String user = "postgres";
        private final String password = "password";
        private Connection connection;
        private Statement statement;
        private static RegistBase users;
        private static TicketsDB tickets;

        public Base() throws SQLException {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            users = new RegistBase(connection);
            tickets = new TicketsDB(connection);
        }

        public static RegistBase getUsersDB() {
            return users;
        }

        public static TicketsDB getTicketsDB() {
            return tickets;
        }
    }
