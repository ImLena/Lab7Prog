package DataBase;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class RegistBase {

    private static Connection connection;
    private static Logger log = Logger.getLogger(RegistBase.class.getName());

    public RegistBase(Connection connect) {
        connection = connect;
    }

    public void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException ignored){ }
    }

    public static LinkedHashMap<String, String> getUsers() throws SQLException {
        LinkedHashMap<String, String> users = new LinkedHashMap<>();
        String password;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        while (rs.next()) {
            String user = rs.getString(1);
            password = rs.getString(2);/*
            password[1] = rs.getString(3);*/
            users.put(user, password);
        }
        statement.close();
        return users;
    }

    public static String addNewUser(String login, String password) throws SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
        preparedStatement.close();
        return "user reggistered";
    }

    public static String login(String login, String password) throws SQLException {
       /* PreparedStatement statement;
        if (statement = connection.prepareStatement("select exists(select 1 from " + base + " where username = " + login).equals("true")) {
*/
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()&&hash(password/*, rs.getString("salt")*/)
                    .equals(rs.getString("password"))) {
                System.out.println("user logged");
                statement.close();
                return "user logged";
        }else {
        System.out.println("user didn't logged");
        return "Wrong username or password";
        }

    }

    private static String hash(String password/*, String salt*/){
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
        try {
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE users " +
                    "(username TEXT, " +
                    " password TEXT)";
            statement.execute(createTableSQL);
            log.info("Users db created");
        }catch (RuntimeException e){
            log.info("This data base is already created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
