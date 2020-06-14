package Actions;

import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для реализации команды count_greater_than_price
 */

public class CountGreaterThanPrice extends Command {
    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public CountGreaterThanPrice(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void help(){
        System.out.println("count_greater_than_price price - enter number of elements with price more than entered ");
    }
    @Override
    protected void execute(String[] args, Scanner in) throws IOException {
        commandReceiver.count_greater_than_price(args);

        }
    }