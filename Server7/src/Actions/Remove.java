package Actions;

import DataBase.TicketsDB;
import Collections.MapCommands;
import Other.ReadCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс для реализации команды remove
 */

public class Remove extends Command {
    private static final long serialVersionUID = 32L;
    ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException {
        try{
        String arg = com.getStrArgs();
        Long id = Long.valueOf(arg);
        String user = com.getLogin();
        TicketsDB.remove(id, user);
        try {
            lock.readLock().lock();
            lock.writeLock().lock();
            mc.remove(id, user);
        }finally {
            lock.readLock().unlock();
            lock.writeLock().unlock();
        }
        return  "Element " + arg + " removed";
        } catch (NumberFormatException | SQLException e) {
            return "Not correct key";
        }
    }
}
