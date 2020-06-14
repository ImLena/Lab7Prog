package Collections;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс, определяющий поля коллекции
 */

public class TicketMap implements Comparable<Ticket>, Serializable {
    private static Long id = 0L; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private static Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null
    private Location location;


    public static LinkedHashMap<Long, Ticket> Tickets = new LinkedHashMap<>();

    public static void setId(Long id) {
        id = id;
    }

    public static Long getId() {
        return id;
    }

    @Override
    public int compareTo(Ticket t) {
        if (t == null) {
            return -1;
        }
        return (int) (this.price - t.getPrice());
    }

    @Override
    public String toString(){
        return (name + ", " + coordinates.getX() + ", " + coordinates.getY() + ", " + price + ", " + type + ", " + person.getHeight() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ() + ", " + location.getName() + "\n");

    }
}
