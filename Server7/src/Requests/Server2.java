package Requests;

import DataBase.Base;
import DataBase.RegistBase;
import DataBase.TicketsDB;
import Collections.MapCommands;
import Collections.TicketMap;
import Other.ReadCommand;

import java.io.IOException;
import java.net.BindException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class Server2 {

    private static Logger log = Logger.getLogger(Server2.class.getName());

    private static ExecutorService reading = Executors.newCachedThreadPool();
    private static ExecutorService handling = Executors.newCachedThreadPool();
    private static ForkJoinPool sending = new ForkJoinPool();

    public static void server(int port) throws IOException, SQLException {
        Base dataBase = new Base();

        RegistBase users = dataBase.getUsersDB();
        TicketsDB ticketsDB = dataBase.getTicketsDB();
        TicketMap tm;
        ServerHandler serverHandler = null;
        LinkedHashMap<SelectionKey, Future<ReadCommand>> threadCom = new LinkedHashMap<>();
        LinkedHashMap<SelectionKey, Future<String>> threadAnsw = new LinkedHashMap<>();

        try {
            serverHandler = new ServerHandler(port);
        } catch (BindException e) {
            log.warning("Port is already using :(");
            System.exit(1);
        } catch (Exception e) {
            log.warning("Server died, come back later! Have a nice day!");
            System.exit(0);
        }

        tm = new TicketMap(ticketsDB.loadTicketsDB());
        MapCommands mc = new MapCommands(tm);

        while (true) {
            serverHandler.getSelector().select(700);
            Iterator iter = serverHandler.getSelector().selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = (SelectionKey) iter.next();
                iter.remove();


                if (!key.isValid()) {
                    continue;
                }
                    if (key.isAcceptable()) {
                        serverHandler.accept();
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        threadCom.put(key, reading.submit(new ReadClient(channel)));
                        key.interestOps(SelectionKey.OP_WRITE);


                    }
                    if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        try {
                            if (threadCom.containsKey(key) && threadCom.get(key).isDone()) {
                                threadAnsw.put(key, handling.submit(new ExecuteCommand(mc, threadCom.get(key).get())));
                                threadCom.remove(key);
                                log.info("Command executed");
                            }
                            if (threadAnsw.containsKey(key) && threadAnsw.get(key).isDone()) {
                                sending.execute(new AnswerClient(channel, threadAnsw.get(key).get()));
                                threadAnsw.remove(key);
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        } catch (NullPointerException e) {
                            log.info("Client disconnected");
                            key.cancel();
                        } catch (InterruptedException | ExecutionException | CancelledKeyException e) {
                            e.printStackTrace();
                            log.warning("\nDisconnection.");
                            ticketsDB.closeConnection();
                            users.closeConnection();
                            key.cancel();
                        }
                    }
            }
        }
    }
}
