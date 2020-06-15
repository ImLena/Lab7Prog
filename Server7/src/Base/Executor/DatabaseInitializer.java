package Base.Executor;

import Base.BaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public final class DatabaseInitializer {

    private static final String URL = "jdbc:postgresql://localhost:5432/"; //"jdbc:postgresql://pg:5432/studs";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "puz273";
    private static final String DRIVER = "org.postgresql.Driver";

    private static Connection connection;

    public static void executeDDL(String sqlCommands) throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            initialize();

// Проверка на то, что база уже инициализирована
         /*   if (isCreated()) {
                return;
            }
*/
            Arrays.stream(sqlCommands.split(";"))
                    .forEach(sqlCommand -> runSql(sqlCommand, connection));

        } catch (Exception exception) {
            System.out.println("Problems with running SQL: " + exception.getMessage());
        } finally {
            connection.close();
        }
    }

    public static boolean isCreated(String base, Connection con) {
        try {
            runSql("SELECT * FROM " + base, con);
        } catch (RuntimeException e) {
            System.out.println("Duplicated query: " + e.getMessage());
            return true;
        }

        return false;
    }

    private static void initialize() {
        if (connection != null) {
            return;
        }

        connection = BaseConnection.getConnect(URL, LOGIN, PASSWORD);
    }

    public static void runSql(String query, Connection connection) {
        try {
            connection.createStatement().executeUpdate(query + ";");
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}