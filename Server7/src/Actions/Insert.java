package Actions;

import DataBase.TicketsDB;
import Collections.MapCommands;
import Collections.Ticket;
import Other.ReadCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс для реализации команды insert
 */


public class Insert extends Command {
    private static final long serialVersionUID = 32L;
    ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public  String execute(ReadCommand com, MapCommands mc) throws IOException {
        try {
            Ticket tic = com.getTicket();
            tic.setCreationDate(LocalDateTime.now());
            tic.setUser(com.getLogin());
            TicketsDB.insert(tic);
            try {
                lock.readLock().lock();
                lock.writeLock().lock();
                mc.insert(Long.valueOf(com.getStrArgs()), tic);
            } finally {
                lock.readLock().unlock();
                lock.writeLock().unlock();
            }
            return "Element with ID " + com.getStrArgs() + " inserted to collection";


        } catch (NumberFormatException | IOException | SQLException e) {
            return "Not correct key";
        }
    }
}
