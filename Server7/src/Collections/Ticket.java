package Collections;

import Exceptions.InvalidFieldException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Класс для заполнения полей ticket
 */

public class Ticket implements Comparable, Serializable {
    private String user;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null

    public Ticket(String name, Coordinates coordinates, float price, TicketType type, Person person, String user) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
        this.person = person;
        this.user = user;
    }

    private static final long serialVersionUID = 32L;


    public float getPrice() {
        return price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }


    public void setId(Long id) {
        this.id = id;
        checkId();
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        checkDate();
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getUser(){
        return user;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    public TicketType getType(){
        return type;
    }

    public Person getPerson(){
        return person;
    }

    @Override
    public String toString() {
        return ( "id = " + id + ", name = " + name + ", coordinates: " + coordinates + "; creationDate = " + creationDate + ", price = " + price + ", Ticket type = " + type + ", person: " + person + ". Element belongs to " + user);
    }

    public String toParse(){
        return name + ", " + coordinates.getX() + ", " + coordinates.getY() + ", " + price + ", " + type + ", " + person.getHeight() + ", "  + person.getLoc().getName() + ", " + person.getLoc().getX() + ", " + person.getLoc().getY() + ", " + person.getLoc().getZ() + "\n";
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return -1;
        }
        Ticket t = (Ticket) o;
        return (int)  (t.getPrice() - this.getPrice());
    }

    private void checkId() {
        if (id < 0) {
            throw new InvalidFieldException("Error in field id");
        }
    }
    private void checkDate() {
        if (creationDate == null) {
            throw new InvalidFieldException("Error in field creationDate");
        }
    }
}