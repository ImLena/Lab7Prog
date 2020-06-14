package Actions;

import Collections.MapCommands;
import DataBase.TicketsDB;
import Other.ReadCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс для реализации команды clear
 */

public class Clear extends Command {
    private static final long serialVersionUID = 32L;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException, SQLException {
        TicketsDB.clear(com.getLogin());
        try {
            lock.writeLock().lock();
            mc.clear(com.getLogin());
        }finally {
            lock.writeLock().unlock();
        }

        return "Collection is clear";
    }
}
