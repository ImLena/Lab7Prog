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
