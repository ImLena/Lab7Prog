package Collections;



import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapCommands implements Comparable<Ticket>{
    private TicketMap tm;

    private static Long id = 0L; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private static LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null
    private Location location;

    public MapCommands(TicketMap tm) {
        this.tm = tm;
    }


    public String info() throws IOException {
        return "Collection type: " + tm.getClass().toString() + "\n" +
                "Date and time of initialization: " + tm.getCreationDate() + "\n" +
                "Number of items: " + tm.getTickets().size() + "\n";
    }

    public String count_greater_than_price(Float price) throws IOException {
        int count = 0;
        for (Map.Entry<Long, Ticket> e : tm.getTickets().entrySet()) {
            Ticket tic = e.getValue();
            if (tic.getPrice() > price) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    public String min_by_creation_date() throws IOException {
        StringBuilder sb = new StringBuilder();
        if (!tm.getTickets().isEmpty()){
            Map.Entry<Long, Ticket> tick = tm.getTickets().entrySet().stream().min(Comparator.comparing(x -> x.getValue().getCreationDate())).get();
            sb.append(tick.toString());
    }else {
        sb.append("Collection is empty");
    }
        return sb.toString();
    }

    public String print_descending() {
        StringBuilder sb = new StringBuilder();
        if (!tm.getTickets().isEmpty()) {
            tm.getTickets().entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                    .forEach(x -> sb.append(x.toString() + "\n"));
        }else {
            sb.append("Collection is empty");
        }
        return sb.toString();
    }

    public String remove(Long id, String user) throws IOException {
        if (tm.getTickets().get(id).getUser().equals(user)) {
            tm.getTickets().remove(id);
            return "Element " + id + " removed";
        } else {
            return "You can't remove element with ID " + id;
        }
    }

    public void remove_greater(Ticket tic, String user){
        tm.getTickets().entrySet().removeIf(x -> (x.getValue().compareTo(tic) < 0)&&(x.getValue().getUser().equals(user)));
    }

    public String show() throws IOException {
        StringBuilder sb = new StringBuilder();
        if (!(tm.getTickets().isEmpty())) {
            for (Map.Entry<Long, Ticket> entry : tm.getTickets().entrySet()) {
                sb.append("Key: ").append(entry.getKey()).append("\n").append("Value: ").append(entry.getValue().toString()).append("\n");
            }
            return sb.toString();
        } else return "Collection is empty\n";
    }

    public String insert(Long elemId, Ticket tic) throws IOException {
        if (!tm.getTickets().containsKey(elemId)) {
            tm.getTickets().put(elemId, tic);
            return "Element with key " + elemId + " added to collection.";
        } else {
            return "ELement with key " + elemId + " exist, to update this element use command update";
        }
    }

    public String update(Long id, Ticket tic){
       // if (tm.getTickets().containsKey(id)&&tm.getTickets().get(id).getUser().equals(tic.getUser())) {
            tm.getTickets().put(id, tic);
            return "Element with key " + id + " updated.";
        /*} else {
            return "ELement with key " + id + " doesn't exist, to insert new element use command insert.";
        }*/
    }

    public String clear(String user) {
        StringBuilder sb = new StringBuilder();
        tm.getTickets().entrySet().removeIf(x -> x.getValue().getUser().equals(user));
        return "All " + user + "'s elements removed";
    }

    public boolean replace_if_greater(Long id, Ticket tic, String user) throws IOException {
            if ((tm.getTickets().get(id).compareTo(tic) > 0)&&(tm.getTickets().get(id).getUser().equals(user))) {
                tm.getTickets().put(id, tic);
                return true;
            }else{
                return false;
            }
        }

    public LinkedHashMap<Long, Ticket> getTickets(){
        return tm.getTickets();
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
