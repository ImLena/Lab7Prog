package Actions;

import java.io.IOException;
import java.util.Scanner;

public class Exit extends Command {
    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public Exit (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args, Scanner in) throws IOException {
        commandReceiver.exit();
    }

    @Override
    protected void help() {
        System.out.println("help - all available commands");
    }
}

