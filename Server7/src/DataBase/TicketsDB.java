package DataBase;

import Collections.*;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class TicketsDB {
    private static Connection connection;
    private static String base = "tickets";
    private static Logger log = Logger.getLogger(RegistBase.class.getName());

    public TicketsDB(Connection connect) throws SQLException {
        connection = connect;
        this.createTicketsDB();
    }

    public static void createTicketsDB() throws SQLException {
        try(Statement statement = connection.createStatement();) {
            String createTableSQL = "CREATE TABLE tickets(id BIGINT NOT NULL," +
                    " username VARCHAR NOT NULL, " +
                    "name VARCHAR NOT NULL, " +
                    "x FLOAT NOT NULL, y FLOAT NOT NULL, " +
                    "creation_date DATE NOT NULL, price FLOAT NOT NULL, " +
                    "type TEXT, person_height FLOAT NOT NULL, " +
                    "place VARCHAR NOT NULL, xpl BIGINT NOT NULL, " +
                    "ypl FLOAT NOT NULL, zpl FLOAT NOT NULL)";

            statement.execute(createTableSQL);
            log.info("Tickets db created");
        } catch (RuntimeException e) {
            log.info("This data base is already created");
        }catch (SQLException e){
            log.info("Tickets db uploaded");
        } catch (Exception e) {
        }
    }


    public LinkedHashMap<Long, Ticket> loadTicketsDB() throws SQLException {
        try(Statement statement = connection.createStatement();) {
            LinkedHashMap<Long, Ticket> tm = new LinkedHashMap<>();
            ResultSet rs = statement.executeQuery("SELECT * FROM tickets");
            while (rs.next()) {
                TicketType type = (!rs.getString("type").isEmpty()) ? TicketType.valueOf(rs.getString(8)) : null;
                Ticket tic = new Ticket(rs.getString("name"),
                        new Coordinates(rs.getFloat("x"), rs.getInt("y")),
                        rs.getFloat("price"), type,
                        new Person(rs.getDouble("person_height"), new Location(rs.getLong("xpl"), rs.getFloat("ypl"), rs.getFloat("zpl"), rs.getString("place"))),
                        rs.getString("username"));
                tic.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
                tic.setId(rs.getLong("id"));
                tm.put(rs.getLong("id"), tic);
            }
            return tm;
        } catch (PSQLException e) {
            log.warning("Oh no, this base seems to be broken :(");
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    public static void insert(Ticket tic) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + base + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");) {
            preparedStatement.setLong(1, tic.getId());
            preparedStatement.setString(2, tic.getUser());
            preparedStatement.setString(3, tic.getName());
            preparedStatement.setFloat(4, tic.getCoordinates().getX());
            preparedStatement.setInt(5, tic.getCoordinates().getY());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(tic.getCreationDate()));
            preparedStatement.setFloat(7, tic.getPrice());
            preparedStatement.setString(8, String.valueOf(tic.getType()));
            preparedStatement.setDouble(9, tic.getPerson().getHeight());
            preparedStatement.setString(10, tic.getPerson().getLoc().getName());
            preparedStatement.setLong(11, tic.getPerson().getLoc().getX());
            preparedStatement.setFloat(12, tic.getPerson().getLoc().getY());
            preparedStatement.setFloat(13, tic.getPerson().getLoc().getZ());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String clear(String user) throws SQLException {
        try(Statement st = connection.createStatement()) {
            StringBuilder sb = new StringBuilder();
            st.executeUpdate("DELETE FROM " + base + " WHERE username = '" + user + "'");
            ResultSet rs = st.executeQuery("SELECT * FROM " + base);
            while (rs.next()) {
                sb.append(rs.getString("id") + ",");
            }
          //  if (!sb.toString().isEmpty()) sb.deleteCharAt(sb.length() - 1);

            return sb.toString();
        }catch (Exception e){
        e.printStackTrace();
        return null;
    }
    }

    public static void removeGreater(Ticket tic, String user) throws SQLException {
        try(Statement st = connection.createStatement()) {
            st.execute("DELETE FROM " + base + " WHERE price > '" + tic.getPrice() + "' AND \"username\" = '" + user + "'");
            ResultSet rs = st.executeQuery("select name from " + base + " where price > '" + tic.getPrice() + "' and \"user\" != '" + user + "'");
        }
    }

    public static void remove(Long id, String user) throws SQLException {
        try(Statement st = connection.createStatement()) {
            st.execute("DELETE FROM " + base + " WHERE id = '" + id + "' AND \"username\" = '" + user + "'");
        //    ResultSet rs = st.executeQuery("select username from " + base + " where id ='" + id + "'");
            //rs.next();
           /* if (!rs.getString("username").equals(user))
                throw new SQLException("This object belongs to another user, you can't change it.");
            st.executeUpdate("delete from " + base + " where id = '" + id + "'");*/
        }
    }

    public static void replaceIfGreater(Ticket tic, Long id) throws SQLException {
        try(Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from " + base + " where place < '" + tic.getName() + "' and id = '" + id + "'");
            if (rs.next()) {
                if (!rs.getString("username").equals(tic.getUser()))
                    throw new SQLException("Object with id = " + id + " belongs to another user, you can't change it.");
                remove(id, tic.getUser());
                tic.setId(rs.getLong("id"));
                insert(tic);
            }
        }
    }

    public static void update(Ticket tic, Long id) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * from " + base + " WHERE id = " + id);
            if (rs.next()) {
                System.out.println("updating...");
                if (!rs.getString("username").equals(tic.getUser()))
                    throw new SQLException("Object with id = " + id + " belongs to another user, you can't change it.");
                System.out.println("correct user");
                remove(rs.getLong("id"), tic.getUser());
                insert(tic);
            }
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
    }
}
