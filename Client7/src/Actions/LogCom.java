package Actions;

import java.io.IOException;
import java.util.Scanner;

public class LogCom extends Command{
    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public LogCom (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    @Override
    protected void help(){
        System.out.println("help - show information about collection");
    }
    @Override
    protected  void execute (String[] args, Scanner in) throws IOException, InterruptedException, ClassNotFoundException {
    }
}
