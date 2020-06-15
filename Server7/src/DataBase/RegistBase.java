package DataBase;

import Base.Executor.DatabaseInitializer;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.logging.Logger;

public class RegistBase {

    private static Connection connection;
    private static Logger log = Logger.getLogger(RegistBase.class.getName());

    public RegistBase(Connection connect) throws SQLException {
        connection = connect;
        this.createUsersDB();
    }

    public void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException ignored){ }
    }

    public static String addNewUser(String login, String password) throws SQLException {
        if (!isRegistered(login)) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, login);
                String pass = hash(password);
                preparedStatement.setString(2, pass);
                preparedStatement.execute();
                System.out.println("user registered.");
                return "user registered";
            }
        }else {
            return "this user exist";
        }
    }

    public static String login(String login, String password) throws SQLException {
       try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
           statement.setString(1, login);
           ResultSet rs = statement.executeQuery();
           if (rs.next() && hash(password)
                   .equals(rs.getString("password"))) {
               System.out.println("user logged.");
               return "user logged";
           } else {
               System.out.println("user didn't logged");
               return "Wrong username or password";
           }
       }

    }

    private static String hash(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            System.out.println(password);
            byte[] data = (password).getBytes(StandardCharsets.UTF_8);
            byte[] hashbytes = md.digest(data);
            return Base64.getEncoder().encodeToString(hashbytes);
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }


    public static void createUsersDB() throws SQLException {
        try(Statement statement = connection.createStatement();) {
          //  if (!DatabaseInitializer.isCreated("users", connection)) {
                String createTableSQL = "CREATE TABLE users " +
                        "(username TEXT, " +
                        " password TEXT)";
                statement.execute(createTableSQL);
                log.info("Users db created");
            /*}else {
                log.info("Users db uploaded");
            }*/
        }catch (RuntimeException e) {
            log.info("This data base is already created");
        } catch (SQLException e){
            log.info("Users db uploaded");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isRegistered(String login) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }

    }
}
