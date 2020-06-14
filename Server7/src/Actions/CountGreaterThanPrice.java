package Actions;

import Collections.MapCommands;
import Other.ReadCommand;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс для реализации команды count_greater_than_price
 */

public class CountGreaterThanPrice extends Command {
    private static final long serialVersionUID = 32L;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException {
        String price = com.getStrArgs();
        float pr = Float.parseFloat(price);
        try {
            lock.readLock().lock();
            return mc.count_greater_than_price(pr);
        }catch (NumberFormatException e){
            return "Wrong type of price, enter command again";
        } catch (NullPointerException e){
            return "Price expected";
        } finally {
            lock.readLock().unlock();
        }
    }
}
