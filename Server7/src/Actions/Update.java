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
    private ReadWriteLock lock = new ReentrantReadWriteLock();


    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException {
        try {
            String answ;
            String arg = com.getStrArgs();
            Long id = Long.valueOf(arg);
            Ticket tic = com.getTicket();
            if (mc.getTickets().containsKey(id)){
                if(mc.getTickets().get(id).getUser().equals(tic.getUser())){
                    TicketsDB.update(tic, id);
                    try {
                    lock.writeLock().lock();
                        answ = mc.update(id, tic);
                    } finally {
                        lock.writeLock().unlock();
                    }
                    return answ;
                } else {
                    return "This element belongs to another user.";
                }
            } else{
            return "No element with such ID, to add new element use command insert";
        }

        } catch (NumberFormatException | SQLException e) {
            return "Not correct key";
        }

    }
}