package Actions;

import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для реализации команды print_descending
 */


public class PrintDescending extends Command {

    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public PrintDescending(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public void help(){
        System.out.println("print_descending - sorting collection in descending order");
    }
    public void execute(String[] args, Scanner in) throws IOException, ClassNotFoundException, InterruptedException {
        commandReceiver.print_descending();
    }

}

