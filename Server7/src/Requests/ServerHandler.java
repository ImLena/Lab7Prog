package Requests;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.logging.Logger;

public class ServerHandler {

    private static Logger log = Logger.getLogger(ServerHandler.class.getName());

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

/*
    private LinkedHashMap<String, String> logPas;

    @Override
    public void run() {
        try {
            String[] s = Server.readClient();
            if (s.length != 2) {
                Server.answer(channel, "Login and password expected");
            } else {
                String log = s[0];
                String pas = s[1];
            }
            if (Users.isVal)
            logPas.put(s[0],s[1]);
            String login = (String) logPas.get("login");
            Server.setLogPas(logPas);
            //user.setCommandAndArgument(receiver.receiveCommand());
            Server.server();
            Thread.currentThread().sleep(20);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public ServerHandler(int PORT) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        log.info("Success connection");
    }

    public Selector getSelector() {
        return selector;
    }

    public void accept() throws IOException {
        Register.register(selector, serverSocketChannel);
    }

    public void close() {
        try {
            serverSocketChannel.close();
        }
        catch (IOException ignored){}
    }
}
