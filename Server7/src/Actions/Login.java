package Actions;

import DataBase.RegistBase;
import Collections.MapCommands;
import Other.ReadCommand;

import java.io.IOException;
import java.sql.SQLException;

public class Login extends Command {
    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException, SQLException {
        String login = com.getLogin();
        String pass = com.getPass();
        return RegistBase.login(login, pass);
    }
}
