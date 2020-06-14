package Actions;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для реализации команды clear
 */

public class Clear extends Command {
    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public Clear (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    @Override
    protected void help(){
        System.out.println("clear - clear collection");
    }

    @Override
    protected void execute(String[] args, Scanner in) throws IOException, ClassNotFoundException, InterruptedException {
        commandReceiver.clear();

    }
}
