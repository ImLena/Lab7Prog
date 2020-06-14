package Actions;

import Collections.MapCommands;
import Other.ReadCommand;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * Класс для реализации команды info
 */

public class Info extends Command {
    private static final long serialVersionUID = 32L;
    @Override
    public String execute (ReadCommand com, MapCommands mc) throws IOException {
        return mc.info();
    }
}
