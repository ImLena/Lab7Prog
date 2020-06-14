package Actions;

import Collections.MapCommands;
import Other.ReadCommand;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public abstract class Command implements Serializable {

    private static final long serialVersionUID = 32L;
    public abstract String execute(ReadCommand com, MapCommands mc) throws IOException, SQLException;

}
