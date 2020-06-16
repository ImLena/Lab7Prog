
import Requests.Server2;
import Requests.ServerHandler;

import java.io.IOException;
import java.net.BindException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainServer {
    private static Logger log = Logger.getLogger(Server2.class.getName());
    public static void main(String[] args) throws IOException, SQLException {
        try{
            ServerHandler sh = port();
            Server2.server(sh);
            } catch (IllegalArgumentException e){
                System.out.println("wrong port");
                main(args);

            }
        }


     public static ServerHandler port() throws IOException {
         System.out.println("Enter port:");
         ServerHandler serverHandler = null;
         int port = Integer.valueOf(new Scanner(System.in).nextLine());
         try {
             serverHandler = new ServerHandler(port);
         } catch (BindException e) {
             log.warning("Port is already using :(");
             port();

         /*} catch (Exception e) {
            log.warning("Server died, come back later! Have a nice day!");
            e.printStackTrace();
            System.exit(0);*/
    }
         return serverHandler;
     }

}
