package Requests;

import Actions.Command;
import Other.ReadCommand;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class ReadClient implements Callable<ReadCommand>{
    private SocketChannel channel;

    public ReadClient(SocketChannel channel) {
        this.channel = channel;
    }

    private static Logger log = Logger.getLogger(ReadClient.class.getName());

    @Override
    public ReadCommand call() throws IOException {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            channel.read(buffer);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream objIn = new ObjectInputStream(byteArrayInputStream);
            Object o = objIn.readObject();
            log.info("Server got object");
            if (o instanceof ReadCommand) {
                ReadCommand ob = (ReadCommand) o;
                return ob;
            } else {
                new AnswerClient(channel, "Unidentified object type");
            }
            return null;
        }catch(IOException | ClassNotFoundException | NullPointerException e){
            channel.close();
            log.warning("Client disconnected.");
           // e.printStackTrace();
            return null;
        }
    }
}
