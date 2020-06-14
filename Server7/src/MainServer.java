
import Requests.Server2;

import java.io.IOException;
import java.sql.SQLException;

public class MainServer {
    public static void main(String[] args) throws IOException, SQLException {
        int port = 1213;
        Server2.server(port);
    }
}
