package Actions;

import DataBase.TicketsDB;
import Collections.MapCommands;
import Collections.Ticket;
import Other.ReadCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс для реализации команды update
 */

public class Update extends Command {

    private static final long serialVersionUID = 32L;
    ReadWriteLock lock = new ReentrantReadWriteLock();


    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException {
        try {
            String arg = com.getStrArgs();
            Long id = Long.valueOf(arg);
            LocalDateTime date = LocalDateTime.now();
            for (Map.Entry<Long, Ticket> x : mc.getTickets().entrySet()) {
                if (x.getValue().getId() == id) {
                    Ticket tic = com.getTicket();
                    tic.setId(id);
                    tic.setCreationDate(date);
                    tic.setUser(com.getLogin());
                    TicketsDB.update(tic, id);
                    try {
                    lock.readLock().lock();
                    lock.writeLock().lock();
                        mc.insert(id, x.getValue());
                    } finally {
                        lock.readLock().unlock();
                        lock.writeLock().unlock();
                    }
                    return "Element with ID " + id + " updated";
                }
            }
            return "No element with such ID, to add new element use command insert";

        } catch (NumberFormatException | SQLException e) {
            return "Not correct key";
        }

    }
}