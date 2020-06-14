package Actions;

import Collections.MapCommands;
import Other.ReadCommand;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс для реализации команды min_by_creation_date
 */

public class MinByCreationDate extends Command {

    private static final long serialVersionUID = 32L;
    ReadWriteLock lock = new ReentrantReadWriteLock();


    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException {
        String answ;
        try {
            lock.readLock().lock();
            answ = mc.min_by_creation_date();
        }finally {
            lock.readLock().unlock();
        }
        return answ;
    }
}
