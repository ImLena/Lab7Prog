package Actions;

import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для реализации команды remove_greater
 */

public class RemoveGreater extends Command {

    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public RemoveGreater(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void help(){
        System.out.println("remove_greater {element} - remove all elements, which price is greater");
    }

    @Override
    protected void execute(String[] args, Scanner in) throws IOException {
        commandReceiver.remove_greater(args, in);
    }
}
