package Actions;

import java.util.Scanner;

/**
 * Класс для реализации команды help
 */

public class Help extends Command {
    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public Help (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args, Scanner in) {
        commandReceiver.help();
    }

    @Override
    protected void help() {
        System.out.println("help - all available commands");
    }
}
