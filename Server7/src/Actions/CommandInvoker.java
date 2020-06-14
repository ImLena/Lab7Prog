package Actions;

import Collections.MapCommands;
import Other.ReadCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class CommandInvoker {

    private static CommandInvoker instance;

    private Command clear;
    private Command cgtp;
    private Command es;
    public Command help;
    private Command history;
    private Command info;
    private Command insert;
    private Command mbcd;
    private Command pd;
    private Command remove;
    private Command rg;
    private Command rig;
    private Command show;
    private Command update;
    private Command login;
    private Command regist;

    private final LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
    public void addCom(){
        commands.put("clear", new Clear());
        commands.put("count_greater_than_price", new CountGreaterThanPrice());
        commands.put("info", new Info());
        commands.put("insert", new Insert());
        commands.put("min_by_creation_date", new MinByCreationDate());
        commands.put("print_descending", new PrintDescending());
        commands.put("remove_key", new Remove());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("replace_if_greater", new ReplaceIfGreater());
        commands.put("show", new Show());
        commands.put("update", new Update());
        commands.put("login", new Login());
        commands.put("regist", new Regist());
    }
    public String executeCom(MapCommands mc, ReadCommand com) throws IOException, ClassNotFoundException {
        addCom();
        Command cmd = commands.get(com.getComm());
        try {
            String response = cmd.execute(com, mc);
            return response;
        }
        catch (SQLException e){
            return "Something strange happened with Data Base: " +  e.getMessage();
        }
    }

    public static CommandInvoker getInstance() {
        if (instance == null) {
            instance = new CommandInvoker();
        }
        return instance;
    }
}
