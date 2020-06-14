package Actions;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Класс для реализации команды show
 */

public class Show extends Command implements Serializable {
    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public Show(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void help(){
        System.out.println("show - show collection");
    }

    @Override
    protected void execute(String[] args, Scanner in) throws IOException, ClassNotFoundException, InterruptedException {
        commandReceiver.show();
    }
}
