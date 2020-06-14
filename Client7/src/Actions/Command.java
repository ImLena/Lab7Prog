package Actions;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 32L;
    protected abstract void help();
    protected abstract void execute(String[] command, Scanner in) throws IOException, ClassNotFoundException, InterruptedException;


}
