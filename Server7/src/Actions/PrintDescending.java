package Actions;

import Collections.MapCommands;
import Other.ReadCommand;

import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс для реализации команды print_descending
 */


public class PrintDescending extends Command {

    private static final long serialVersionUID = 32L;
    ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public String execute(ReadCommand com, MapCommands mc) {
        String answ;
        try {
            lock.readLock().lock();
            answ = mc.print_descending();
        }finally {
            lock.readLock().unlock();
        }

        return answ;
    }
}

