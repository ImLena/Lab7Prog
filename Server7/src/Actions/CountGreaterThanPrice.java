package Actions;

import Collections.MapCommands;
import Other.ReadCommand;

import java.io.IOException;

/**
 * Класс для реализации команды count_greater_than_price
 */

public class CountGreaterThanPrice extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public String execute(ReadCommand com, MapCommands mc) throws IOException {
        String price = com.getStrArgs();
        float pr = Float.parseFloat(price);
        try {
            return mc.count_greater_than_price(pr);
        }catch (NumberFormatException e){
            return "Wrong type of price, enter command again";
        } catch (NullPointerException e){
            return "Price expected";
        }
    }
}
