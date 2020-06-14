package Requests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class AnswerClient extends RecursiveAction {
    private SocketChannel channel;
    private String msg;
    private Logger log = Logger.getLogger(AnswerClient.class.getName());

    public AnswerClient(SocketChannel channel, String msg) {
        this.channel = channel;
        this.msg = msg;
    }

    @Override
    protected void compute() {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(msg);
            oos.close();
            byte[] data = baos.toByteArray();
            log.info("Answer sent");
            channel.write(ByteBuffer.wrap(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
