package Actions;

import java.util.Scanner;

/**
 * Класс для реализации команды execute_script
 */


public class ExecuteScript extends Command {
    transient final CommandReceiver commandReceiver;
    private static final long serialVersionUID = 32L;

    public ExecuteScript (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void help(){
        System.out.println("execute_script file_name - read and execute script from file");
    }

    @Override
    protected void execute(String[] args, Scanner in) {
        if (args.length > 1) {
            commandReceiver.execute_script(args[1]);
        }else {
            System.out.println("Enter file name after command");
        }
    }

}