package Collections;

import Actions.*;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class CommandCollection implements Serializable {

    public final LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
    public void addCom(CommandReceiver cr){
        Command clear = new Clear(cr);
        Command cgtp = new CountGreaterThanPrice(cr);
        Command es = new ExecuteScript(cr);
        Command help = new Help(cr);
        Command history = new History(cr);
        Command info = new Info(cr);
        Command insert = new Insert(cr);
        Command mbcd = new MinByCreationDate(cr);
        Command pd = new PrintDescending(cr);
        Command remove = new Remove(cr);
        Command rg = new RemoveGreater(cr);
        Command rig = new ReplaceIfGreater(cr);
        Command show = new Show(cr);
        Command update = new Update(cr);

        commands.put("clear", clear);
        commands.put("count_greater_than_price", cgtp);
        commands.put("execute_script", es);
        commands.put("help", help);
        commands.put("history", history);
        commands.put("info", info);
        commands.put("insert", insert);
        commands.put("min_by_creation_date", mbcd);
        commands.put("print_descending", pd);
        commands.put("remove_key", remove);
        commands.put("remove_greater", rg);
        commands.put("replace_if_greater", rig);
        commands.put("show", show);
        commands.put("update", update);
    }

    public Command getCommand(String str){
        return commands.get(str);
    }

    public LinkedHashMap<String, Command> getCommands(){
        return commands;
    }

}
