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
    public Ticket(){
    }
    private static final long serialVersionUID = 32L;

    public void setUser(String user) {
        this.user = user;
    }

    public float getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
        checkId();
    }

    public Long getId(){
        return id;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        checkDate();
    }

    public void setName(String name) {
        this.name = name;
        checkName();
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        checkCoords();
    }

    public void setPrice(float price) {
        this.price = price;
        checkPrice();
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public void setPerson(Person person) {
        this.person = person;
        checkPers();
    }

    @Override
    public String toString() {
        return ( "id = " + id + ", name = " + name + ", coordinates: " + coordinates + "; creationDate = " + creationDate + ", price = " + price + ", Ticket type = " + type + ", person: " + person);
    }


    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return -1;
        }
        Ticket t = (Ticket) o;
        return (int)  (t.getPrice() - this.getPrice());
    }

    private void checkName() {
        if (name == null || name.isEmpty()) {
            throw new InvalidFieldException("Error in field name");
        }
    }
    private void checkCoords() {
        if (coordinates == null) {
            throw new InvalidFieldException("Error in field coordinates");
        }
    }
    private void checkId() {
        if (id < 0) {
            throw new InvalidFieldException("Error in field id");
        }
    }
    private void checkPrice() {
        if (price <= 0) {
            throw new InvalidFieldException("Error in field price");
        }
    }
    private void checkDate() {
        if (creationDate == null) {
            throw new InvalidFieldException("Error in field creationDate");
        }
    }
    private void checkPers(){
        if (person == null) {
            throw new InvalidFieldException("Error in field person");
        }
    }
}