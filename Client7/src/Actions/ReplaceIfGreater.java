package Actions;

import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для реализации команды replace_if_greater
 */

public class ReplaceIfGreater extends Command {
    transient final CommandReceiver commandReceiver;

    private static final long serialVersionUID = 32L;

    public ReplaceIfGreater(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void help(){
        System.out.println("replace_is_greater key {element} - replace element by key if new value is greater");
    }

    @Override
    protected void execute(String[] args, Scanner in) throws IOException, InterruptedException, ClassNotFoundException {
        if (args.length>1){
            commandReceiver.replace_if_greater(args, in);
        }else {
            System.out.println("Enter id after command");
        }
    }
}
