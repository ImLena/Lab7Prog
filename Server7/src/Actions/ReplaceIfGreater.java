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
 * Класс для реализации команды replace_if_greater
 */

public class ReplaceIfGreater extends Command{
    private static final long serialVersionUID = 32L;
    ReadWriteLock lock = new ReentrantReadWriteLock();
    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException, SQLException {
        Ticket tic = com.getTicket();
        Long id = Long.valueOf(com.getStrArgs());
        tic.setCreationDate(LocalDateTime.now());
        if (mc.getTickets().containsKey(id)) {
            TicketsDB.replaceIfGreater(tic, id);
            try {
            lock.readLock().lock();
            lock.writeLock().lock();
                mc.replace_if_greater(tic.getId(), tic, tic.getUser());
            }finally {
                lock.readLock().unlock();
                lock.writeLock().unlock();
            }
            if (mc.replace_if_greater(id, tic, tic.getUser())) {
                return "Element " + id + " replaced";
            } else {
                return "Element didn't removed";
            }
        } else {
            return "There's no element with such key. To add a new element use command insert";
        }
    }
}
