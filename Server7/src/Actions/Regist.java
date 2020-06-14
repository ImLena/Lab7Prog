package Actions;

import Collections.MapCommands;
import DataBase.RegistBase;
import Other.ReadCommand;

import java.io.IOException;
import java.sql.SQLException;

public class Regist extends Command {
    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException, SQLException {
        String login = com.getLogin();
        String pass = com.getPass();
        System.out.println(pass);
        return RegistBase.addNewUser(login, pass);
    }
}
